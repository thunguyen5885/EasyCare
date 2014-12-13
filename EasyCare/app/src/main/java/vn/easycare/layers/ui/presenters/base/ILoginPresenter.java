package vn.easycare.layers.ui.presenters.base;

import vn.easycare.layers.ui.presenters.base.IPresenter;
import vn.easycare.layers.ui.views.ILoginView;

/**
 * Created by phannguyen on 12/7/14.
 */
public interface ILoginPresenter extends IPresenter <ILoginView>{
    void DoAuthenticateUser(String email,String password);
}
