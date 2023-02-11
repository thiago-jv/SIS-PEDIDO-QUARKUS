package org.cliente.api.v1.mapper;

import org.cliente.domain.dto.ItemDTO;
import org.cliente.domain.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemMapper {

    ItemEntity paraItemEntity(ItemDTO itemDTO);
}
