package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite.GeneratedExtension;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExtensionRegistryLite {
    private static final ExtensionRegistryLite EMPTY;
    private final Map<ObjectIntPair, GeneratedExtension<?, ?>> extensionsByNumber;

    private static final class ObjectIntPair {
        private final int number;
        private final Object object;

        ObjectIntPair(Object object, int number) {
            this.object = object;
            this.number = number;
        }

        public int hashCode() {
            return (System.identityHashCode(this.object) * 65535) + this.number;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ObjectIntPair)) {
                return false;
            }
            ObjectIntPair other = (ObjectIntPair) obj;
            if (this.object == other.object && this.number == other.number) {
                return true;
            }
            return false;
        }
    }

    public static ExtensionRegistryLite newInstance() {
        return new ExtensionRegistryLite();
    }

    public static ExtensionRegistryLite getEmptyRegistry() {
        return EMPTY;
    }

    public ExtensionRegistryLite getUnmodifiable() {
        return new ExtensionRegistryLite(this);
    }

    public <ContainingType extends MessageLite> GeneratedExtension<ContainingType, ?> findLiteExtensionByNumber(ContainingType containingTypeDefaultInstance, int fieldNumber) {
        return (GeneratedExtension) this.extensionsByNumber.get(new ObjectIntPair(containingTypeDefaultInstance, fieldNumber));
    }

    public final void add(GeneratedExtension<?, ?> extension) {
        this.extensionsByNumber.put(new ObjectIntPair(extension.getContainingTypeDefaultInstance(), extension.getNumber()), extension);
    }

    ExtensionRegistryLite() {
        this.extensionsByNumber = new HashMap();
    }

    ExtensionRegistryLite(ExtensionRegistryLite other) {
        if (other == EMPTY) {
            this.extensionsByNumber = Collections.emptyMap();
        } else {
            this.extensionsByNumber = Collections.unmodifiableMap(other.extensionsByNumber);
        }
    }

    private ExtensionRegistryLite(boolean empty) {
        this.extensionsByNumber = Collections.emptyMap();
    }

    static {
        EMPTY = new ExtensionRegistryLite(true);
    }
}
