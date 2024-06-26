package org.cliente.api.v1.controller;

import io.quarkus.logging.Log;
import org.apache.http.HttpStatus;
import org.cliente.api.v1.dto.pagination.FilterDTO;
import org.cliente.api.v1.dto.pagination.PageResponseDTO;
import org.cliente.api.v1.dto.request.ProdutoRequestDTO;
import org.cliente.api.v1.dto.response.ProdutoResponseDTO;
import org.cliente.api.v1.handler.CustomException;
import org.cliente.api.v1.handler.NotFoundException;
import org.cliente.api.v1.handler.exception.Exception;
import org.cliente.api.v1.handler.exception.ExceptionDTO;
import org.cliente.api.v1.mapper.ProdutoMapper;
import org.cliente.domain.dto.ProdutoDTO;
import org.cliente.domain.service.ProdutoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.reactive.ResponseStatus;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1/produtos")
//@SecurityScheme(securitySchemeName = "quarkus-oauth",
//        type = SecuritySchemeType.OAUTH2,
//        flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8089/auth/realms/quarkus/protocol/openid-connect/token")))
//@SecurityRequirement(name = "quarkus-oauth")
public class ProdutoController {

    private final ProdutoService produtoService;

    private final ProdutoMapper produtoMapper;

    public ProdutoController(ProdutoService produtoService, ProdutoMapper produtoMapper) {
        this.produtoService = produtoService;
        this.produtoMapper = produtoMapper;
    }

    @Operation(summary = "Busca todos os produtos",
            description = "Realiza busca de produtos")
    @GET
    public PageResponseDTO<List<ProdutoDTO>> buscaTodos(@BeanParam FilterDTO filterDTO) {
        return produtoService.buscaTodos(filterDTO);
    }

    @Operation(summary = "Realiza insert do produto",
            description = "Salva produto na base de dados")
    @POST
    @Transactional
    public ProdutoResponseDTO salva(ProdutoRequestDTO produtoDTO) {
        try {
            return produtoMapper.paraProdutoResponseDTO(produtoService.salva(produtoMapper.paraProdutoDTO(produtoDTO)));
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Operation(summary = "Realiza update do produto",
            description = "Atualiza produto na base de dados")
    @PUT
    @Transactional
    @Path("/{id}")
    public ProdutoResponseDTO atualiza(@PathParam("id") Long id, ProdutoRequestDTO produtoDTO) {
        try {
            return produtoMapper.paraProdutoResponseDTO(produtoService.atualiza(id, produtoMapper.paraProdutoDTO(produtoDTO)));
        } catch (NotFoundException e) {
            Log.error(e.getMessage());
            throw new CustomException(404, new ExceptionDTO(e.getMessage()));
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    @Operation(summary = "Realiza delete do produto",
            description = "Deleta produto na base de dados")
    @ResponseStatus(HttpStatus.SC_NO_CONTENT)
    @DELETE
    @Transactional
    @Path("/{id}")
    public void deleta(@PathParam("id") Long id) {
        try {
            produtoService.deleta(id);
        } catch (NotFoundException e) {
            Log.error(e.getMessage());
            throw new CustomException(404, new ExceptionDTO(e.getMessage()));
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
    }

}
