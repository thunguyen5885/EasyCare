package vn.easycare.layers.services;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by phan on 12/9/2014.
 */
public class WSAccessFactory {
    private static WSAccessFactory instance;
    private static Object lock = new Object();

    public static WSAccessFactory getInstance() {
        synchronized (lock) {
            if (instance == null) {
                instance = new WSAccessFactory();
            }
        }

        return instance;
    }

    public <T extends IWebServiceAccess<IWebServiceModel,IWebServiceParamModel>> T getWebServiceAccess(Class<T> clazz,
                                                                                                       Context context,
                                                                                                       IWSResponse callback,
                                                                                                       IWebServiceParamModel param)
            throws InstantiationException, IllegalAccessException {
        T wsObj = clazz.newInstance();
        wsObj.setContext(context);
        wsObj.setResponseCallback(callback);
        wsObj.setWSParams(param);
        return wsObj;
    }

}

