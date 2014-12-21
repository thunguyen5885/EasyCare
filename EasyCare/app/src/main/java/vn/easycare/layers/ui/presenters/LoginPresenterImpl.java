package vn.easycare.layers.ui.presenters;

import android.content.Context;

import vn.easycare.R;
import vn.easycare.layers.services.IWSResponse;
import vn.easycare.layers.services.IWebServiceModel;
import vn.easycare.layers.services.WSError;
import vn.easycare.layers.ui.models.base.ILoginModel;
import vn.easycare.layers.ui.models.LoginModel;
import vn.easycare.layers.ui.presenters.base.ILoginPresenter;
import vn.easycare.layers.ui.views.ILoginView;

/**
 * Created by phannguyen on 12/7/14.
 */
public class LoginPresenterImpl  implements ILoginPresenter, ILoginModel.ILoginCallback{
    ILoginView iView;
    ILoginModel iModel;
    Context mContext;
    public LoginPresenterImpl(ILoginView loginView, Context context){
        iView = loginView;
        iModel = new LoginModel(context);
        mContext = context;
    }
    @Override
    public void DoAuthenticateUser(String email, String password) {
        String validAccInfo = iModel.ValidateAccountInfo(email, password);
        if (validAccInfo != null && !validAccInfo.isEmpty()) {
            iModel.getLoginAuthentication(email, password);
        } else {
            iView.ShowIncorrectAccountInfoMessage(validAccInfo);
        }
    }

    @Override
    public void init(ILoginView view) {

    }


    @Override
    public void onLoginOK() {
        iView.LoginOK("");
    }

    @Override
    public void onLoginFail(String message) {
        iView.LoginFail(mContext.getResources().getString(R.string.login_fail_msg));
    }
}
