package org.cliente.domain.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.cliente.domain.entity.ItemEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<ItemEntity> {
}
