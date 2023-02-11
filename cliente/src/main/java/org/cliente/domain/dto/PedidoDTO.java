package org.cliente.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Long id;

    private LocalDate horario;

    private BigDecimal valorTotal = new BigDecimal(BigInteger.ZERO);

    private ClienteIdDTO cliente;

}
