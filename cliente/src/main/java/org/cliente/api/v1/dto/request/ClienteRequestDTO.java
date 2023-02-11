package org.cliente.api.v1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO {

    @Schema(description = "Id", example = "1")
    private Long id;

    @Schema(description = "Nome", example = "Thiago")
    private String nome;

    @Schema(description = "Contato", example = "92991919191")
    private String contato;

    @Schema(description = "Email", example = "thi@hotmail.com")
    private String email;

    @Schema(description = "Endereco", example = " rua x n° 0")
    private String endereco;

    @Schema(description = "Idade", example = "33")
    private Long idade;
}
