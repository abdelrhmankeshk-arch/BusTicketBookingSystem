package client;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static Stage primaryStage;
    private static String currentUsername = "";

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("🚍 Bus Ticket Booking System");
        showLoginScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void showLoginScreen() {
        LoginController login = new LoginController();
        primaryStage.setScene(login.getScene());
    }

    public static void showRegisterScreen() {
        RegisterController register = new RegisterController();
        primaryStage.setScene(register.getScene());
    }

    public static void showDashboard() {
        DashboardController dashboard = new DashboardController();
        primaryStage.setScene(dashboard.getScene());
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }

    public static void main(String[] args) {
        launch(args);
    }
}