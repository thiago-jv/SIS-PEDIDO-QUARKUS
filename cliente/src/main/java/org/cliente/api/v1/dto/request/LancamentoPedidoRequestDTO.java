package org.cliente.api.v1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoPedidoRequestDTO {

    private ClienteIdRequestDTO cliente;
    private List<ProdutoIdRequestDTO> produtoIdDTOList;
}
