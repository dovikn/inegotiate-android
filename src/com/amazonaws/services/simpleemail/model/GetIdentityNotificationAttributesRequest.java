package com.amazonaws.services.simpleemail.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GetIdentityNotificationAttributesRequest extends AmazonWebServiceRequest {
    private List<String> identities;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetIdentityNotificationAttributesRequest)) {
            return false;
        }
        GetIdentityNotificationAttributesRequest getIdentityNotificationAttributesRequest = (GetIdentityNotificationAttributesRequest) obj;
        return ((getIdentityNotificationAttributesRequest.getIdentities() == null ? 1 : 0) ^ (getIdentities() == null ? 1 : 0)) == 0 ? getIdentityNotificationAttributesRequest.getIdentities() == null || getIdentityNotificationAttributesRequest.getIdentities().equals(getIdentities()) : false;
    }

    public List<String> getIdentities() {
        if (this.identities == null) {
            this.identities = new ArrayList();
        }
        return this.identities;
    }

    public int hashCode() {
        return (getIdentities() == null ? 0 : getIdentities().hashCode()) + 31;
    }

    public void setIdentities(Collection<String> collection) {
        if (collection == null) {
            this.identities = null;
            return;
        }
        List arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.identities = arrayList;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        if (this.identities != null) {
            stringBuilder.append("Identities: " + this.identities + ", ");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public GetIdentityNotificationAttributesRequest withIdentities(Collection<String> collection) {
        if (collection == null) {
            this.identities = null;
        } else {
            List arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.identities = arrayList;
        }
        return this;
    }

    public GetIdentityNotificationAttributesRequest withIdentities(String... strArr) {
        if (getIdentities() == null) {
            setIdentities(new ArrayList(strArr.length));
        }
        for (Object add : strArr) {
            getIdentities().add(add);
        }
        return this;
    }
}
