package org.cliente.api.v1.mapper;

import org.cliente.api.v1.dto.request.LancamentoPedidoRequestDTO;
import org.cliente.api.v1.dto.response.PedidoResponseDTO;
import org.cliente.domain.dto.LancamentoPedidoDTO;
import org.cliente.domain.dto.PedidoDTO;
import org.cliente.domain.entity.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PedidoMapper {

    PedidoEntity paraPedidoEntity(PedidoDTO pedidoDTO);

    PedidoDTO paraPedidoDTO(PedidoEntity pedidoEntity);

    LancamentoPedidoDTO paraLancamentoPedidoDTO(LancamentoPedidoRequestDTO lancamentoPedidoRequestDTO);

    PedidoResponseDTO paraPedidoResponseDTO(PedidoDTO pedidoDTO);

}
