package vn.easycare.layers.services.concretes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
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
    private static final String LOGIN_URI = WEBSERVICE_HOST + "/login";
    private static final String Param_Username = "email";
    private static final String Param_Password = "password";
    private static final String Param_Token = "token";

    private AuthorizationWSParamModel mParam;
    public  LoginWSAccess(){

    }

    @Override
    public void onParseJsonResponseOK(String jsonResponse) {
        try {
            AuthorizationWSBuilder modelBuilder = new  AuthorizationWSBuilder();
            JSONObject jsonObj = new JSONObject(jsonResponse);
            modelBuilder.withSessionToken(jsonObj.get("token").toString());
            modelBuilder.withSessionTokenExpire(Long.valueOf(jsonObj.get("token_expire").toString()).longValue());
            if(mCallback!=null)
                mCallback.onWSResponseOK(modelBuilder.build());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //mCallback.onWSResponseOK();
    }





    @Override
    public String getWSURL() {
        return LOGIN_URI;
    }

    @Override
    public Map<String,String> getWSParams() {

        Map<String,String> params = new HashMap<String, String>();
        if(mParam!=null) {
            params.put(Param_Username,mParam.getUsername());
            params.put(Param_Password,mParam.getPassword());
        }
        return params;
    }

    @Override
    public void setWSParams(IWebServiceParamModel params) {
        mParam = (AuthorizationWSParamModel)params;
    }
}
