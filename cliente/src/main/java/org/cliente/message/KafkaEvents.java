package org.cliente.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cliente.domain.dto.PedidoEmailDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KafkaEvents {

    private final Logger LOG = LoggerFactory.getLogger(KafkaEvents.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @Channel("pedido")
    Emitter<String> emitterKafka;

    public void enviaToFila(PedidoEmailDTO pedido) throws JsonProcessingException {
        emitterKafka.send(objectMapper.writeValueAsString(pedido));
    }

}
