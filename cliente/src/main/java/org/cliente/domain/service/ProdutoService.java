package org.cliente.domain.service;

import org.cliente.api.v1.handler.CustomException;
import org.cliente.api.v1.mapper.ProdutoMapper;
import org.cliente.domain.dto.ProdutoDTO;
import org.cliente.domain.repository.ProdutoRepositoty;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

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

    public ProdutoDTO buscaPorId(Long id) {
        return produtoMapper.paraProdutoDTO(produtoRepositoty.findByIdOptional(id).orElseThrow(() -> new CustomException("Id:" + id + " não encontrado")));
    }

    public ProdutoDTO salva(ProdutoDTO produtoDTO) {
        return produtoMapper.paraProdutoDTO(entityManager.merge(produtoMapper.paraProdutoEntity(produtoDTO)));
    }

    @Transactional
    public ProdutoDTO atualiza(Long id, ProdutoDTO produtoDTO) {
        var entity = produtoRepositoty.findById(id);
        entity = produtoMapper.paraProdutoEntity(produtoDTO);
        return produtoMapper.paraProdutoDTO(entityManager.merge((entity)));
    }

    public void deleta(Long id) {
        try {
            produtoRepositoty.deleteById(id);
        } catch (Exception e) {
            throw new CustomException("Entidade ou recurso não encontrado" + id);
        }
    }

}
