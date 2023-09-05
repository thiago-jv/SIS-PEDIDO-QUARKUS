package org.cliente.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cliente.domain.dto.PedidoEmailDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class RabitMqEvents {

    ObjectMapper objectMapper = new ObjectMapper();
    @Channel("pedido-queue")
    Emitter<String> emitterRMQ;

    @Produces(MediaType.APPLICATION_JSON)
    public void enviaToFila(PedidoEmailDTO pedido) throws JsonProcessingException {
        emitterRMQ.send(objectMapper.writeValueAsString(pedido));
    }
}
