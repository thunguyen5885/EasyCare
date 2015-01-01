package vn.easycare.layers.services.concretes;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import vn.easycare.layers.services.AbstractWSAccess;
import vn.easycare.layers.services.IWebServiceModel;
import vn.easycare.layers.services.IWebServiceParamModel;
import vn.easycare.layers.services.WSError;
import vn.easycare.layers.services.models.AuthorizationWSModel;
import vn.easycare.layers.services.models.AuthorizationWSParamModel;
import vn.easycare.layers.services.models.builders.AuthorizationWSBuilder;

/**
 * Created by phan on 12/9/2014.
 */
public class LoginWSAccess extends AbstractWSAccess<AuthorizationWSModel,AuthorizationWSParamModel> {
    private static final String LOGIN_URI = WEBSERVICE_HOST + "/users/login?email=%s&password=%s";
    private static final String Param_Username = "email";
    private static final String Param_Password = "password";
    private static final String Res_Id = "id";
    private static final String Res_email = "email";
    private static final String Res_full_name = "full_name";
    private static final String Res_avatar = "avatar";
    private static final String Res_avatar_thumb = "avatar_thumb";
    private static final String Res_Token = "token";
    private static final String Res_User = "user";
    private static final String Res_Errors = "errors";

    private AuthorizationWSParamModel mParam;
    public  LoginWSAccess(){

    }

    @Override
    public void onParseJsonResponseOK(String jsonResponse) {
        try {
            AuthorizationWSBuilder modelBuilder = new  AuthorizationWSBuilder();
            JSONObject jsonBigObj = new JSONObject(jsonResponse);

            modelBuilder.withSessionToken(jsonBigObj.optString(Res_Token,""));
            JSONObject jsonUserObj = (JSONObject)jsonBigObj.get(Res_User);
            if(jsonUserObj!=null){
                modelBuilder.withUserAvatar(jsonUserObj.optString(Res_avatar,""));
                modelBuilder.withUserAvatarThumb(jsonUserObj.optString(Res_avatar_thumb,""));
                modelBuilder.withUserEmail(jsonUserObj.optString(Res_email,""));
                modelBuilder.withUserFullname(jsonUserObj.optString(Res_full_name,""));
                modelBuilder.withUserId(jsonUserObj.optString(Res_Id,""));
            }

            if(mCallback!=null)
                mCallback.onWSResponseOK(modelBuilder.build());
        } catch (JSONException e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage()));
        }
        catch (Exception e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage()));
        }

        //mCallback.onWSResponseOK();
    }


    @Override
    public int getMethod() {
        return Request.Method.GET;
    }

    @Override
    public String getWSURL() {

        return String.format(LOGIN_URI,mParam.getUsername(),mParam.getPassword());
    }

    @Override
    public Map<String,String> getWSParams() {

        return null;
    }

    @Override
    public void setWSParams(IWebServiceParamModel params) {
        mParam = (AuthorizationWSParamModel)params;
    }
}
