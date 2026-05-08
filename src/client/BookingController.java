package client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


public class BookingController {
    private Scene scene;

    public BookingController() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.CENTER);

        Label title = new Label("Book a Ticket");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        TextField tripIdField = new TextField();
        tripIdField.setPromptText("Trip ID (e.g. T001)");

        TextField seatField = new TextField();
        seatField.setPromptText("Seat Number (e.g. A5)");

        Button bookBtn = new Button("Confirm Booking");
        Button backBtn = new Button("Back to Dashboard");

        Label message = new Label();

        bookBtn.setOnAction(e -> {
            String response = ClientNetwork.sendRequest("BOOK " + tripIdField.getText().trim() + " " + seatField.getText().trim());
            if (response.startsWith("BOOK_SUCCESS")) {
                message.setText("✅ Booking Successful! Booking ID: " + response.split(" ")[1]);
                message.setStyle("-fx-text-fill: green;");
            } else {
                message.setText("❌ Booking Failed - " + response);
                message.setStyle("-fx-text-fill: red;");
            }
        });

        backBtn.setOnAction(e -> MainApp.showDashboard());

        root.getChildren().addAll(title, tripIdField, seatField, bookBtn, backBtn, message);
        scene = new Scene(root, 550, 420);
    }

    public Scene getScene() { return scene; }
}