package br.com.portfolio.booker.clients;

import br.com.portfolio.booker.models.Booking;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingClient {

    private static final String BOOKING_PATH = "/booking/";

    public Response createBooking(Booking booking) {
        return given()
                .contentType("application/json")
                .body(booking)
                .when()
                .post(BOOKING_PATH);
    }
}
