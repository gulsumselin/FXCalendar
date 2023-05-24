package me.takvim.fxcalendar.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CalendarViewController {

    @FXML
    private Label monthYearLabel;

    @FXML
    private GridPane calendarGrid;

    private YearMonth currentYearMonth;
    private String username;

    public void initialize() {
        currentYearMonth = YearMonth.now();
        updateCalendar();
    }

    public void setUserName(String userName) {
        this.username = userName;
        updateCalendar();
    }

    @FXML
    private void prevMonthButtonClicked() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        updateCalendar();
    }

    @FXML
    private void nextMonthButtonClicked() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        updateCalendar();
    }

    private void updateCalendar() {
        monthYearLabel.setText(currentYearMonth.getMonth().toString() + " " + currentYearMonth.getYear());

        calendarGrid.getChildren().clear();

        LocalDate firstDayOfMonth = currentYearMonth.atDay(1);
        int dayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();

        // Haftanın hangi gününe denk geldiğine göre ay başlangıcını yerleştir.
        int startingDayOffset = dayOfWeek < DayOfWeek.MONDAY.getValue()
                ? 7 - (DayOfWeek.MONDAY.getValue() - dayOfWeek)
                : dayOfWeek - DayOfWeek.MONDAY.getValue();

        int daysInMonth = currentYearMonth.lengthOfMonth();

        int row = 0;
        int col = startingDayOffset;

        for (int day = 1; day <= daysInMonth; day++) {
            Rectangle dayBox = new Rectangle(30, 30);
            Label dayLabel = new Label(Integer.toString(day));

            LocalDate currentDate = currentYearMonth.atDay(day);
            Color color = getColorForEvent(username, currentDate);
            dayBox.setFill(color);

            calendarGrid.add(dayBox, col, row);
            calendarGrid.add(dayLabel, col, row);

            col++;
            if (col == 7) {
                col = 0;
                row++;
            }
        }
    }

    private Color getColorForEvent(String userName, LocalDate date) {
        Color defaultColor = Color.WHITE;

        try (BufferedReader reader = new BufferedReader(new FileReader("events.txt"))) {
            String line;
            Map<LocalDate, String> events = new HashMap<>();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String eventUserName = parts[0].trim();
                    String eventDateStr = parts[1].trim();
                    String eventDescription = parts[2].trim();

                    LocalDate eventDate = LocalDate.parse(eventDateStr, dateFormatter);

                    events.put(eventDate, eventUserName);
                }
            }

            if (events.containsKey(date)) {
                String eventUserName = events.get(date);

                LocalDate currentDate = LocalDate.now();
                long daysUntilEvent = currentDate.until(date).getDays();

                if (eventUserName.equals(userName)) {
                    if (daysUntilEvent <= 2) {
                        return Color.RED;
                    } else {
                        return Color.LIGHTGREEN;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return defaultColor;
    }


    @FXML
    private void closeCalendar() {
        Stage stage = (Stage) monthYearLabel.getScene().getWindow();
        stage.close();
    }
}