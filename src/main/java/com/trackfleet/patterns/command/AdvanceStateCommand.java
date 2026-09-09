package com.trackfleet.patterns.command;
import com.trackfleet.models.Parcel;
import com.trackfleet.patterns.state.ParcelContext;

public class AdvanceStateCommand implements Command {
    private Parcel parcel;
    private Runnable onComplete;

    public AdvanceStateCommand(Parcel parcel, Runnable onComplete) {
        this.parcel = parcel;
        this.onComplete = onComplete;
    }

    @Override
    public void execute() {
        if (parcel != null && !parcel.getStatus().equals("Delivered")) {
            ParcelContext context = new ParcelContext(parcel);
            context.setCurrentBranchId(parcel.getDestBranchId());
            context.proceed(); // Advances state and persists it
            if (onComplete != null) onComplete.run();
        }
    }
}
