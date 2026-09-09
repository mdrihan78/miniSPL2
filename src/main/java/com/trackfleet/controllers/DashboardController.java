package com.trackfleet.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.trackfleet.dao.ParcelDAO;

public class DashboardController {
    @FXML private Label welcomeLabel;
    @FXML public void initialize() {
        welcomeLabel.setText("Welcome to TrackFleet Management System!");
    }
}