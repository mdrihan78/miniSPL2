package com.trackfleet.patterns.state;
public class PendingState implements ParcelState {
    public void handleState(ParcelContext context) {
        context.setState(new InTransitState());
    }
    public String getStatusName() { return "Pending"; }
}