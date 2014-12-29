package vn.easycare.layers.services;

/**
 * Created by phan on 12/9/2014.
 */
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import vn.easycare.utils.AppConstants;


public abstract class AbstractWSAccess<T extends IWebServiceModel, P extends IWebServiceParamModel> implements IWebServiceAccess {

    protected  Context mContext;
    protected  IWSResponse mCallback;
    private static final String Res_Errors = "errors";
    public void setContext(Context context){
        this.mContext = context;
    }

    public void setResponseCallback(IWSResponse callback){
        this.mCallback = callback;
    }

    @Override
    public void onResponseFailed(VolleyError error) {
        NetworkResponse response = error.networkResponse;
        String errMessage = "";
        if(response != null && response.data != null) {
            String errorResponse = new String(response.data);
            try {
                JSONObject jsonObj = new JSONObject(errorResponse);
                JSONObject jsonErrorObj = (JSONObject)jsonObj.get(Res_Errors);
                if(jsonErrorObj!=null){
                    Iterator<?> keys = jsonErrorObj.keys();
                    while( keys.hasNext() ){
                        String key = (String)keys.next();
                        errMessage+=jsonErrorObj.getString(key)+"\r\n";
                    }
                }
            } catch (JSONException e) {
                errMessage = errorResponse;

            }

        }
        else{
            errMessage = error.getMessage();
        }
        WSError errorObj = new WSError(errMessage);
        if(mCallback!=null)
            mCallback.onWSResponseFailed(errorObj);
    }

    @Override
    public Map<String, String> getWSHeaders() {

        Map<String,String> params = new HashMap<String, String>();
        params.put("Content-Type","application/x-www-form-urlencoded");
        return params;
    }

    @Override
    public void sendRequest()
    {
        switch (getMethod()){
            case Request.Method.GET:
                sendGetRequest();
                break;
            case Request.Method.POST:
                sendPostRequest();
                break;
            case Request.Method.DELETE:
                sendDeleteRequest();
                break;
           default:
               break;

        }
    }
    private void sendGetRequest(){
        StringRequest sr = new StringRequest(Request.Method.GET, getWSURL(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onParseJsonResponseOK(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onResponseFailed(error);
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                return  getWSParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return  getWSHeaders();
            }
        };
        WSDataSingleton.getInstance(mContext).getRequestQueue().add(sr);
    }


    private void sendPostRequest(){

       StringRequest sr = new StringRequest(Request.Method.POST, getWSURL(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onParseJsonResponseOK(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onResponseFailed(error);
            }
        }){
            @Override
            protected Map<String,String> getParams(){
              return  getWSParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
              return  getWSHeaders();
            }
        };
        WSDataSingleton.getInstance(mContext).getRequestQueue().add(sr);
    }

    private void sendDeleteRequest(){

        StringRequest sr = new StringRequest(Request.Method.DELETE, getWSURL(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onParseJsonResponseOK(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onResponseFailed(error);
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                return  getWSParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return  getWSHeaders();
            }
        };
        WSDataSingleton.getInstance(mContext).getRequestQueue().add(sr);
    }

}

