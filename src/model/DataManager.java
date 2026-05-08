package model;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager instance;
    private List<Trip> trips = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();
    private List<Passenger> users = new ArrayList<>();

    private DataManager() {
        trips.add(new Trip("T001", "Cairo", "Alexandria", "2026-05-15", 250.0, 20));
        trips.add(new Trip("T002", "Cairo", "Luxor", "2026-05-16", 450.0, 15));
        trips.add(new Trip("T003", "Alexandria", "Hurghada", "2026-05-17", 380.0, 25));
    }

    public static DataManager getInstance() {
        if (instance == null) instance = new DataManager();
        return instance;
    }

    public List<Trip> getTrips() { return trips; }
    public List<Booking> getBookings() { return bookings; }
    public List<Passenger> getUsers() { return users; }

    public void addUser(Passenger p) { users.add(p); }
    public void addBooking(Booking b) { bookings.add(b); }

    public Passenger findUser(String username, String password) {
        for (Passenger p : users) {
            if (p.getUsername().equals(username) && p.getPassword().equals(password)) {
                return p;
            }
        }
        return null;
    }

    public Trip findTrip(String tripId) {
        for (Trip t : trips) {
            if (t.getTripId().equals(tripId)) return t;
        }
        return null;
    }
}