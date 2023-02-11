package org.cliente.domain.service;

import org.cliente.api.v1.handler.CustomException;
import org.cliente.api.v1.mapper.ClienteMapper;
import org.cliente.domain.dto.ClienteDTO;
import org.cliente.domain.dto.ProdutoDTO;
import org.cliente.domain.repository.ClienteRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final ClienteMapper clienteMapper;

    private final EntityManager entityManager;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper, EntityManager entityManager) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.entityManager = entityManager;
    }

    public List<ClienteDTO> buscaTodos() {
        return clienteMapper.paraListaClienteDTO(clienteRepository.findAll().stream().toList());
    }

    public ClienteDTO buscaPorId(Long id) {
        return clienteMapper.paraClienteDTO(clienteRepository.findByIdOptional(id).orElseThrow(() -> new CustomException("Id:" + id + " não encontrado")));
    }

    public ClienteDTO salva(ClienteDTO clienteDTO) {
        return clienteMapper.paraClienteDTO(entityManager.merge(clienteMapper.paraClienteEntity(clienteDTO)));
    }

    public ClienteDTO atualiza(Long id, ClienteDTO clienteDTO) {
        var entity = clienteRepository.findById(id);
        entity = clienteMapper.paraClienteEntity(clienteDTO);
        return clienteMapper.paraClienteDTO(entityManager.merge(entity));
    }

    public void deleta(Long id) {
        try {
            clienteRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException("Entidade ou recurso não encontrado " +id);
        }

    }

}
