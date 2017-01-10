package com.amazonaws;

import com.amazonaws.http.HttpMethodName;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;

public interface Request<T> {
    void addHeader(String str, String str2);

    void addParameter(String str, String str2);

    InputStream getContent();

    URI getEndpoint();

    Map<String, String> getHeaders();

    HttpMethodName getHttpMethod();

    AmazonWebServiceRequest getOriginalRequest();

    Map<String, String> getParameters();

    String getResourcePath();

    String getServiceName();

    void setContent(InputStream inputStream);

    void setEndpoint(URI uri);

    void setHeaders(Map<String, String> map);

    void setHttpMethod(HttpMethodName httpMethodName);

    void setParameters(Map<String, String> map);

    void setResourcePath(String str);

    Request<T> withParameter(String str, String str2);
}
