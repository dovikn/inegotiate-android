package com.google.gdata.model.gd;

import com.google.gdata.model.Element;
import com.google.gdata.model.ElementCreator;
import com.google.gdata.model.ElementKey;
import com.google.gdata.model.MetadataRegistry;
import com.google.gdata.model.QName;
import com.google.gdata.util.Namespaces;

public class Recurrence extends Element {
    public static final ElementKey<String, Recurrence> KEY;

    static {
        KEY = ElementKey.of(new QName(Namespaces.gNs, "recurrence"), String.class, Recurrence.class);
    }

    public static void registerMetadata(MetadataRegistry registry) {
        if (!registry.isRegistered(KEY)) {
            ElementCreator builder = registry.build(KEY);
        }
    }

    public Recurrence() {
        super(KEY);
    }

    protected Recurrence(ElementKey<String, ? extends Recurrence> key) {
        super((ElementKey) key);
    }

    protected Recurrence(ElementKey<String, ? extends Recurrence> key, Element source) {
        super(key, source);
    }

    public Recurrence(String value) {
        this();
        setValue(value);
    }

    public Recurrence lock() {
        return (Recurrence) super.lock();
    }

    public String getValue() {
        return (String) super.getTextValue(KEY);
    }

    public Recurrence setValue(String value) {
        super.setTextValue(value);
        return this;
    }

    public boolean hasValue() {
        return super.hasTextValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!sameClassAs(obj)) {
            return false;
        }
        return Element.eq(getValue(), ((Recurrence) obj).getValue());
    }

    public int hashCode() {
        int result = getClass().hashCode();
        if (getValue() != null) {
            return (result * 37) + getValue().hashCode();
        }
        return result;
    }
}
