package client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


public class LoginController {
    private Scene scene;

    public LoginController() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f4f4f4;");

        Label title = new Label("🚍 Bus Ticket System");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginBtn = new Button("Login");
        Button registerBtn = new Button("Register");

        Label message = new Label();

        loginBtn.setOnAction(e -> {
            String response = ClientNetwork.sendRequest("LOGIN " + usernameField.getText() + " " + passwordField.getText());
            if (response.startsWith("LOGIN_SUCCESS")) {
                MainApp.setCurrentUsername(usernameField.getText());
                MainApp.showDashboard();
            } else {
                message.setText("Login Failed! Try again.");
                message.setStyle("-fx-text-fill: red;");
            }
        });

        registerBtn.setOnAction(e -> MainApp.showRegisterScreen());

        root.getChildren().addAll(title, usernameField, passwordField, loginBtn, registerBtn, message);
        scene = new Scene(root, 500, 400);
    }

    public Scene getScene() { return scene; }
}