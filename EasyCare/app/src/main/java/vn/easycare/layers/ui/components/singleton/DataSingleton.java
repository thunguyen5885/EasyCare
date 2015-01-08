package vn.easycare.layers.ui.components.singleton;

import android.content.Context;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import vn.easycare.utils.ImageMemoryCache;

/**
 * Created by Thu on 10/25/2014.
 */
public class DataSingleton {
    private static DataSingleton mInstance;
    private ImageLoader mImageLoader;

    private DataSingleton(Context context){
        mImageLoader = new ImageLoader(Volley.newRequestQueue(context.getApplicationContext()), ImageMemoryCache.INSTANCE);
    }
    public static DataSingleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new DataSingleton(context);
        }
        return mInstance;
    }
    public ImageLoader getImageLoader(){
        return mImageLoader;
    }


}
