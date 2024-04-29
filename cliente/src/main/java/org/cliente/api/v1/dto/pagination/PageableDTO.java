/**
 * @author Thiago henrique on 29/04/2024
 */


package org.cliente.api.v1.dto.pagination;

import io.quarkus.panache.common.Page;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class PageableDTO {

    @QueryParam("page")
    @DefaultValue("0")
    private @PositiveOrZero int page;
    @QueryParam("size")
    @DefaultValue("10")
    private @Positive int size;

    public PageableDTO() {
    }

    public PageableDTO(int page, int size) {
        this.page = Math.max(page, 0);
        this.size = Math.max(size, 1);
    }

    public Page getPage() {
        return Page.of(this.page, this.size);
    }
}
