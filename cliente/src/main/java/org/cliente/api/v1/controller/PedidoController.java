package org.cliente.api.v1.controller;

import io.quarkus.logging.Log;
import org.cliente.api.v1.dto.request.LancamentoPedidoRequestDTO;
import org.cliente.api.v1.dto.response.PedidoResponseDTO;
import org.cliente.api.v1.handler.BusinnesException;
import org.cliente.api.v1.handler.ConflictException;
import org.cliente.api.v1.handler.CustomException;
import org.cliente.api.v1.handler.NotFoundException;
import org.cliente.api.v1.handler.exception.Exception;
import org.cliente.api.v1.handler.exception.ExceptionDTO;
import org.cliente.api.v1.mapper.PedidoMapper;
import org.cliente.domain.service.PedidoService;
import org.cliente.redis.RedisService;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1/pedidos")
//@SecurityScheme(securitySchemeName = "quarkus-oauth",
//        type = SecuritySchemeType.OAUTH2,
//        flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8089/auth/realms/quarkus/protocol/openid-connect/token")))
//@SecurityRequirement(name = "quarkus-oauth")
public class PedidoController {

    private final PedidoService pedidoService;

    private final PedidoMapper pedidoMapper;

    private final RedisService redisService;

    public PedidoController(PedidoService pedidoService, PedidoMapper pedidoMapper, RedisService redisService) {
        this.pedidoService = pedidoService;
        this.pedidoMapper = pedidoMapper;
        this.redisService = redisService;
    }

    @Operation(summary = "Realiza cadastro do pedido",
            description = "Cadastra pedido na base de dados passando como parametro lista de produtos e quantidades")
    @POST
    @Transactional
    public PedidoResponseDTO salva(LancamentoPedidoRequestDTO lancamentoPedidoDTO) {
        try {
            return pedidoMapper.paraPedidoResponseDTO(pedidoService.processa(pedidoMapper.paraLancamentoPedidoDTO(lancamentoPedidoDTO)));
        } catch (ConflictException e) {
            Log.error(e.getMessage());
            throw new CustomException(409, new ExceptionDTO(e.getMessage()));
        } catch (NotFoundException e) {
            Log.error(e.getMessage());
            throw new CustomException(404, new ExceptionDTO(e.getMessage()));
        } catch (BusinnesException e) {
            Log.error(e.getMessage());
            throw new CustomException(400, new ExceptionDTO(e.getMessage()));
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    @Operation(summary = "Realiza busca de pedido",
            description = "Realiza busca de pedido na base REDIS")
    @GET
    @Path("/{id}")
    public PedidoResponseDTO buscaPedido(@PathParam("id") String id) {
        try {
            return pedidoMapper.paraPedidoResponseDTO(redisService.buscaPorId(id));
        } catch (NotFoundException e) {
            Log.error(e.getMessage());
            throw new CustomException(404, new ExceptionDTO(e.getMessage()));
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
    }

}
