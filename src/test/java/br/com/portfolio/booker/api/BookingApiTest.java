package br.com.portfolio.booker.api;

import br.com.portfolio.booker.clients.AuthClient;
import br.com.portfolio.booker.clients.BookingClient;
import br.com.portfolio.booker.clients.RoomClient;
import br.com.portfolio.booker.config.BaseApiTest;
import br.com.portfolio.booker.factories.BookingFactory;
import br.com.portfolio.booker.factories.RoomFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Booking API")
@Feature("Bookings")
class BookingApiTest extends BaseApiTest {

    private static final BookingClient bookingClient = new BookingClient();
    private static final AuthClient authClient = new AuthClient();
    private static final RoomClient roomClient = new RoomClient();
    private static int roomId;

    @BeforeAll
    static void setup() {
        String token = authClient.getToken(
                props.getProperty("admin.username"),
                props.getProperty("admin.password")
        );
        var room = RoomFactory.buildValidRoom();
        var resp = roomClient.createRoom(room, token);
        roomId = resp.jsonPath().getInt("roomid");
    }

    @Test
    @Story("API-006")
    @DisplayName("Should create booking with valid data")
    void shouldCreateBookingWithValidData() {
        var booking = BookingFactory.buildValidBooking(roomId);
        var response = bookingClient.createBooking(booking);

        assertThat(response.statusCode()).isIn(200, 201);
    }

    @Test
    @Story("API-007")
    @DisplayName("Should return error on invalid booking payload")
    void shouldReturnErrorOnInvalidPayload() {
        var booking = BookingFactory.buildInvalidBooking();
        var response = bookingClient.createBooking(booking);

        assertThat(response.statusCode()).isIn(400, 422, 500);
    }
}
