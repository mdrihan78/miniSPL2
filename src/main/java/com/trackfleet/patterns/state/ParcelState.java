package com.trackfleet.patterns.state;
import com.trackfleet.models.Parcel;
public interface ParcelState {
    void handleState(ParcelContext context);
    String getStatusName();
}