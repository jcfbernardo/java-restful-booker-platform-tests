package br.com.portfolio.booker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Room {

    private String roomName;
    private String type;
    private boolean accessible;
    private int roomPrice;
    private List<String> features;
    private String description;
    private String image;

    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean isAccessible() { return accessible; }
    public void setAccessible(boolean accessible) { this.accessible = accessible; }

    public int getRoomPrice() { return roomPrice; }
    public void setRoomPrice(int roomPrice) { this.roomPrice = roomPrice; }

    public List<String> getFeatures() { return features; }
    public void setFeatures(List<String> features) { this.features = features; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
