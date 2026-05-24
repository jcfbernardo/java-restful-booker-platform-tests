package br.com.portfolio.booker.api;

import br.com.portfolio.booker.clients.AuthClient;
import br.com.portfolio.booker.clients.RoomClient;
import br.com.portfolio.booker.config.BaseApiTest;
import br.com.portfolio.booker.factories.RoomFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Room API")
@Feature("Rooms")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoomApiTest extends BaseApiTest {

    private static final AuthClient authClient = new AuthClient();
    private static final RoomClient roomClient = new RoomClient();
    private static String token;

    @BeforeAll
    static void obtainToken() {
        token = authClient.getToken(
                props.getProperty("admin.username"),
                props.getProperty("admin.password")
        );
    }

    @Test
    @Order(1)
    @Story("API-003")
    @DisplayName("Should list available rooms")
    void shouldListAvailableRooms() {
        var response = roomClient.listRooms();

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getList("rooms")).isNotNull();
    }

    @Test
    @Order(2)
    @Story("API-004")
    @DisplayName("Should create room with valid token")
    void shouldCreateRoomWithValidToken() {
        var room = RoomFactory.buildValidRoom();
        var response = roomClient.createRoom(room, token);

        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    @Order(3)
    @Story("API-005")
    @DisplayName("Should return 401 when creating room without auth")
    void shouldReturn403WhenCreatingRoomWithoutAuth() {
        var room = RoomFactory.buildValidRoom();
        var response = roomClient.createRoomWithoutAuth(room);

        assertThat(response.statusCode()).isEqualTo(401);
    }

    @Test
    @Order(4)
    @Story("API-008")
    @DisplayName("Should validate JSON contract of room listing")
    void shouldValidateRoomListingJsonContract() {
        var response = roomClient.listRooms();

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getList("rooms")).isNotNull();

        var firstRoom = response.jsonPath().getMap("rooms[0]");
        assertThat(firstRoom).containsKeys("roomid", "type", "roomPrice", "accessible");
    }
}
