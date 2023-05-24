package me.takvim.fxcalendar.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import me.takvim.fxcalendar.Main;
import me.takvim.fxcalendar.event.Event;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {

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
    private Button updateButton;

    private TableView<Event> eventTable;

    private String islemZamani;
    private String baslangicZamani;
    private String bitisZamani;
    private String olayTipi;
    private String olayAciklama;
    private LocalDate oldDate;
    private int dosyadakiSatirNo;

    private String userName;

    public void setOldData(String islem, String baslangic, String bitis, String olayTip, String olayAcik, int satirNo, LocalDate oldD) {
        this.islemZamani = islem;
        this.baslangicZamani = baslangic;
        this.bitisZamani = bitis;
        this.olayTipi = olayTip;
        this.olayAciklama = olayAcik;
        this.oldDate = oldD;
        this.dosyadakiSatirNo = satirNo;
    }

    public void setEventTable(TableView<Event> eventTable) {
        this.eventTable = eventTable;
    }

    public void setUserName(String username) {
        this.userName = username;
        updateFields(oldDate);
    }

    @FXML
    private void updateButtonClicked() {
        String newIslemZamani = operationTimePicker.getValue().toString();
        String newBaslangicZamani = startTimeField.getText();
        String newBitisZamani = endTimeField.getText();
        String newOlayTipi = eventTypeField.getText();
        String newOlayAciklama = eventDescriptionArea.getText();

        String dosyayaYazilacakVeri = userName + "," + newIslemZamani + "," + newBaslangicZamani + "," + newBitisZamani + "," + newOlayTipi + "," + newOlayAciklama;

        updateFile(dosyayaYazilacakVeri, dosyadakiSatirNo);
        updateEventList(newIslemZamani, newBaslangicZamani, newBitisZamani, newOlayTipi, newOlayAciklama);

        Stage currentStage = (Stage) updateButton.getScene().getWindow();
        currentStage.close();
    }

    private void updateEventList(String islemZamani, String baslangicZamani, String bitisZamani, String olayTipi, String olayAciklama) {
        Event newEvent = new Event(islemZamani, baslangicZamani, bitisZamani, olayTipi, olayAciklama, userName);
        eventTable.getItems().get(dosyadakiSatirNo-2).setOperationTime(newEvent.getOperationTime());
        eventTable.getItems().get(dosyadakiSatirNo-2).setStartTime(newEvent.getStartTime());
        eventTable.getItems().get(dosyadakiSatirNo-2).setEndTime(newEvent.getEndTime());
        eventTable.getItems().get(dosyadakiSatirNo-2).setEventType(newEvent.getEventType());
        eventTable.getItems().get(dosyadakiSatirNo-2).setEventDescription(newEvent.getEventDescription());
    }

    private void updateFile(String veri, int satirNo) {
        String fileName = "events.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            int tempLineNo = 1;

            while((line = reader.readLine()) != null) {
                if(tempLineNo != satirNo) {
                    fileContent.append(line);
                    fileContent.append(System.lineSeparator());
                    tempLineNo++;
                } else {
                    fileContent.append(veri);
                    fileContent.append(System.lineSeparator());
                    tempLineNo++;
                }
            }

            FileWriter writer = new FileWriter(fileName);
            writer.write(fileContent.toString());
            writer.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelButtonClicked() {
        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateFields(oldDate);
    }

    public void updateFields(LocalDate oldDate) {
        operationTimePicker.setValue(oldDate);
        startTimeField.setText(baslangicZamani);
        endTimeField.setText(bitisZamani);
        eventTypeField.setText(olayTipi);
        eventDescriptionArea.setText(olayAciklama);
    }
}
