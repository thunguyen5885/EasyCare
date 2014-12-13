package vn.easycare.layers.ui.models;

import android.content.Context;

import vn.easycare.layers.ui.models.base.ILoginModel;

/**
 * Created by phannguyen on 12/7/14.
 */
public class LoginModel implements ILoginModel {

    private Context mContext;
    public  LoginModel(Context context){
        mContext = context;
    }
    @Override
    public boolean getLoginAuthentication(String email, String password) {
        return true;
    }

    @Override
    public String ValidateAccountInfo(String email, String password) {
        return null;
    }
}
