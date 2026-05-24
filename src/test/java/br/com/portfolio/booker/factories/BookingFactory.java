package br.com.portfolio.booker.factories;

import br.com.portfolio.booker.models.Booking;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingFactory {

    private static final Faker faker = new Faker();
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static Booking buildValidBooking(int roomId) {
        // Use 2030 + random day offset to avoid collisions on this shared demo site
        LocalDate checkin = LocalDate.of(2030, 1, 1).plusDays(faker.number().numberBetween(0, 364));
        LocalDate checkout = checkin.plusDays(faker.number().numberBetween(2, 5));

        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.number().numberBetween(100, 1000));
        booking.setDepositpaid(true);
        booking.setRoomid(roomId);

        Booking.BookingDates dates = new Booking.BookingDates();
        dates.setCheckin(checkin.format(FMT));
        dates.setCheckout(checkout.format(FMT));
        booking.setBookingdates(dates);

        booking.setAdditionalneeds("None");
        return booking;
    }

    public static Booking buildInvalidBooking() {
        Booking booking = new Booking();
        booking.setFirstname("");
        return booking;
    }
}
