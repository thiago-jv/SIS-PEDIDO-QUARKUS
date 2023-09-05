package org.cliente.api.v1.controller;

import org.apache.http.HttpStatus;
import org.cliente.api.v1.dto.request.ProdutoRequestDTO;
import org.cliente.api.v1.dto.response.ProdutoResponseDTO;
import org.cliente.api.v1.handler.CustomException;
import org.cliente.api.v1.mapper.ProdutoMapper;
import org.cliente.domain.service.ProdutoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.jboss.resteasy.reactive.ResponseStatus;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    public List<ProdutoResponseDTO> buscaTodos() {
        return produtoMapper.paraListProdutoResponseDTO(produtoService.buscaTodos());
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
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
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
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

}
