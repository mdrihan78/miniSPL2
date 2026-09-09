module com.trackfleet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    opens com.trackfleet.controllers to javafx.fxml;
    opens com.trackfleet.models to javafx.base;
    exports com.trackfleet;
}
