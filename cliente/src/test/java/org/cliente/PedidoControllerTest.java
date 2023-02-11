package org.cliente;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.cliente.api.v1.controller.PedidoController;
import org.cliente.utils.FileUtils;
import org.cliente.utils.TokenUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestHTTPEndpoint(PedidoController.class)
class PedidoControllerTest {

    private String token;

    @BeforeEach
    public void geraToken() throws Exception {
        token = TokenUtils.generateTokenString("/JWTProprietarioClaims.json", null);
    }

    private RequestSpecification given() {
        return RestAssured.given()
                .contentType(ContentType.JSON).header(new Header("Authorization", "Bearer " + token));
    }

    @Test
    @Order(1)
    @DisplayName("Deve salvar um pedido na base de dados, caso tenha cliente e produto com estoque")
    void salva_pedido() throws IOException {
        given()
                .contentType(ContentType.JSON)
                .body(FileUtils.readFileData("pedido/pedido_request.json"))
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("horario", equalTo("2023-02-05"))
                .body("valorTotal", equalTo(30.0F))
                .body("cliente.id", equalTo(1));
    }

}
