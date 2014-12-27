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
    private String sessionToken="";
    private String doctorFullName="";
    private String doctorAvatar="";
    private String doctorId="";
    private String doctorEmail="";
    private String doctorAvatarThumb="";
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

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    public String getDoctorAvatar() {
        return doctorAvatar;
    }

    public void setDoctorAvatar(String doctorAvatar) {
        this.doctorAvatar = doctorAvatar;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorAvatarThumb() {
        return doctorAvatarThumb;
    }

    public void setDoctorAvatarThumb(String doctorAvatarThumb) {
        this.doctorAvatarThumb = doctorAvatarThumb;
    }
}
