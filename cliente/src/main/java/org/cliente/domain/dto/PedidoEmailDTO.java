package org.cliente.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Builder
@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEmailDTO {

    private String idPedido;

    private String horario;

    private String valorTotal;

    private String cliente;

}
