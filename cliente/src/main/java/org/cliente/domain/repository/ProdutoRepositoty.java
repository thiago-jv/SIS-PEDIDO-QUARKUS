package org.cliente.domain.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.cliente.domain.entity.ProdutoEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepositoty implements PanacheRepository<ProdutoEntity> {
}
