package org.cliente.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.cliente.api.v1.handler.BusinnesException;
import org.cliente.api.v1.handler.ConflictException;
import org.cliente.api.v1.mapper.ItemMapper;
import org.cliente.api.v1.mapper.PedidoMapper;
import org.cliente.domain.dto.ClienteDTO;
import org.cliente.domain.dto.ItemDTO;
import org.cliente.domain.dto.LancamentoPedidoDTO;
import org.cliente.domain.dto.PedidoDTO;
import org.cliente.domain.dto.ProdutoDTO;
import org.cliente.domain.dto.ProdutoIdDTO;
import org.cliente.domain.repository.ItemRepository;
import org.cliente.redis.RedisService;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PedidoService {

    private final PedidoMapper pedidoMapper;

    private final ProdutoService produtoService;

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    private final ClienteService clienteService;

    private final EntityManager entityManager;

    private final PedidoProducer pedidoProducer;

    private final RedisService redisService;


    public PedidoService(PedidoMapper pedidoMapper, ProdutoService produtoService, ItemRepository itemRepository, ItemMapper itemMapper, ClienteService clienteService, EntityManager entityManager, PedidoProducer pedidoProducer, RedisService redisService) {
        this.pedidoMapper = pedidoMapper;
        this.produtoService = produtoService;
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.clienteService = clienteService;
        this.entityManager = entityManager;
        this.pedidoProducer = pedidoProducer;
        this.redisService = redisService;
    }

    @Transactional
    public PedidoDTO processa(LancamentoPedidoDTO lancamentoPedidoDTO) {
        List<ItemDTO> itensDTO = new ArrayList<>();
        var pedidoDTO = new PedidoDTO();
        var pedidoSalvo = new PedidoDTO();

        Optional<ClienteDTO> clienteOptional = Optional.ofNullable(clienteService.clienteDTObuscaPorId(lancamentoPedidoDTO.getCliente().getId()));
        if (clienteOptional.isPresent()) {
            pedidoDTO.setCliente(lancamentoPedidoDTO.getCliente());
            lancamentoPedidoDTO.getProdutoIdDTOList().stream().forEach(idProduto -> {
                Optional<ProdutoDTO> produtoOptional = Optional.ofNullable(produtoService.produtoDTObuscaPorId(idProduto.getId()));
                var produto = produtoOptional.get();
                if (validaQuantidadeEstoque(produto, idProduto)) {
                    var item = new ItemDTO();
                    item.setQuantidade(idProduto.getQuantidade());
                    item.setValorParcial(produto.getPreco());
                    item.setProduto(produto);
                    itensDTO.add(item);
                    pedidoDTO.setValorTotal(pedidoDTO.getValorTotal().add(BigDecimal.valueOf(item.getQuantidade()).multiply(item.getValorParcial())));
                    atualizaEstoqueProduto(idProduto, produto);
                }
            });
        }


        if (itensDTO.size() != 0) {

            pedidoDTO.setHorario(LocalDate.now());
            pedidoSalvo = pedidoMapper.paraPedidoDTO(entityManager.merge(pedidoMapper.paraPedidoEntity(pedidoDTO)));

            PedidoDTO finalPedidoSalvo = pedidoSalvo;
            itensDTO.stream().forEach(item -> {
                item.setPedido(finalPedidoSalvo);
                itemRepository.persist(itemMapper.paraItemEntity(item));
            });
        }
        try {
            redisService.salvaPedido(pedidoSalvo);
        } catch (JsonProcessingException e) {
            throw new BusinnesException(e.getMessage());
        }
        try {
            pedidoProducer.enviaPedidoProducer(pedidoSalvo, clienteOptional);
        } catch (JsonProcessingException e) {
            throw new BusinnesException(e.getMessage());
        }

        return pedidoSalvo;
    }

    private boolean validaQuantidadeEstoque(ProdutoDTO produto, ProdutoIdDTO idProduto) {
        if(!isEstoque(produto, idProduto)){
            throw new ConflictException("Quantidade " + idProduto.getQuantidade() + " maior que estoque do produto " + produto.getDescricao() + " com estoque " + produto.getQuantidade());
        }
        return true;
    }
    private boolean isEstoque(ProdutoDTO produto, ProdutoIdDTO idProduto) {
        return produto.getQuantidade() >= idProduto.getQuantidade();
    }


    private void atualizaEstoqueProduto(ProdutoIdDTO idProduto, ProdutoDTO produto) {
        produto.setQuantidade(produto.getQuantidade() - idProduto.getQuantidade());
        produtoService.atualiza(produto.getId(), produto);
    }

}


