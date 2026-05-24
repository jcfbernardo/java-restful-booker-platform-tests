package br.com.portfolio.booker.api;

import br.com.portfolio.booker.clients.BookingClient;
import br.com.portfolio.booker.clients.RoomClient;
import br.com.portfolio.booker.config.BaseApiTest;
import br.com.portfolio.booker.factories.BookingFactory;
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
    private static final RoomClient roomClient = new RoomClient();
    private static int roomId;

    @BeforeAll
    static void setup() {
        // Get an existing room id from the listing — create room no longer returns roomid
        var resp = roomClient.listRooms();
        roomId = resp.jsonPath().getInt("rooms[0].roomid");
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
