package org.cliente.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate horario;

    private BigDecimal valorTotal;

    @ManyToOne(fetch = FetchType.EAGER)
    private ClienteEntity cliente;

}
