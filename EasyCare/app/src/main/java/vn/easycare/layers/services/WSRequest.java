package vn.easycare.layers.services;

/**
 * Created by phan on 12/9/2014.
 */


import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vn.easycare.utils.AppConstants.WSMethod;



public class WSRequest {
    private URI requestUri;
    private Map<String, List<String>> headers;
    private byte[] body;
    private WSMethod method;

    public WSRequest(WSMethod method, URI requestUri, Map<String, List<String>> headers,
                     byte[] body) {
        super();
        this.method = method;
        this.requestUri = requestUri;
        this.headers = headers;
        this.body = body;
    }

    public WSMethod getMethod() {
        return method;
    }

    public URI getRequestUri() {
        return requestUri;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public byte[] getBody() {
        return body;
    }

    public void addHeader(String key, List<String> value) {

        if (headers == null) {
            headers = new HashMap<String, List<String>>();
        }
        headers.put(key, value);
    }
}

