package org.cliente.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.cliente.api.v1.dto.request.ProdutoIdRequestDTO;

import java.util.List;
import java.util.Set;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoPedidoDTO {

    private ClienteIdDTO cliente;
    private Set<ProdutoIdDTO> produtoIdDTOList;
}
