package client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class RegisterController {
    private Scene scene;

    public RegisterController() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        Label title = new Label("Create New Account");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField usernameField = new TextField(); 
        usernameField.setPromptText("Username");
        
        PasswordField passwordField = new PasswordField(); 
        passwordField.setPromptText("Password");
        
        TextField nameField = new TextField(); 
        nameField.setPromptText("Full Name");
        
        TextField phoneField = new TextField(); 
        phoneField.setPromptText("Phone Number");

        Button registerBtn = new Button("Register");
        Button backBtn = new Button("Back to Login");
        Label message = new Label();

        registerBtn.setOnAction(e -> {
            String name = nameField.getText().replace(" ", "_");
            String req = "REGISTER " + usernameField.getText() + " " + passwordField.getText() + " " + name + " " + phoneField.getText();
            String response = ClientNetwork.sendRequest(req);
            if (response.equals("REGISTER_SUCCESS")) {
                message.setText("✅ Registration Successful! Go back and login.");
                message.setStyle("-fx-text-fill: green;");
            } else {
                message.setText("Registration Failed");
                message.setStyle("-fx-text-fill: red;");
            }
        });

        backBtn.setOnAction(e -> MainApp.showLoginScreen());

        root.getChildren().addAll(title, usernameField, passwordField, nameField, phoneField, registerBtn, backBtn, message);
        scene = new Scene(root, 520, 480);
    }

    public Scene getScene() { return scene; }
}