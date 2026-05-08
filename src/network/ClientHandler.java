package network;

import model.*;
import util.BookingException;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String currentUser;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while ((message = in.readLine()) != null) {
                handleRequest(message);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected");
        } finally {
            try { socket.close(); } catch (IOException ignored) {}
        }
    }

    private void handleRequest(String request) {
        try {
            String[] parts = request.split(" ");
            String command = parts[0].toUpperCase();

            switch (command) {
                case "REGISTER": handleRegister(parts); break;
                case "LOGIN": handleLogin(parts); break;
                case "GET_TRIPS": handleGetTrips(); break;
                case "BOOK": handleBook(parts); break;
                case "MY_BOOKINGS": handleMyBookings(); break;
                default: out.println("ERROR Unknown command");
            }
        } catch (Exception e) {
            out.println("ERROR " + e.getMessage());
        }
    }

    private void handleRegister(String[] parts) {
        String username = parts[1];
        String password = parts[2];
        String name = parts[3].replace("_", " ");
        String phone = parts[4];

        Passenger newUser = new Passenger(username, password, name, phone);
        DataManager.getInstance().addUser(newUser);
        out.println("REGISTER_SUCCESS");
    }

    private void handleLogin(String[] parts) {
        String username = parts[1];
        String password = parts[2];

        Passenger user = DataManager.getInstance().findUser(username, password);
        if (user != null) {
            currentUser = username;
            out.println("LOGIN_SUCCESS " + user.getName().replace(" ", "_"));
        } else {
            out.println("LOGIN_FAILED");
        }
    }

    private void handleGetTrips() {
        StringBuilder sb = new StringBuilder("TRIPS ");
        for (Trip t : DataManager.getInstance().getTrips()) {
            sb.append(t.getTripId()).append("|")
              .append(t.getFromCity()).append("|")
              .append(t.getToCity()).append("|")
              .append(t.getDate()).append("|")
              .append(t.getPrice()).append("|")
              .append(t.getAvailableSeats()).append("#");
        }
        out.println(sb.toString());
    }

    private void handleBook(String[] parts) {
        if (currentUser == null) {
            out.println("ERROR Not logged in");
            return;
        }
        try {
            String tripId = parts[1];
            String seat = parts[2];

            Trip trip = DataManager.getInstance().findTrip(tripId);
            if (trip == null) throw new BookingException("Trip not found");

            if (trip.bookSeat(seat)) {
                String bookingId = "B" + System.currentTimeMillis();
                Booking booking = new Booking(bookingId, currentUser, tripId, seat, trip.getPrice());
                DataManager.getInstance().addBooking(booking);
                out.println("BOOK_SUCCESS " + bookingId);
            } else {
                throw new BookingException("Seat not available");
            }
        } catch (BookingException e) {
            out.println("BOOK_FAILED " + e.getMessage());
        }
    }

    private void handleMyBookings() {
        if (currentUser == null) {
            out.println("ERROR Not logged in");
            return;
        }
        StringBuilder sb = new StringBuilder("MYBOOKINGS ");
        for (Booking b : DataManager.getInstance().getBookings()) {
            if (b.getUsername().equals(currentUser)) {
                sb.append(b.getBookingId()).append("|")
                  .append(b.getTripId()).append("|")
                  .append(b.getSeat()).append("|")
                  .append(b.getPrice()).append("#");
            }
        }
        out.println(sb.toString());
    }
}