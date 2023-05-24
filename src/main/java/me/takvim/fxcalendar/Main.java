package me.takvim.fxcalendar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent loginRoot = FXMLLoader.load(Main.class.getResource("Login.fxml"));
        Scene loginScene = new Scene(loginRoot);

        Parent registrationRoot = FXMLLoader.load(Main.class.getResource("Registration.fxml"));
        Scene registrationScene = new Scene(registrationRoot);

        primaryStage.setScene(loginScene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/icons/ikon.png")));
        primaryStage.setTitle("Takvim");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

