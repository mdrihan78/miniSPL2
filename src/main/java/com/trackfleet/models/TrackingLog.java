package com.trackfleet.models;
public class TrackingLog {
    private int id; private int parcelId; private int branchId; private String status; private String timestamp; private String remarks;
    public TrackingLog(int id, int parcelId, int branchId, String status, String timestamp, String remarks) {
        this.id = id; this.parcelId = parcelId; this.branchId = branchId; this.status = status; this.timestamp = timestamp; this.remarks = remarks;
    }
    public int getId() { return id; }
    public int getParcelId() { return parcelId; }
    public int getBranchId() { return branchId; }
    public String getStatus() { return status; }
    public String getTimestamp() { return timestamp; }
    public String getRemarks() { return remarks; }
}