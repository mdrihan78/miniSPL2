package com.trackfleet.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.trackfleet.models.*;
import com.trackfleet.dao.*;
import com.trackfleet.patterns.command.AdvanceStateCommand;
import com.trackfleet.patterns.command.Command;
import javafx.beans.property.SimpleStringProperty;
import java.util.List;

public class TrackingController {
    @FXML private TextField trackingField;
    @FXML private Label statusLabel;
    @FXML private TableView<TrackingLog> logTable;
    @FXML private TableColumn<TrackingLog, String> statusCol;
    @FXML private TableColumn<TrackingLog, String> timeCol;
    @FXML private TableColumn<TrackingLog, String> remarksCol;
    
    private ParcelDAO parcelDAO = new ParcelDAO();
    private TrackingLogDAO logDAO = new TrackingLogDAO();
    private Parcel currentParcel = null;
    
    @FXML public void initialize() {
        statusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        timeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTimestamp()));
        remarksCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRemarks()));
    }
    
    @FXML public void searchParcel() {
        String tn = trackingField.getText().trim();
        currentParcel = parcelDAO.findByTrackingNumber(tn);
        if (currentParcel != null) {
            statusLabel.setText("Current Status: " + currentParcel.getStatus());
            loadLogs();
        } else {
            statusLabel.setText("Parcel not found.");
            logTable.getItems().clear();
        }
    }
    
    @FXML public void advanceState() {
        // Using Command Pattern
        Command advanceCmd = new AdvanceStateCommand(currentParcel, () -> {
            statusLabel.setText("Current Status: " + currentParcel.getStatus());
            loadLogs();
        });
        advanceCmd.execute();
    }
    
    private void loadLogs() {
        List<TrackingLog> logs = logDAO.getLogsForParcel(currentParcel.getId());
        logTable.getItems().setAll(logs);
    }
}
