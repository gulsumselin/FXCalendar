package me.takvim.fxcalendar.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

import javazoom.jl.player.Player;
import me.takvim.fxcalendar.event.Event;
import me.takvim.fxcalendar.Main;


public class EventAddController {

    @FXML
    private DatePicker operationTimePicker;

    @FXML
    private TextField startTimeField;

    @FXML
    private TextField endTimeField;

    @FXML
    private TextField eventTypeField;

    @FXML
    private TextArea eventDescriptionArea;

    @FXML
    private Button addButton;

    private String userName;
    private TableView<Event> eventTable;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEventTable(TableView<Event> eventTable) {
        this.eventTable = eventTable;
    }

    @FXML
    private void addButtonClicked() {
        LocalDate operationDate;
        try {
            operationDate = operationTimePicker.getValue();
            if (operationDate == null) {
                operationTimePicker.getEditor().clear();
                showAlert("Hata", null, "Lütfen geçerli bir tarih formatı girin.", Alert.AlertType.ERROR);
                return;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        String operationTime = operationDate.toString();
        //Saat regex: ^([01]?[0-9]|2[0-3]):[0-5][0-9]$
        String timeRegex = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";
        String startTime = startTimeField.getText();
        String endTime = endTimeField.getText();
        if (!startTime.matches(timeRegex)) {
            System.out.println("Hata: Lütfen başlangıç saat değerlerini HH:mm formatında girin.");
            showAlert("Hata !", null, "Lütfen başlangıç saat formatını düzgün girin!", Alert.AlertType.ERROR);
            startTimeField.setText("");
            return;
        }

        if(!endTime.matches(timeRegex)) {
            System.out.println("Hata: Lütfen bitiş saat değerlerini HH:mm formatında girin.");
            showAlert("Hata !", null, "Lütfen bitiş saat formatını düzgün girin !", Alert.AlertType.ERROR);
            endTimeField.setText("");
            return;
        }

        String eventType = eventTypeField.getText();
        String eventDescription = eventDescriptionArea.getText();


        Event newEvent = new Event(operationTime, startTime, endTime, eventType, eventDescription, userName);

        saveEventToFile(newEvent);

        eventTable.getItems().add(newEvent);

        playBipSound(startTime);

        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }



    private void showAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void cancelButtonClicked() {
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    private void saveEventToFile(Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt", true))) {
            writer.write(event.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playBipSound(String startTime) {
        Thread countdownThread = new Thread(() -> {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalDate currentDate = LocalDate.now();
                LocalTime currentTime = LocalTime.now();
                LocalDateTime currentDateTime = LocalDateTime.of(currentDate, currentTime);

                LocalDate eventDate = operationTimePicker.getValue();
                LocalTime eventTime = LocalTime.parse(startTime, formatter);
                LocalDateTime eventDateTime = LocalDateTime.of(eventDate, eventTime);

                long delayInMillis = java.time.Duration.between(currentDateTime, eventDateTime).toMillis();

                if (delayInMillis > 0) {
                    Thread.sleep(delayInMillis);
                }

                playSound("bip.mp3");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        countdownThread.start();
    }

    private void playSound(String soundFile) {
        try {
            URL resourceUrl = Main.class.getResource("/sound/" + soundFile);
            if (resourceUrl == null) {
                System.err.println("Ses dosyası bulunamadı: " + soundFile);
                return;
            }

            InputStream inputStream = resourceUrl.openStream();
            Player player = new Player(inputStream);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}