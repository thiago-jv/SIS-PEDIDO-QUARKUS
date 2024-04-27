package org.cliente.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.cliente.domain.dto.ClienteDTO;
import org.cliente.domain.dto.PedidoDTO;
import org.cliente.domain.dto.PedidoEmailDTO;
import org.cliente.message.KafkaEvents;
import org.cliente.message.RabitMqEvents;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@ApplicationScoped
public class PedidoProducer {

    @Inject
    private RabitMqEvents rabitMqEvents;

    @Inject
    private KafkaEvents kafkaEvents;

    public void enviaPedidoProducer(PedidoDTO pedidoDTO, Optional<ClienteDTO> clienteOptional) throws JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(clienteOptional.isPresent()) {
            var cliente = clienteOptional.get();

            var pedido = PedidoEmailDTO.builder()
                    .idPedido(pedidoDTO.getId().toString())
                    .horario(formatter.format(pedidoDTO.getHorario()))
                    .valorTotal(pedidoDTO.getValorTotal().toString())
                    .cliente(cliente.getNome())
                    .build();
            rabitMqEvents.enviaToFila(pedido);
            kafkaEvents.enviaToFila(pedido);

        }
    }

}
