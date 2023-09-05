package org.cliente;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.cliente.api.v1.controller.ClienteController;
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
@TestHTTPEndpoint(ClienteController.class)
class ClienteControllerTest {

    private String token;

//    @BeforeEach
//    public void geraToken() throws Exception {
//        token = TokenUtils.generateTokenString("/JWTProprietarioClaims.json", null);
//    }

//    private RequestSpecification given() {
//        return RestAssured.given()
//                .contentType(ContentType.JSON).header(new Header("Authorization", "Bearer " + token));
//    }

    @Test
    @Order(1)
    @DisplayName("Deve salvar um cliente")
    void salva_pedido() throws IOException {
        given()
                .contentType(ContentType.JSON)
                .body(FileUtils.readFileData("cliente/cliente_request.json"))
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("nome", equalTo("Joao"));
    }

}
