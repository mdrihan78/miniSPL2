package com.trackfleet.patterns.state;
public class DeliveredState implements ParcelState {
    public void handleState(ParcelContext context) {
        // Final state, no further transition
    }
    public String getStatusName() { return "Delivered"; }
}