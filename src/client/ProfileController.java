package client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ProfileController {
    private Scene scene;

    public ProfileController() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        Label title = new Label("My Profile");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label info = new Label("Username: " + MainApp.getCurrentUsername() + "\n\n"
                + "This project demonstrates:\n"
                + "- Encapsulation & Polymorphism\n"
                + "- JavaFX GUI\n"
                + "- Multithreading\n"
                + "- TCP Networking");

        Button backBtn = new Button("Back to Dashboard");

        backBtn.setOnAction(e -> MainApp.showDashboard());

        root.getChildren().addAll(title, info, backBtn);
        scene = new Scene(root, 520, 400);
    }

    public Scene getScene() { return scene; }
}