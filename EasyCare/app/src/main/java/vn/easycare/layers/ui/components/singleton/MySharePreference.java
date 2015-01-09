package vn.easycare.layers.ui.components.singleton;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Thunguyen on 1/8/2015.
 */
public class MySharePreference {
    private static final String EASYCARE_PREFERENCE_KEY = "easycare_preference_key";
    private static final String ACCESS_TOKEN_KEY = "access_token_key";

    public static void storeAccessToken(Context context, String accessToken){
        SharedPreferences.Editor editor = context.getSharedPreferences(
                EASYCARE_PREFERENCE_KEY, Context.MODE_PRIVATE).edit();
        editor.putString(ACCESS_TOKEN_KEY, accessToken);
        editor.commit();

    }
    public static void clearAccessToken(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(
                EASYCARE_PREFERENCE_KEY, Context.MODE_PRIVATE).edit();
        editor.putString(ACCESS_TOKEN_KEY, "");
        editor.commit();
    }
    public static String getAccessToken(Context context){
        SharedPreferences lSharePreference = context.getSharedPreferences(
                EASYCARE_PREFERENCE_KEY, Context.MODE_PRIVATE);
        String accessToken = lSharePreference.getString(
                ACCESS_TOKEN_KEY, "");
        return accessToken;
    }
    public static boolean isLogin(Context context){
        String accessToken = getAccessToken(context);
        return (accessToken != null) && (accessToken.length() > 0);
    }
}
