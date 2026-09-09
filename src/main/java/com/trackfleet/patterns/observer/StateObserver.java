package com.trackfleet.patterns.observer;
import com.trackfleet.models.Parcel;
public interface StateObserver {
    void onStateChanged(Parcel parcel, String oldState, String newState);
}
