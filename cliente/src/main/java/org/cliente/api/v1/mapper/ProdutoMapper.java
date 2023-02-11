package org.cliente.api.v1.mapper;

import org.cliente.api.v1.dto.request.ProdutoRequestDTO;
import org.cliente.api.v1.dto.response.ProdutoResponseDTO;
import org.cliente.domain.dto.ProdutoDTO;
import org.cliente.domain.entity.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProdutoMapper {

    List<ProdutoDTO> paraListaProdutoDTO(List<ProdutoEntity> produtoEntity);

    ProdutoDTO paraProdutoDTO(ProdutoEntity produtoEntity);

    ProdutoEntity paraProdutoEntity(ProdutoDTO produtoDTO);

    List<ProdutoResponseDTO> paraListProdutoResponseDTO(List<ProdutoDTO> produtoDTO);

    ProdutoResponseDTO paraProdutoResponseDTO(ProdutoDTO produtoDTO);

    ProdutoDTO paraProdutoDTO(ProdutoRequestDTO produtoDTO);


}
