package org.cliente.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String descricao;

    private BigDecimal preco;

    private Integer quantidade;

}
