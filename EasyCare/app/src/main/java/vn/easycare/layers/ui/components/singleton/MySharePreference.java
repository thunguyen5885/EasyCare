package vn.easycare.layers.ui.components.singleton;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Thunguyen on 1/8/2015.
 */
public class MySharePreference {
    private static final String EASYCARE_PREFERENCE_KEY = "easycare_preference_key";
    private static final String DOCTOR_INFO_KEY = "doctor_info_key";

    public static void storeDoctorInfo(Context context, String doctorInfoJson){
        SharedPreferences.Editor editor = context.getSharedPreferences(
                EASYCARE_PREFERENCE_KEY, Context.MODE_PRIVATE).edit();
        editor.putString(DOCTOR_INFO_KEY, doctorInfoJson);
        editor.commit();

    }
    public static void clearDoctorInfo(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(
                EASYCARE_PREFERENCE_KEY, Context.MODE_PRIVATE).edit();
        editor.putString(DOCTOR_INFO_KEY, "");
        editor.commit();
    }
    public static String getDoctorInfo(Context context){
        SharedPreferences lSharePreference = context.getSharedPreferences(
                EASYCARE_PREFERENCE_KEY, Context.MODE_PRIVATE);
        String doctorInfoJson = lSharePreference.getString(
                DOCTOR_INFO_KEY, "");
        return doctorInfoJson;
    }
    public static boolean isLogin(Context context){
        String doctorInfoJson = getDoctorInfo(context);
        return (doctorInfoJson != null) && (doctorInfoJson.length() > 0);
    }
}
