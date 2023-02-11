package org.cliente.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    @Schema(description = "Id", example = "1")
    private Long id;

    @Schema(description = "Produto", example = "Cano de meia")
    private String descricao;

    @Schema(description = "Valor", example = "10")
    private BigDecimal preco;

    @Schema(description = "Quantidade", example = "1")
    private Integer quantidade;
}
