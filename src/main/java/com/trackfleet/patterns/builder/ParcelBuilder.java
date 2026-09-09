package com.trackfleet.patterns.builder;
import com.trackfleet.models.Parcel;

public class ParcelBuilder {
    private Parcel parcel;

    public ParcelBuilder() {
        this.parcel = new Parcel();
    }

    public ParcelBuilder setTrackingNumber(String tn) { parcel.setTrackingNumber(tn); return this; }
    public ParcelBuilder setSenderId(int sid) { parcel.setSenderId(sid); return this; }
    public ParcelBuilder setReceiverId(int rid) { parcel.setReceiverId(rid); return this; }
    public ParcelBuilder setOriginBranchId(int oid) { parcel.setOriginBranchId(oid); return this; }
    public ParcelBuilder setDestBranchId(int did) { parcel.setDestBranchId(did); return this; }
    public ParcelBuilder setWeight(double w) { parcel.setWeight(w); return this; }
    public ParcelBuilder setPrice(double p) { parcel.setPrice(p); return this; }
    public ParcelBuilder setStatus(String s) { parcel.setStatus(s); return this; }

    public Parcel build() {
        return this.parcel;
    }
}
