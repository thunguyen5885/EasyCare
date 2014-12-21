package vn.easycare.layers.ui.models;

import android.content.Context;

import vn.easycare.R;
import vn.easycare.layers.services.IWSResponse;
import vn.easycare.layers.services.IWebServiceAccess;
import vn.easycare.layers.services.IWebServiceModel;
import vn.easycare.layers.services.WSAccessFactory;
import vn.easycare.layers.services.WSDataSingleton;
import vn.easycare.layers.services.WSError;
import vn.easycare.layers.services.concretes.LoginWSAccess;
import vn.easycare.layers.services.models.AuthorizationWSModel;
import vn.easycare.layers.services.models.AuthorizationWSParamModel;
import vn.easycare.layers.ui.models.base.ILoginModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/7/14.
 */
public class LoginModel implements ILoginModel, IWSResponse{

    private Context mContext;
    private ILoginCallback mCallback;

    public  LoginModel(Context context){
        mContext = context;
    }
    @Override
    public void getLoginAuthentication(String email, String password) {
        try {
            IWebServiceAccess<AuthorizationWSModel,AuthorizationWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    LoginWSAccess.class,
                    mContext,
                    this,
                    new AuthorizationWSParamModel(email,password));
            WS.sendPostRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public String ValidateAccountInfo(String email, String password) {
        String messageError = "";

        if(email.matches(AppConstants.EMAIL_REGEX))
            messageError = mContext.getResources().getString(R.string.email_format_incorrect);
        if(password.length()<6){
            if(!messageError.isEmpty())
                messageError+= "\r\n"+mContext.getResources().getString(R.string.password_format_incorrect);
            else
                messageError = mContext.getResources().getString(R.string.password_format_incorrect);
        }

        return messageError;

    }

    @Override
    public void setLoginCallback(ILoginCallback callback) {
        this.mCallback = callback;
    }


    @Override
    public void onWSResponseOK(IWebServiceModel result) {
        AuthorizationWSModel model  = (AuthorizationWSModel) result;
        WSDataSingleton.getInstance(mContext).setSessionToken(model.getCurrentSessionToken());
        mCallback.onLoginOK();
    }

    @Override
    public void onWSResponseFailed(WSError error) {
        mCallback.onLoginFail(error.getErrorMessage());
    }
}
