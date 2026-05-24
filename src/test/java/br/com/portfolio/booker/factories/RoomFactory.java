package br.com.portfolio.booker.factories;

import br.com.portfolio.booker.models.Room;
import net.datafaker.Faker;

import java.util.List;

public class RoomFactory {

    private static final Faker faker = new Faker();
    private static final String[] TYPES = {"Single", "Double", "Twin", "Family", "Suite"};

    public static Room buildValidRoom() {
        Room room = new Room();
        room.setRoomName(String.valueOf(faker.number().numberBetween(100, 999)));
        room.setType(TYPES[faker.number().numberBetween(0, TYPES.length)]);
        room.setAccessible(faker.bool().bool());
        room.setRoomPrice(faker.number().numberBetween(50, 500));
        room.setFeatures(List.of("WiFi", "TV"));
        room.setDescription(faker.lorem().sentence());
        room.setImage("https://www.mwtestconsultancy.co.uk/img/room1.jpg");
        return room;
    }
}
