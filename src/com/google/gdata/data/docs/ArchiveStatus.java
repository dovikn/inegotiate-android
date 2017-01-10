package com.google.gdata.data.docs;

import com.google.gdata.data.AbstractExtension;
import com.google.gdata.data.AttributeGenerator;
import com.google.gdata.data.AttributeHelper;
import com.google.gdata.data.AttributeHelper.EnumToAttributeValue;
import com.google.gdata.data.AttributeHelper.LowerCaseEnumToAttributeValue;
import com.google.gdata.data.ExtensionDescription;
import com.google.gdata.data.ExtensionDescription.Default;
import com.google.gdata.util.ParseException;

@Default(localName = "archiveStatus", nsAlias = "docs", nsUri = "http://schemas.google.com/docs/2007")
public class ArchiveStatus extends AbstractExtension {
    private static final EnumToAttributeValue<Value> VALUE_ENUM_TO_ATTRIBUTE_VALUE;
    static final String XML_NAME = "archiveStatus";
    private Value value;

    public enum Value {
        ABORTED,
        ARCHIVING,
        EXPIRED,
        FINISHED,
        FLATTENING,
        QUEUED
    }

    static {
        VALUE_ENUM_TO_ATTRIBUTE_VALUE = new LowerCaseEnumToAttributeValue();
    }

    public ArchiveStatus() {
        this.value = null;
    }

    public ArchiveStatus(Value value) {
        this.value = null;
        setValue(value);
        setImmutable(true);
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value value) {
        throwExceptionIfImmutable();
        this.value = value;
    }

    public boolean hasValue() {
        return getValue() != null;
    }

    protected void validate() {
        if (this.value == null) {
            throw new IllegalStateException("Missing text content");
        }
    }

    public static ExtensionDescription getDefaultDescription(boolean required, boolean repeatable) {
        ExtensionDescription desc = ExtensionDescription.getDefaultDescription(ArchiveStatus.class);
        desc.setRequired(required);
        desc.setRepeatable(repeatable);
        return desc;
    }

    protected void putAttributes(AttributeGenerator generator) {
        generator.setContent(VALUE_ENUM_TO_ATTRIBUTE_VALUE.getAttributeValue(this.value));
    }

    protected void consumeAttributes(AttributeHelper helper) throws ParseException {
        this.value = (Value) helper.consumeEnum(null, true, Value.class, null, VALUE_ENUM_TO_ATTRIBUTE_VALUE);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!sameClassAs(obj)) {
            return false;
        }
        return AbstractExtension.eq(this.value, ((ArchiveStatus) obj).value);
    }

    public int hashCode() {
        int result = getClass().hashCode();
        if (this.value != null) {
            return (result * 37) + this.value.hashCode();
        }
        return result;
    }

    public String toString() {
        return "{ArchiveStatus value=" + this.value + "}";
    }
}
