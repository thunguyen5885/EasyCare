package vn.easycare.layers.ui.presenters;

import android.content.Context;

import vn.easycare.R;
import vn.easycare.layers.ui.models.ILoginModel;
import vn.easycare.layers.ui.models.LoginModel;
import vn.easycare.layers.ui.views.ILoginView;

/**
 * Created by phannguyen on 12/7/14.
 */
public class LoginPresenterImpl implements ILoginPresenter {
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
        String validAccInfo = iModel.ValidateAccountInfo(email,password);
        if(validAccInfo!=null && !validAccInfo.isEmpty()){
            boolean isLogin = iModel.getLoginAuthentication(email,password);
            if(isLogin)
                iView.LoginOK("");
            else
                iView.LoginFail(mContext.getResources().getString(R.string.login_fail_msg));
        }else{
            iView.ShowIncorrectAccountInfoMessage(validAccInfo);
        }
    }

    @Override
    public void init(Object view) {

    }
}
