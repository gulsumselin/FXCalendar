package me.takvim.fxcalendar.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.takvim.fxcalendar.Main;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    private Button registerButton;

    @FXML
    private Button loginButton;

    public void loginButtonClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("admin") && password.equals("admin123")) {
            redirectToAdminScreen();
        } else {
            redirectToNormalUserScreen();
        }
    }

    public void registerButtonClicked() {
        try {
            Parent registrationRoot = FXMLLoader.load(Main.class.getResource("Registration.fxml"));
            Scene registrationScene = new Scene(registrationRoot);

            Stage currentStage = (Stage) registerButton.getScene().getWindow();

            currentStage.setResizable(false);
            currentStage.setScene(registrationScene);
            currentStage.setTitle("Kayıt Ol");
        } catch (IOException e) {
            statusLabel.setText("Kayıt olma ekranı yüklenirken bir hata oluştu.");
            e.printStackTrace();
        }
    }

    private void redirectToAdminScreen() {
        try {
            Parent adminRoot = FXMLLoader.load(Main.class.getResource("admin.fxml"));
            Scene adminScene = new Scene(adminRoot);

            Stage currentStage = (Stage) registerButton.getScene().getWindow();

            currentStage.setScene(adminScene);
            currentStage.setTitle("Admin Ekranı");
        } catch (IOException e) {
            statusLabel.setText("Admin ekranı yüklenirken bir hata oluştu.");
            e.printStackTrace();
        }
    }

    private void redirectToNormalUserScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("normalUser.fxml"));
            Parent userRoot = loader.load();
            Scene userScene = new Scene(userRoot);

            Stage currentStage = (Stage) registerButton.getScene().getWindow();

            currentStage.setScene(userScene);
            currentStage.setTitle("Normal Kullanıcı Ekranı");

            NormalUserController userController = loader.getController();
            userController.setUserName(usernameField.getText());
            userController.loadEvents();
        } catch (IOException e) {
            statusLabel.setText("Normal kullanıcı ekranı yüklenirken bir hata oluştu.");
            e.printStackTrace();
        }
    }
}
