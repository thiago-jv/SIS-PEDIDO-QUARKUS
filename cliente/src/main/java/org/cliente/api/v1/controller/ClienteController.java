package org.cliente.api.v1.controller;

import io.quarkus.logging.Log;
import org.apache.http.HttpStatus;
import org.cliente.api.v1.dto.request.ClienteRequestDTO;
import org.cliente.api.v1.dto.response.ClienteResponseDTO;
import org.cliente.api.v1.handler.CustomException;
import org.cliente.api.v1.handler.exception.Exception;
import org.cliente.api.v1.handler.exception.ExceptionDTO;
import org.cliente.api.v1.handler.NotFoundException;
import org.cliente.api.v1.mapper.ClienteMapper;
import org.cliente.domain.service.ClienteService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.reactive.ResponseStatus;

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
@Path("/v1/clientes")
//@SecurityScheme(securitySchemeName = "quarkus-oauth",
//        type = SecuritySchemeType.OAUTH2,
//        flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8089/auth/realms/quarkus/protocol/openid-connect/token")))
//@SecurityRequirement(name = "quarkus-oauth")
public class ClienteController {

    private final ClienteService clienteService;

    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    @Operation(summary = "Busca todos os cliente",
            description = "Realiza busca de clientes")
    @GET
    public List<ClienteResponseDTO> buscaTodos() {
        return clienteMapper.paraListClienteResponseDTO(clienteService.buscaTodos());
    }

    @Operation(summary = "Realiza insert do cliente",
            description = "Salva cliente na base de dados")
    @POST
    @Transactional
    public ClienteResponseDTO salva(ClienteRequestDTO clienteDTO) {
        try {
            return clienteMapper.paraClienteResponseDTO(clienteService.salva(clienteMapper.paraClienteDTO(clienteDTO)));
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Operation(summary = "Realiza update do cliente",
            description = "Atualiza cliente na base de dados")
    @PUT
    @Transactional
    @Path("/{id}")
    public ClienteResponseDTO atualiza(@PathParam("id") Long id, ClienteRequestDTO clienteDTO) {
        try {
            return clienteMapper.paraClienteResponseDTO(clienteService.atualiza(id, clienteMapper.paraClienteDTO(clienteDTO)));
        } catch (NotFoundException e) {
            Log.error(e.getMessage());
            throw new CustomException(404, new ExceptionDTO(e.getMessage()));
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    @Operation(summary = "Realiza delete do cliente",
            description = "Deleta cliente na base de dados")
    @ResponseStatus(HttpStatus.SC_NO_CONTENT)
    @DELETE
    @Transactional
    @Path("/{id}")
    public void deleta(@PathParam("id") Long id) {
        try {
            clienteService.deleta(id);
        } catch (NotFoundException e) {
            Log.error(e.getMessage());
            throw new CustomException(404, new ExceptionDTO(e.getMessage()));
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
    }

}
