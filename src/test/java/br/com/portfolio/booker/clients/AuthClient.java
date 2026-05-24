package br.com.portfolio.booker.clients;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthClient {

    private static final String AUTH_PATH = "/auth/login";

    public Response login(String username, String password) {
        return given()
                .contentType("application/json")
                .body(String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password))
                .when()
                .post(AUTH_PATH);
    }

    public String getToken(String username, String password) {
        return login(username, password)
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}
