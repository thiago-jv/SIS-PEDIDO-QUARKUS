package org.cliente.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.redis.client.RedisClient;
import org.cliente.api.v1.handler.CustomException;
import org.cliente.domain.dto.PedidoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class RedisService {

    private final Logger LOG = LoggerFactory.getLogger(RedisService.class);

    private final RedisClient redisClient;

    public RedisService(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public void salvaPedido(PedidoDTO dto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        var pedido = montaPedido(dto);
        var payload = objectMapper.writeValueAsString(pedido);
        LOG.info("Processando no REDIS {}", payload);
        redisClient.append(dto.getId().toString(), payload);
        redisClient.save();

    }

    private static PedidoDTO montaPedido(PedidoDTO dto) {
        var pedido = new PedidoDTO();
        pedido.setId(dto.getId());
        pedido.setValorTotal(dto.getValorTotal());
        pedido.setCliente(dto.getCliente());
        return pedido;
    }

    public PedidoDTO buscaPorId(String id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        var response = redisClient.get(id);
        if(Objects.isNull(response)){
            try {
                throw new CustomException("Registro não encontrado na base do REDIS " + id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return objectMapper.readValue(response.toString(), PedidoDTO.class);
        }
    }
}
