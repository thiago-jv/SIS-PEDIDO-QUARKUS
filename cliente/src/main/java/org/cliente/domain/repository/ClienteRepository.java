package org.cliente.domain.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.cliente.domain.entity.ClienteEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<ClienteEntity> {
}
