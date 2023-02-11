package org.cliente.api.v1.dto.request;

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
public class PedidoRequestDTO {

    private Long id;

    private LocalDate horario;

    private BigDecimal valorTotal = new BigDecimal(BigInteger.ZERO);

}
