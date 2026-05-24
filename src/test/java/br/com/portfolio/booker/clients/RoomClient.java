package br.com.portfolio.booker.clients;

import br.com.portfolio.booker.models.Room;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RoomClient {

    private static final String ROOM_PATH = "/room/";

    public Response listRooms() {
        return given()
                .contentType("application/json")
                .when()
                .get(ROOM_PATH);
    }

    public Response createRoom(Room room, String token) {
        return given()
                .contentType("application/json")
                .cookie("token", token)
                .body(room)
                .when()
                .post(ROOM_PATH);
    }

    public Response createRoomWithoutAuth(Room room) {
        return given()
                .contentType("application/json")
                .body(room)
                .when()
                .post(ROOM_PATH);
    }
}
