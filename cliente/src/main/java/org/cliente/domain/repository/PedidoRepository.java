package org.cliente.domain.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.cliente.domain.entity.PedidoEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<PedidoEntity> {
}
