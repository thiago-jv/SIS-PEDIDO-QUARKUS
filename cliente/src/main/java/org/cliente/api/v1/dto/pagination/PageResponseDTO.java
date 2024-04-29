/**
 * @author Thiago henrique on 29/04/2024
 */


package org.cliente.api.v1.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PageResponseDTO<E> implements Serializable {
    private static final long serialVersionUID = 3565813504514059646L;
    private List<E> content;
    private long numberOfPages;
    private long currentPage;
    private long quantityOfElements;
    private long totalQuantityOfElements;


}
