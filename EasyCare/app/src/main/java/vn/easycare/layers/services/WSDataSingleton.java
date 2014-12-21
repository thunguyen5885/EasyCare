package vn.easycare.layers.services;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by phannguyen on 12/21/14.
 */
public class WSDataSingleton {
    private static WSDataSingleton instance;
    private static Object lock = new Object();
    private Context mContext;
    private RequestQueue queue;
    private String sessionToken;
    public static WSDataSingleton getInstance(Context context) {
        synchronized (lock) {
            if (instance == null) {
                instance = new WSDataSingleton(context);
            }
        }

        return instance;
    }

    public WSDataSingleton(Context context) {
        this.mContext = mContext;
        queue = Volley.newRequestQueue(context);
    }

    public RequestQueue getRequestQueue(){
        return queue;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
