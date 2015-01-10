package vn.easycare.layers.services;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import vn.easycare.layers.ui.components.data.DoctorInfoAccItemData;

/**
 * Created by phannguyen on 12/21/14.
 */
public class WSDataSingleton {
    private static WSDataSingleton instance;
    private static Object lock = new Object();
    private Context mContext;
    private RequestQueue queue;
    DoctorInfoAccItemData mDoctorInfo;

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
        mDoctorInfo = new DoctorInfoAccItemData();
    }

    public RequestQueue getRequestQueue(){
        return queue;
    }

    public String getSessionToken() {
        return mDoctorInfo.getSessionToken();
    }

    public void setSessionToken(String sessionToken) {
        mDoctorInfo.setSessionToken(sessionToken);
    }

    public String getDoctorFullName() {
        return mDoctorInfo.getDoctorFullName();
    }

    public void setDoctorFullName(String doctorFullName) {
        mDoctorInfo.setDoctorFullName(doctorFullName);
    }

    public String getDoctorAvatar() {
        return mDoctorInfo.getDoctorAvatar();
    }

    public void setDoctorAvatar(String doctorAvatar) {
        mDoctorInfo.setDoctorAvatar(doctorAvatar);
    }

    public String getDoctorId() {
        return mDoctorInfo.getDoctorId();
    }

    public void setDoctorId(String doctorId) {
        mDoctorInfo.setDoctorId(doctorId);
    }

    public String getDoctorEmail() {
        return mDoctorInfo.getDoctorEmail();
    }

    public void setDoctorEmail(String doctorEmail) {
        mDoctorInfo.setDoctorEmail(doctorEmail);
    }

    public String getDoctorAvatarThumb() {
        return mDoctorInfo.getDoctorAvatarThumb();
    }

    public void setDoctorAvatarThumb(String doctorAvatarThumb) {
        mDoctorInfo.setDoctorAvatarThumb(doctorAvatarThumb);
    }

    public void setDoctorInfo(String doctorInfoJson){
        this.mDoctorInfo.initWithJsonString(doctorInfoJson);
    }
    public DoctorInfoAccItemData getDoctorInfo(){
        return mDoctorInfo;
    }

}
