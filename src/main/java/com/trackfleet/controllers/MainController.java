package com.trackfleet.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class MainController {
    @FXML private BorderPane mainPane;
    
    @FXML public void showDashboard() { loadView("dashboard"); }
    @FXML public void showCreateParcel() { loadView("parcel_create"); }
    @FXML public void showTracking() { loadView("tracking"); }
    
    private void loadView(String view) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/trackfleet/views/" + view + ".fxml"));
            mainPane.setCenter(loader.load());
        } catch (IOException e) { e.printStackTrace(); }
    }
}