package com.trackfleet.patterns.state;
public class InTransitState implements ParcelState {
    public void handleState(ParcelContext context) {
        context.setState(new DeliveredState());
    }
    public String getStatusName() { return "In Transit"; }
}