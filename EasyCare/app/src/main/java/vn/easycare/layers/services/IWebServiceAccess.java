package vn.easycare.layers.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import java.util.List;
import java.util.Map;

/**
 * Created by phannguyen on 12/7/14.
 */

public interface IWebServiceAccess<T extends IWebServiceModel,P extends IWebServiceParamModel> {
    static final String WEBSERVICE_HOST = "http://edev.easycare.vn/api/v1";
    String getWSURL();
    Map<String,String> getWSParams();
    void setWSParams(IWebServiceParamModel params);
    void onParseJsonResponseOK(String jsonResponse);
    void onResponseFailed(VolleyError error);
    Map<String, String> getWSHeaders();
    void setContext(Context context);
    void setResponseCallback(IWSResponse callback);
    void sendRequest();
    int getMethod();
    String getRequestTitle();
}
