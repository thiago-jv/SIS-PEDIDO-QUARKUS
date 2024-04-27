package org.cliente.domain.service;

import org.cliente.api.v1.handler.NotFoundException;
import org.cliente.api.v1.mapper.ClienteMapper;
import org.cliente.domain.dto.ClienteDTO;
import org.cliente.domain.entity.ClienteEntity;
import org.cliente.domain.repository.ClienteRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

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

    public ClienteDTO clienteDTObuscaPorId(Long id) {
        ClienteEntity cliente = clienteRepository.findById(id);
        if (Objects.isNull(cliente)) {
            throw new NotFoundException("Cliente: " + id + " não encontrado");
        }
        return clienteMapper.paraClienteDTO(cliente);
    }

    public ClienteEntity ClienteEntitybuscaPorId(Long id) {
        ClienteEntity cliente = clienteRepository.findById(id);
        if (Objects.isNull(cliente)) {
            throw new NotFoundException("Cliente: " + id + " não encontrado");
        }
        return cliente;
    }

    public ClienteDTO salva(ClienteDTO clienteDTO) {
        return clienteMapper.paraClienteDTO(entityManager.merge(clienteMapper.paraClienteEntity(clienteDTO)));
    }

    public ClienteDTO atualiza(Long id, ClienteDTO clienteDTO) {
        var cliente = ClienteEntitybuscaPorId(id);
        cliente = clienteMapper.paraClienteEntity(clienteDTO);
        return clienteMapper.paraClienteDTO(entityManager.merge(cliente));
    }

    public void deleta(Long id) {
        ClienteEntitybuscaPorId(id);
        clienteRepository.deleteById(id);

    }


}
