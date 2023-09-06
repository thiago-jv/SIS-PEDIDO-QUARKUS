package utils;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.smallrye.reactive.messaging.providers.connectors.InMemoryConnector;

import java.util.HashMap;
import java.util.Map;

public class KafkaResourceLifecycleManager implements QuarkusTestResourceLifecycleManager {

    @Override
    public Map<String, String> start() {
        Map<String, String> data = new HashMap<>();
        data.putAll(InMemoryConnector.switchOutgoingChannelsToInMemory("pedido"));
        return data;
    }

    @Override
    public void stop() {
        InMemoryConnector.clear();
    }
}
