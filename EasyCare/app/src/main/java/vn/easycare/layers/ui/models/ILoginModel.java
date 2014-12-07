package vn.easycare.layers.ui.models;

import vn.easycare.layers.ui.models.base.IBaseModel;

/**
 * Created by phannguyen on 12/7/14.
 */
public interface ILoginModel extends IBaseModel {
    boolean getLoginAuthentication(String email,String password);
    String ValidateAccountInfo(String email,String password);
}
