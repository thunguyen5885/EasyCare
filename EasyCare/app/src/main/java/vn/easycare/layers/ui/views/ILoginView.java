package vn.easycare.layers.ui.views;

import vn.easycare.layers.ui.views.base.IBaseView;

/**
 * Created by phannguyen on 12/7/14.
 */
public interface ILoginView extends IBaseView {
    void LoginOK(String message);
    void LoginFail(String message);

}
