package org.cliente.client;

import org.cliente.domain.dto.EnderecoDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@RegisterRestClient(configKey = "cep-api")
public interface ViaCepClient {

    @GET
    @Path("{cep}/json/")
    EnderecoDTO buscaEndereco(@PathParam("cep") String cep);
}
