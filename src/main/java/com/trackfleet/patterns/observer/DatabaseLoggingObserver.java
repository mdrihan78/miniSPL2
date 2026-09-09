package com.trackfleet.patterns.observer;
import com.trackfleet.models.Parcel;

public class DatabaseLoggingObserver implements StateObserver {
    @Override
    public void onStateChanged(Parcel parcel, String oldState, String newState) {
        System.out.println("OBSERVER ALERT: Parcel " + parcel.getTrackingNumber() + " changed from " + oldState + " to " + newState);
        // We could also trigger a database log here, but TrackingLogDAO already does it.
        // This demonstrates the decoupling using Observer.
    }
}
