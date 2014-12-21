package vn.easycare.layers.services;

/**
 * Created by phan on 12/9/2014.
 */
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import vn.easycare.utils.AppConstants;


public abstract class AbstractWSAccess<T extends IWebServiceModel, P extends IWebServiceParamModel> implements IWebServiceAccess {

    protected  Context mContext;
    protected  IWSResponse mCallback;

    public void setContext(Context context){
        this.mContext = context;
    }

    public void setResponseCallback(IWSResponse callback){
        this.mCallback = callback;
    }

    public void sendGetRequest(){
        StringRequest sr = new StringRequest(Request.Method.GET, getWSURL(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onParseJsonResponseOK(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onResponseFailed(error.getMessage());
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

    public void sendPostRequest(){

       StringRequest sr = new StringRequest(Request.Method.POST, getWSURL(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onParseJsonResponseOK(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onResponseFailed(error.getMessage());
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

