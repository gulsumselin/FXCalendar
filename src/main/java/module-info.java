module me.takvim.glsm {
    requires javafx.controls;
    requires javafx.fxml;
    requires jlayer;


    opens me.takvim.fxcalendar to javafx.fxml;
    exports me.takvim.fxcalendar;
    exports me.takvim.fxcalendar.Controller;
    opens me.takvim.fxcalendar.Controller to javafx.fxml;
    exports me.takvim.fxcalendar.event;
    opens me.takvim.fxcalendar.event to javafx.fxml;
    exports me.takvim.fxcalendar.User;
    opens me.takvim.fxcalendar.User to javafx.fxml;
}