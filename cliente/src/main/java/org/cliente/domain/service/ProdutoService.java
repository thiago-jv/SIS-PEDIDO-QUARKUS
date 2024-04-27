package org.cliente.domain.service;

import org.cliente.api.v1.handler.NotFoundException;
import org.cliente.api.v1.mapper.ProdutoMapper;
import org.cliente.domain.dto.ProdutoDTO;
import org.cliente.domain.entity.ProdutoEntity;
import org.cliente.domain.repository.ProdutoRepositoty;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ProdutoService {

    private final EntityManager entityManager;

    private final ProdutoRepositoty produtoRepositoty;

    private final ProdutoMapper produtoMapper;


    public ProdutoService(EntityManager entityManager, ProdutoRepositoty produtoRepositoty, ProdutoMapper produtoMapper) {
        this.entityManager = entityManager;
        this.produtoRepositoty = produtoRepositoty;
        this.produtoMapper = produtoMapper;
    }

    public List<ProdutoDTO> buscaTodos() {
        return produtoMapper.paraListaProdutoDTO(produtoRepositoty.findAll().stream().toList());
    }

    public ProdutoDTO produtoDTObuscaPorId(Long id) {
        var entity = produtoRepositoty.findById(id);
        if (Objects.isNull(entity)) {
            throw new NotFoundException("Produto: " + id + " não encontrado");
        }
        return produtoMapper.paraProdutoDTO(entity);
    }

    public ProdutoEntity produtoEntitybuscaPorId(Long id) {
        var entity = produtoRepositoty.findById(id);
        if (Objects.isNull(entity)) {
            throw new NotFoundException("Produto: " + id + " não encontrado");
        }
        return entity;
    }

    public ProdutoDTO salva(ProdutoDTO produtoDTO) {
        return produtoMapper.paraProdutoDTO(entityManager.merge(produtoMapper.paraProdutoEntity(produtoDTO)));
    }

    @Transactional
    public ProdutoDTO atualiza(Long id, ProdutoDTO produtoDTO) {
        var entity = produtoEntitybuscaPorId(id);
        entity = produtoMapper.paraProdutoEntity(produtoDTO);
        return produtoMapper.paraProdutoDTO(entityManager.merge((entity)));
    }

    public void deleta(Long id) {
        produtoEntitybuscaPorId(id);
        produtoRepositoty.deleteById(id);
    }

}
