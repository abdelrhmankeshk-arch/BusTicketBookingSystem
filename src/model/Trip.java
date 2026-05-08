package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Trip implements Serializable {
    private String tripId;
    private String fromCity;
    private String toCity;
    private String date;
    private double price;
    private int totalSeats;
    private List<String> bookedSeats;

    public Trip(String tripId, String fromCity, String toCity, String date, double price, int totalSeats) {
        this.tripId = tripId;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.date = date;
        this.price = price;
        this.totalSeats = totalSeats;
        this.bookedSeats = new ArrayList<>();
    }

    public boolean bookSeat(String seat) {
        if (bookedSeats.size() < totalSeats && !bookedSeats.contains(seat)) {
            bookedSeats.add(seat);
            return true;
        }
        return false;
    }

    public int getAvailableSeats() {
        return totalSeats - bookedSeats.size();
    }

    public String getTripId() { return tripId; }
    public String getFromCity() { return fromCity; }
    public String getToCity() { return toCity; }
    public String getDate() { return date; }
    public double getPrice() { return price; }
    public List<String> getBookedSeats() { return bookedSeats; }
}