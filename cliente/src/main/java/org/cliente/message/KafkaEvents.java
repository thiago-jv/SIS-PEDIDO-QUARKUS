package org.cliente.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.cliente.domain.dto.PedidoEmailDTO;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class KafkaEvents {

    private final Logger LOG = LoggerFactory.getLogger(KafkaEvents.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @ConfigProperty(name = "topico.pedido.kafka")
    private String topicoPedido;

    @Channel("pedido-out")
    Emitter<String> emitterKafka;

    // estrategia de pre, pos e mais processamento antes do commit
    // @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)

    // caso ocorra algum problema no envio, o kafka irá tentar contornar
    // delay = 10 tempo de 10s e tente novamente por maxRetries = 5 vezes
    @Retry(delay = 10, maxRetries = 5, delayUnit = ChronoUnit.SECONDS)
    public void enviaToFila(PedidoEmailDTO pedido) throws JsonProcessingException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // metadata para enviar dados adicionais
        emitterKafka.send(Message.of(objectMapper.writeValueAsString(pedido)).addMetadata(OutgoingKafkaRecordMetadata.<String>builder()
                .withKey(pedido.getIdPedido().toString())
                .withHeaders(new RecordHeaders().add("x-signature", pedido.getIdPedido().getBytes(StandardCharsets.UTF_8)))
                .withTopic(topicoPedido)
                .withTimestamp(timestamp.toInstant())
                .build()
        ));
    }

}
