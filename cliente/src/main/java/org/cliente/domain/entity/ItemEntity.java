package org.cliente.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "item")
public class ItemEntity  {

    @Id
    @GeneratedValue
    private Long id;

    private Integer quantidade;

    private BigDecimal valorParcial;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProdutoEntity produto;

    @ManyToOne(fetch = FetchType.EAGER)
    private PedidoEntity pedido;

}
