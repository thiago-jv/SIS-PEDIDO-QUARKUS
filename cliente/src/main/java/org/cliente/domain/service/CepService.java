package org.cliente.domain.service;

import org.cliente.client.ViaCepClient;
import org.cliente.domain.dto.EnderecoDTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CepService {

    private final ViaCepClient viaCepClient;

    public CepService(@RestClient ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    public EnderecoDTO buscaCep(String cep){
        return viaCepClient.buscaEndereco(cep);
    }
}
