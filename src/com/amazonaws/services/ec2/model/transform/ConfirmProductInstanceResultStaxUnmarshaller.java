package com.amazonaws.services.ec2.model.transform;

import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.services.ec2.model.ConfirmProductInstanceResult;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

public class ConfirmProductInstanceResultStaxUnmarshaller implements Unmarshaller<ConfirmProductInstanceResult, StaxUnmarshallerContext> {
    private static ConfirmProductInstanceResultStaxUnmarshaller instance;

    public static ConfirmProductInstanceResultStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ConfirmProductInstanceResultStaxUnmarshaller();
        }
        return instance;
    }

    public ConfirmProductInstanceResult unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
        ConfirmProductInstanceResult confirmProductInstanceResult = new ConfirmProductInstanceResult();
        int currentDepth = staxUnmarshallerContext.getCurrentDepth();
        int i = currentDepth + 1;
        if (staxUnmarshallerContext.isStartOfDocument()) {
            i++;
        }
        while (true) {
            XMLEvent nextEvent = staxUnmarshallerContext.nextEvent();
            if (nextEvent.isEndDocument()) {
                return confirmProductInstanceResult;
            }
            if (nextEvent.isAttribute() || nextEvent.isStartElement()) {
                if (staxUnmarshallerContext.testExpression("ownerId", i)) {
                    confirmProductInstanceResult.setOwnerId(StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                }
            } else if (nextEvent.isEndElement() && staxUnmarshallerContext.getCurrentDepth() < currentDepth) {
                return confirmProductInstanceResult;
            }
        }
    }
}
