package org.cliente.domain.service;

import org.cliente.api.v1.dto.pagination.FilterDTO;
import org.cliente.api.v1.dto.pagination.PageResponseDTO;
import org.cliente.api.v1.handler.NotFoundException;
import org.cliente.api.v1.mapper.ProdutoMapper;
import org.cliente.domain.dto.ProdutoDTO;
import org.cliente.domain.entity.ProdutoEntity;
import org.cliente.domain.repository.ProdutoRepositoty;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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

    public PageResponseDTO<List<ProdutoDTO>> buscaTodos(FilterDTO filterDTO) {

        String jpql = "SELECT p FROM ProdutoEntity p";
        Query query = entityManager.createQuery(jpql);
        query.setMaxResults(filterDTO.getPage().size);
        query.setFirstResult(filterDTO.getPage().index * filterDTO.getPage().size);

        Long totalCount = (Long) entityManager.createQuery("select count(p) from ProdutoEntity p").getSingleResult();

        Integer pageCount = totalCount.intValue() / filterDTO.getPage().size;
        List originalContent = query.getResultList();

        return PageResponseDTO.<ProdutoEntity>builder().content(produtoMapper.paraListaProdutoDTO(originalContent))
                .numberOfPages(pageCount)
                .currentPage(filterDTO.getPage().index)
                .quantityOfElements(originalContent.size())
                .totalQuantityOfElements(totalCount).build();
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
