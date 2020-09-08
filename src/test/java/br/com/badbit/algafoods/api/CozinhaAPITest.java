package br.com.badbit.algafoods.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CozinhaAPITest {

    @LocalServerPort
    private int port;

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas() {
        RestAssured.given()
                .baseUri("/cozinhas")
                .port(port)
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(200);
    }

}
