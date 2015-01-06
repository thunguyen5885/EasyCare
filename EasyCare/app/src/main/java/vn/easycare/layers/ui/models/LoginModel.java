package vn.easycare.layers.ui.models;

import android.content.Context;

import vn.easycare.R;
import vn.easycare.layers.services.IWSResponse;
import vn.easycare.layers.services.IWebServiceAccess;
import vn.easycare.layers.services.IWebServiceModel;
import vn.easycare.layers.services.WSAccessFactory;
import vn.easycare.layers.services.WSDataSingleton;
import vn.easycare.layers.services.WSError;
import vn.easycare.layers.services.concretes.AuthorizationWSAccess;
import vn.easycare.layers.services.models.AuthorizationWSModel;
import vn.easycare.layers.services.models.AuthorizationWSParamModel;
import vn.easycare.layers.ui.components.data.GCMItemData;
import vn.easycare.layers.ui.components.data.LoginItemData;
import vn.easycare.layers.ui.components.data.base.IBaseItemData;
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
                    AuthorizationWSAccess.class,
                    mContext,
                    this,
                    new AuthorizationWSParamModel(email,password,"", AppConstants.AUTHORIZATION_ACTION.LOGIN,""));
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
    public void registerDeviceIdGCM(String gcmDeviceid) {
        try {
            IWebServiceAccess<AuthorizationWSModel,AuthorizationWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    AuthorizationWSAccess.class,
                    mContext,
                    this,
                    new AuthorizationWSParamModel("","",gcmDeviceid, AppConstants.AUTHORIZATION_ACTION.REGISTER_DEVICE_ID,WSDataSingleton.getInstance(mContext).getSessionToken()));
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
    public void setResponseCallback(IResponseUIDataCallback callback) {
        this.mCallback = callback;
    }


    @Override
    public void onWSResponseOK(IWebServiceModel result) {
        AuthorizationWSModel model  = (AuthorizationWSModel) result;
        if(model.getErrorMessageIfAny()!=null && !model.getErrorMessageIfAny().isEmpty()){
            mCallback.onResponseFail(model.getErrorMessageIfAny(),mContext.getResources().getString(R.string.title_login));
        }else{
            if(model.getAction()== AppConstants.AUTHORIZATION_ACTION.LOGIN) {
                WSDataSingleton.getInstance(mContext).setSessionToken(model.getCurrentSessionToken());
                WSDataSingleton.getInstance(mContext).setDoctorAvatar(model.getUserAvatar());
                WSDataSingleton.getInstance(mContext).setDoctorAvatarThumb(model.getUserAvatarThumb());
                WSDataSingleton.getInstance(mContext).setDoctorEmail(model.getUserEmail());
                WSDataSingleton.getInstance(mContext).setDoctorFullName(model.getUserFullname());
                WSDataSingleton.getInstance(mContext).setDoctorId(model.getUserId());
                LoginItemData item = new LoginItemData();
                mCallback.onResponseOK(item,LoginItemData.class);
            }else if(model.getAction()== AppConstants.AUTHORIZATION_ACTION.REGISTER_DEVICE_ID){
                GCMItemData item = new GCMItemData();
                mCallback.onResponseOK(item,GCMItemData.class);
            }
        }

    }

    @Override
    public void onWSResponseFailed(WSError error) {
        if(error.getStatusCode() == AppConstants.HTTP_STATUS_UNAUTHORIZED)
            mCallback.onUnauthorized();
        else
            mCallback.onResponseFail(error.getErrorMessage(),error.getFunctionTitle());
    }
}
