package org.cliente.api.v1.controller;

import org.cliente.api.v1.dto.request.LancamentoPedidoRequestDTO;
import org.cliente.api.v1.dto.response.PedidoResponseDTO;
import org.cliente.api.v1.handler.CustomException;
import org.cliente.api.v1.mapper.PedidoMapper;
import org.cliente.domain.service.PedidoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

    public PedidoController(PedidoService pedidoService, PedidoMapper pedidoMapper) {
        this.pedidoService = pedidoService;
        this.pedidoMapper = pedidoMapper;
    }

    @Operation(summary = "Realiza cadastro do pedido",
            description = "Cadastra pedido na base de dados passando como parametro lista de produtos e quantidades")
    @POST
    @Transactional
    public PedidoResponseDTO salva(LancamentoPedidoRequestDTO lancamentoPedidoDTO) {
        try {
            return pedidoMapper.paraPedidoResponseDTO(pedidoService.processa(pedidoMapper.paraLancamentoPedidoDTO(lancamentoPedidoDTO)));
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}
