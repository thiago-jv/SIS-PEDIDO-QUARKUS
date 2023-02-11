package org.cliente.api.v1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDTO {

    private Long id;

    private Integer quantidade;

    private BigDecimal valorParcial;

    private ProdutoRequestDTO produto;

    private PedidoRequestDTO pedido;
}
