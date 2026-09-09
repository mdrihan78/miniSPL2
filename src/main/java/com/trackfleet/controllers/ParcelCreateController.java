package com.trackfleet.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.trackfleet.models.*;
import com.trackfleet.dao.*;
import com.trackfleet.patterns.strategy.*;
import com.trackfleet.patterns.builder.ParcelBuilder;
import com.trackfleet.patterns.factory.PricingStrategyFactory;
import java.util.UUID;

public class ParcelCreateController {
    @FXML private ComboBox<Customer> senderCombo;
    @FXML private ComboBox<Customer> receiverCombo;
    @FXML private ComboBox<Branch> originCombo;
    @FXML private ComboBox<Branch> destCombo;
    @FXML private TextField weightField;
    @FXML private ComboBox<String> strategyCombo;
    @FXML private Label resultLabel;
    
    private BranchDAO branchDAO = new BranchDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    private ParcelDAO parcelDAO = new ParcelDAO();
    
    @FXML public void initialize() {
        refreshData();
        strategyCombo.getItems().clear();
        strategyCombo.getItems().addAll("Standard", "Express");
        if(strategyCombo.getItems().size() > 0) strategyCombo.getSelectionModel().selectFirst();
    }
    
    @FXML public void refreshData() {
        senderCombo.getItems().setAll(customerDAO.getAll());
        receiverCombo.getItems().setAll(customerDAO.getAll());
        originCombo.getItems().setAll(branchDAO.getAll());
        destCombo.getItems().setAll(branchDAO.getAll());
        if(senderCombo.getItems().size() > 0) senderCombo.getSelectionModel().selectFirst();
        if(receiverCombo.getItems().size() > 0) receiverCombo.getSelectionModel().selectFirst();
        if(originCombo.getItems().size() > 0) originCombo.getSelectionModel().selectFirst();
        if(destCombo.getItems().size() > 0) destCombo.getSelectionModel().selectFirst();
    }
    
    @FXML public void createParcel() {
        try {
            if (senderCombo.getValue() == null || originCombo.getValue() == null) {
                resultLabel.setText("Please select valid options. Ensure database is seeded.");
                return;
            }
            
            double weight = Double.parseDouble(weightField.getText());
            // Using Factory Pattern to get Strategy
            PricingStrategy strategy = PricingStrategyFactory.getStrategy(strategyCombo.getValue());
            double price = strategy.calculatePrice(weight);
            
            // Using Builder Pattern
            Parcel p = new ParcelBuilder()
                .setTrackingNumber(UUID.randomUUID().toString().substring(0,8).toUpperCase())
                .setSenderId(senderCombo.getValue().getId())
                .setReceiverId(receiverCombo.getValue().getId())
                .setOriginBranchId(originCombo.getValue().getId())
                .setDestBranchId(destCombo.getValue().getId())
                .setWeight(weight)
                .setPrice(price)
                .setStatus("Pending")
                .build();
            
            parcelDAO.save(p); // saves and generates ID
            new TrackingLogDAO().addLog(p.getId(), p.getOriginBranchId(), "Pending", "Parcel accepted at origin");
            
            resultLabel.setText("Parcel Created! Tracking: " + p.getTrackingNumber() + " | Price: $" + price);
        } catch(Exception e) {
            resultLabel.setText("Error creating parcel. Check weight input.");
            e.printStackTrace();
        }
    }
}
