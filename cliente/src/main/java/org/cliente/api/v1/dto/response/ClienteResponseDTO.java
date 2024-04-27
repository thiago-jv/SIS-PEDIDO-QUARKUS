package org.cliente.api.v1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {

    private String id;

    private String nome;

    private String contato;

    private String email;

    private String endereco;

    private Long idade;
}
