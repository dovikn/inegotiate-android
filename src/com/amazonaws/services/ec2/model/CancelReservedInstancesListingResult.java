package com.amazonaws.services.ec2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CancelReservedInstancesListingResult {
    private List<ReservedInstancesListing> reservedInstancesListings;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CancelReservedInstancesListingResult)) {
            return false;
        }
        CancelReservedInstancesListingResult cancelReservedInstancesListingResult = (CancelReservedInstancesListingResult) obj;
        return ((cancelReservedInstancesListingResult.getReservedInstancesListings() == null ? 1 : 0) ^ (getReservedInstancesListings() == null ? 1 : 0)) == 0 ? cancelReservedInstancesListingResult.getReservedInstancesListings() == null || cancelReservedInstancesListingResult.getReservedInstancesListings().equals(getReservedInstancesListings()) : false;
    }

    public List<ReservedInstancesListing> getReservedInstancesListings() {
        if (this.reservedInstancesListings == null) {
            this.reservedInstancesListings = new ArrayList();
        }
        return this.reservedInstancesListings;
    }

    public int hashCode() {
        return (getReservedInstancesListings() == null ? 0 : getReservedInstancesListings().hashCode()) + 31;
    }

    public void setReservedInstancesListings(Collection<ReservedInstancesListing> collection) {
        if (collection == null) {
            this.reservedInstancesListings = null;
            return;
        }
        List arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.reservedInstancesListings = arrayList;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        if (this.reservedInstancesListings != null) {
            stringBuilder.append("ReservedInstancesListings: " + this.reservedInstancesListings + ", ");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public CancelReservedInstancesListingResult withReservedInstancesListings(Collection<ReservedInstancesListing> collection) {
        if (collection == null) {
            this.reservedInstancesListings = null;
        } else {
            List arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.reservedInstancesListings = arrayList;
        }
        return this;
    }

    public CancelReservedInstancesListingResult withReservedInstancesListings(ReservedInstancesListing... reservedInstancesListingArr) {
        if (getReservedInstancesListings() == null) {
            setReservedInstancesListings(new ArrayList(reservedInstancesListingArr.length));
        }
        for (Object add : reservedInstancesListingArr) {
            getReservedInstancesListings().add(add);
        }
        return this;
    }
}
