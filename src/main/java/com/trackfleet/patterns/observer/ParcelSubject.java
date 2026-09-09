package com.trackfleet.patterns.observer;
import java.util.ArrayList;
import java.util.List;
import com.trackfleet.models.Parcel;

public class ParcelSubject {
    private List<StateObserver> observers = new ArrayList<>();
    
    public void addObserver(StateObserver o) { observers.add(o); }
    public void removeObserver(StateObserver o) { observers.remove(o); }
    
    public void notifyObservers(Parcel p, String oldState, String newState) {
        for (StateObserver o : observers) {
            o.onStateChanged(p, oldState, newState);
        }
    }
}
