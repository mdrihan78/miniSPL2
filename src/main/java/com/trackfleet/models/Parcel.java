package com.trackfleet.models;
public class Parcel {
    private int id; private String trackingNumber; private int senderId; private int receiverId;
    private int originBranchId; private int destBranchId; private double weight; private double price; private String status;
    public Parcel() {}
    // Getters and Setters
    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public String getTrackingNumber() { return trackingNumber; } public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public int getSenderId() { return senderId; } public void setSenderId(int senderId) { this.senderId = senderId; }
    public int getReceiverId() { return receiverId; } public void setReceiverId(int receiverId) { this.receiverId = receiverId; }
    public int getOriginBranchId() { return originBranchId; } public void setOriginBranchId(int originBranchId) { this.originBranchId = originBranchId; }
    public int getDestBranchId() { return destBranchId; } public void setDestBranchId(int destBranchId) { this.destBranchId = destBranchId; }
    public double getWeight() { return weight; } public void setWeight(double weight) { this.weight = weight; }
    public double getPrice() { return price; } public void setPrice(double price) { this.price = price; }
    public String getStatus() { return status; } public void setStatus(String status) { this.status = status; }
}