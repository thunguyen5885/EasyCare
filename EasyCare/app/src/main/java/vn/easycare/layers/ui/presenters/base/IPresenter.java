package vn.easycare.layers.ui.presenters.base;

import vn.easycare.layers.ui.views.base.IBaseView;

/**
 * Created by phannguyen on 12/7/14.
 */
public interface IPresenter<T extends IBaseView> {
    void init(T view);
}
