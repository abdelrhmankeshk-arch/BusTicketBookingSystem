package client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class DashboardController {
    private Scene scene;

    public DashboardController() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #f0f0f0;");

        Label welcome = new Label("Welcome, " + MainApp.getCurrentUsername());
        welcome.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button btnBrowse = new Button("Browse Trips");
        Button btnBook = new Button("Book Ticket");
        Button btnMyBookings = new Button("My Bookings");
        Button btnProfile = new Button("My Profile");
        Button btnLogout = new Button("Logout");

        TextArea displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setPrefHeight(250);

        btnBrowse.setOnAction(e -> {
            String trips = ClientNetwork.sendRequest("GET_TRIPS");
            displayArea.setText(formatTrips(trips));
        });

        btnBook.setOnAction(e -> {
            BookingController booking = new BookingController();
            MainApp.primaryStage.setScene(booking.getScene());
        });

        btnMyBookings.setOnAction(e -> {
            MyBookingsController myBookings = new MyBookingsController();
            MainApp.primaryStage.setScene(myBookings.getScene());
        });

        btnProfile.setOnAction(e -> {
            ProfileController profile = new ProfileController();
            MainApp.primaryStage.setScene(profile.getScene());
        });

        btnLogout.setOnAction(e -> MainApp.showLoginScreen());

        HBox buttons = new HBox(10, btnBrowse, btnBook, btnMyBookings, btnProfile, btnLogout);
        buttons.setAlignment(Pos.CENTER);

        root.getChildren().addAll(welcome, buttons, displayArea);
        scene = new Scene(root, 780, 580);
    }

    private String formatTrips(String data) {
        if (!data.startsWith("TRIPS")) return "No trips available";
        String[] trips = data.substring(6).split("#");
        StringBuilder sb = new StringBuilder("Available Trips:\n\n");
        for (String t : trips) {
            if (!t.isEmpty()) {
                String[] info = t.split("\\|");
                sb.append(info[0]).append(" | ").append(info[1]).append(" → ").append(info[2])
                  .append("\nDate: ").append(info[3]).append(" | Price: ").append(info[4])
                  .append(" | Seats: ").append(info[5]).append("\n\n");
            }
        }
        return sb.toString();
    }

    public Scene getScene() { return scene; }
}