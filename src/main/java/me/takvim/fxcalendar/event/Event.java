package me.takvim.fxcalendar.event;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Event {
    private final StringProperty operationTime;
    private final StringProperty startTime;
    private final StringProperty endTime;
    private final StringProperty eventType;
    private final StringProperty eventDescription;
    private final String userName;

    public Event(String operationTime, String startTime, String endTime, String eventType, String eventDescription, String userName) {
        this.operationTime = new SimpleStringProperty(operationTime);
        this.startTime = new SimpleStringProperty(startTime);
        this.endTime = new SimpleStringProperty(endTime);
        this.eventType = new SimpleStringProperty(eventType);
        this.eventDescription = new SimpleStringProperty(eventDescription);
        this.userName = userName;
    }

    public String getOperationTime() {
        return operationTime.get();
    }

    public StringProperty operationTimeProperty() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime.set(operationTime);
    }

    public String getStartTime() {
        return startTime.get();
    }

    public StringProperty startTimeProperty() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
    }

    public String getEndTime() {
        return endTime.get();
    }

    public StringProperty endTimeProperty() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime.set(endTime);
    }

    public String getEventType() {
        return eventType.get();
    }

    public StringProperty eventTypeProperty() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType.set(eventType);
    }

    public String getEventDescription() {
        return eventDescription.get();
    }

    public StringProperty eventDescriptionProperty() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription.set(eventDescription);
    }

    public String getUserName() {
        return userName;
    }

    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s", getUserName(), operationTime.get(), startTime.get(), endTime.get(), eventType.get(), eventDescription.get());
    }
}