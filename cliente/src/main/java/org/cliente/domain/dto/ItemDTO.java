package org.cliente.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private Long id;

    private Integer quantidade;

    private BigDecimal valorParcial;

    private ProdutoDTO produto;

    private PedidoDTO pedido;
}
