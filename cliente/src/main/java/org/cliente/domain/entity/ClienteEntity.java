package org.cliente.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private String contato;

    private String email;

    private String endereco;

    private Long idade;

}
