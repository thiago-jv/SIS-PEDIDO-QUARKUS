package utils;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockRestClient implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMock;

    @Override
    public Map<String, String> start() {
        wireMock = new WireMockServer(6223);
        wireMock.start();

        return Map.of("quarkus.rest-client.api-cep.url", wireMock.baseUrl());
    }

    @Override
    public void stop() {
        if (null != wireMock) {
            wireMock.stop();
            wireMock = null;
        }
    }

    @Override
    public void inject(TestInjector testInjector) {
        testInjector.injectIntoFields(wireMock, new TestInjector.AnnotatedAndMatchesType(WireMock.class, WireMockServer.class));
    }

    public static void regitroDeUrls(WireMockServer wireMockServer, String url, String body, int httpStatus) {
        wireMockServer.stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                                body
                        )
                        .withStatus(httpStatus)));
    }
}
