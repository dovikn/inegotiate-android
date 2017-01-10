package com.amazonaws.services.sns.model.transform;

import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesResult;
import com.amazonaws.transform.MapEntry;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import java.util.Map.Entry;

public class GetSubscriptionAttributesResultStaxUnmarshaller implements Unmarshaller<GetSubscriptionAttributesResult, StaxUnmarshallerContext> {
    private static GetSubscriptionAttributesResultStaxUnmarshaller instance;

    private static class AttributesMapEntryUnmarshaller implements Unmarshaller<Entry<String, String>, StaxUnmarshallerContext> {
        private static AttributesMapEntryUnmarshaller instance;

        private AttributesMapEntryUnmarshaller() {
        }

        public static AttributesMapEntryUnmarshaller getInstance() {
            if (instance == null) {
                instance = new AttributesMapEntryUnmarshaller();
            }
            return instance;
        }

        public Entry<String, String> unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
            int currentDepth = staxUnmarshallerContext.getCurrentDepth();
            int i = currentDepth + 1;
            MapEntry mapEntry = new MapEntry();
            while (true) {
                XMLEvent nextEvent = staxUnmarshallerContext.nextEvent();
                if (!nextEvent.isEndDocument()) {
                    if (!nextEvent.isAttribute() && !nextEvent.isStartElement()) {
                        if (nextEvent.isEndElement() && staxUnmarshallerContext.getCurrentDepth() < currentDepth) {
                            break;
                        }
                    } else if (staxUnmarshallerContext.testExpression("key", i)) {
                        mapEntry.setKey(StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                    } else if (staxUnmarshallerContext.testExpression("value", i)) {
                        mapEntry.setValue(StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                    }
                } else {
                    break;
                }
            }
            return mapEntry;
        }
    }

    public static GetSubscriptionAttributesResultStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetSubscriptionAttributesResultStaxUnmarshaller();
        }
        return instance;
    }

    public GetSubscriptionAttributesResult unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
        GetSubscriptionAttributesResult getSubscriptionAttributesResult = new GetSubscriptionAttributesResult();
        int currentDepth = staxUnmarshallerContext.getCurrentDepth();
        int i = currentDepth + 1;
        if (staxUnmarshallerContext.isStartOfDocument()) {
            i += 2;
        }
        while (true) {
            XMLEvent nextEvent = staxUnmarshallerContext.nextEvent();
            if (nextEvent.isEndDocument()) {
                return getSubscriptionAttributesResult;
            }
            if (nextEvent.isAttribute() || nextEvent.isStartElement()) {
                if (staxUnmarshallerContext.testExpression("Attributes/entry", i)) {
                    Entry unmarshall = AttributesMapEntryUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext);
                    getSubscriptionAttributesResult.getAttributes().put(unmarshall.getKey(), unmarshall.getValue());
                }
            } else if (nextEvent.isEndElement() && staxUnmarshallerContext.getCurrentDepth() < currentDepth) {
                return getSubscriptionAttributesResult;
            }
        }
    }
}
