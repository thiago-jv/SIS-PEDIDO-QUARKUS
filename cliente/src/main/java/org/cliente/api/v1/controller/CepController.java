package org.cliente.api.v1.controller;

import org.cliente.domain.dto.EnderecoDTO;
import org.cliente.domain.service.CepService;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1/ceps")
public class CepController {

    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @Operation(summary = "Realiza busca de cep",
            description = "Realiza busca de cep no cliente externo ViaCep -> https://viacep.com.br/")
    @GET
    @Path("/{cep}")
    public EnderecoDTO buscarPorCep(@PathParam("cep") String cep) {
        return cepService.buscaCep(cep);
    }
}
