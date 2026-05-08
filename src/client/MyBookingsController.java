package client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class MyBookingsController {
    private Scene scene;

    public MyBookingsController() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        Label title = new Label("My Bookings");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        TextArea displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setPrefHeight(320);

        Button loadBtn = new Button("Load My Bookings");
        Button backBtn = new Button("Back to Dashboard");

        loadBtn.setOnAction(e -> {
            String response = ClientNetwork.sendRequest("MY_BOOKINGS");
            displayArea.setText(formatBookings(response));
        });

        backBtn.setOnAction(e -> MainApp.showDashboard());

        root.getChildren().addAll(title, loadBtn, displayArea, backBtn);
        scene = new Scene(root, 620, 520);
    }

    private String formatBookings(String data) {
        if (!data.startsWith("MYBOOKINGS")) return "Error loading bookings";
        String[] bookings = data.substring(11).split("#");
        if (bookings.length == 0 || bookings[0].isEmpty())
            return "You have no bookings yet.";

        StringBuilder sb = new StringBuilder("My Bookings:\n\n");
        for (String b : bookings) {
            if (!b.isEmpty()) {
                String[] info = b.split("\\|");
                sb.append("Booking ID: ").append(info[0])
                  .append("\nTrip: ").append(info[1])
                  .append("\nSeat: ").append(info[2])
                  .append("\nPrice: ").append(info[3]).append("\n\n");
            }
        }
        return sb.toString();
    }

    public Scene getScene() { return scene; }
}