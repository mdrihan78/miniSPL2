package com.trackfleet.patterns.state;
import com.trackfleet.models.Parcel;
import com.trackfleet.dao.ParcelDAO;
import com.trackfleet.dao.TrackingLogDAO;
import com.trackfleet.patterns.observer.ParcelSubject;
import com.trackfleet.patterns.observer.DatabaseLoggingObserver;

public class ParcelContext {
    private ParcelState state;
    private Parcel parcel;
    private int currentBranchId;
    private ParcelSubject subject;
    
    public ParcelContext(Parcel parcel) {
        this.parcel = parcel;
        this.subject = new ParcelSubject();
        this.subject.addObserver(new DatabaseLoggingObserver()); // Attach observer
        
        switch(parcel.getStatus()) {
            case "Pending": this.state = new PendingState(); break;
            case "In Transit": this.state = new InTransitState(); break;
            case "Delivered": this.state = new DeliveredState(); break;
            default: this.state = new PendingState();
        }
    }
    
    public void setCurrentBranchId(int id) { this.currentBranchId = id; }
    public int getCurrentBranchId() { return currentBranchId; }
    public Parcel getParcel() { return parcel; }
    
    public void setState(ParcelState newState) {
        String oldStateStr = parcel.getStatus();
        this.state = newState;
        String newStateStr = newState.getStatusName();
        
        parcel.setStatus(newStateStr);
        new ParcelDAO().updateStatus(parcel.getId(), newStateStr);
        new TrackingLogDAO().addLog(parcel.getId(), currentBranchId, newStateStr, "State transitioned to " + newStateStr);
        
        // Trigger Observer
        subject.notifyObservers(parcel, oldStateStr, newStateStr);
    }
    
    public void proceed() {
        if(state != null) { state.handleState(this); }
    }
}
