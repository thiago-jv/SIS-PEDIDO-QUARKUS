package org.cliente.api.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ProdutoResponseDTO {

    private Long id;

    private String descricao;

    private BigDecimal preco;

    private Integer quantidade;
}
