package model;

import java.io.Serializable;

public class Booking implements Serializable {
    private String bookingId;
    private String username;
    private String tripId;
    private String seat;
    private double price;

    public Booking(String bookingId, String username, String tripId, String seat, double price) {
        this.bookingId = bookingId;
        this.username = username;
        this.tripId = tripId;
        this.seat = seat;
        this.price = price;
    }

    public String getBookingId() { return bookingId; }
    public String getUsername() { return username; }
    public String getTripId() { return tripId; }
    public String getSeat() { return seat; }
    public double getPrice() { return price; }
}