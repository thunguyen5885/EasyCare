package vn.easycare.layers.ui.models;

import android.content.Context;

import java.util.List;

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
import vn.easycare.layers.ui.components.data.base.IBaseItemData;
import vn.easycare.layers.ui.models.base.IBaseModel;
import vn.easycare.layers.ui.models.base.ILoginModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/7/14.
 */
public class LoginModel implements ILoginModel, IWSResponse{

    private Context mContext;
    private IResponseUIDataCallback mCallback;

    public  LoginModel(Context context,IResponseUIDataCallback callback){
        mContext = context;
        this.mCallback = callback;
    }
    @Override
    public void getLoginAuthentication(String email, String password) {
        try {
            IWebServiceAccess<AuthorizationWSModel,AuthorizationWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    LoginWSAccess.class,
                    mContext,
                    this,
                    new AuthorizationWSParamModel(email,password));
            WS.sendRequest();
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
    public void setResponseCallback(IResponseUIDataCallback callback) {
        this.mCallback = callback;
    }


    @Override
    public void onWSResponseOK(IWebServiceModel result) {
        AuthorizationWSModel model  = (AuthorizationWSModel) result;
        if(model.getErrorMessageIfAny()!=null && !model.getErrorMessageIfAny().isEmpty()){
            mCallback.onResponseFail(model.getErrorMessageIfAny(),mContext.getResources().getString(R.string.title_login));
        }else{
            WSDataSingleton.getInstance(mContext).setSessionToken(model.getCurrentSessionToken());
            WSDataSingleton.getInstance(mContext).setDoctorAvatar(model.getUserAvatar());
            WSDataSingleton.getInstance(mContext).setDoctorAvatarThumb(model.getUserAvatarThumb());
            WSDataSingleton.getInstance(mContext).setDoctorEmail(model.getUserEmail());
            WSDataSingleton.getInstance(mContext).setDoctorFullName(model.getUserFullname());
            WSDataSingleton.getInstance(mContext).setDoctorId(model.getUserId());
            IBaseItemData item = null;
            mCallback.onResponseOK(item);
        }

    }

    @Override
    public void onWSResponseFailed(WSError error) {
        mCallback.onResponseFail(error.getErrorMessage(),error.getFunctionTitle());
    }
}
