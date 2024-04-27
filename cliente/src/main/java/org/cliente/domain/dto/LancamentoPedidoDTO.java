package org.cliente.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.Set;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoPedidoDTO {

    private ClienteIdDTO cliente;
    private Set<ProdutoIdDTO> produtoIdDTOList;
}
