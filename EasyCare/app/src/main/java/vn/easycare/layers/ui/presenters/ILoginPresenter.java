package vn.easycare.layers.ui.presenters;

import vn.easycare.layers.ui.presenters.base.IPresenter;

/**
 * Created by phannguyen on 12/7/14.
 */
public interface ILoginPresenter extends IPresenter {
    void DoAuthenticateUser(String email,String password);
}
