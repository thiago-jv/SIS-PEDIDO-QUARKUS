package org.cliente.api.v1.mapper;

import org.cliente.api.v1.dto.request.ClienteRequestDTO;
import org.cliente.api.v1.dto.response.ClienteResponseDTO;
import org.cliente.domain.dto.ClienteDTO;
import org.cliente.domain.entity.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {

    List<ClienteDTO> paraListaClienteDTO(List<ClienteEntity> clienteEntity);

    ClienteEntity paraClienteEntity(ClienteDTO clienteDTO);

    ClienteDTO paraClienteDTO(ClienteEntity clienteEntity);

    List<ClienteResponseDTO> paraListClienteResponseDTO(List<ClienteDTO> clienteDTO);

    ClienteResponseDTO paraClienteResponseDTO(ClienteDTO clienteDTO);

    ClienteDTO paraClienteDTO(ClienteRequestDTO clienteDTO);
}
