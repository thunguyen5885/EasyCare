package vn.easycare.layers.ui.models.base;

import vn.easycare.layers.ui.models.base.IBaseModel;

/**
 * Created by phannguyen on 12/7/14.
 */
public interface ILoginModel extends IBaseModel {
    void getLoginAuthentication(String email,String password);
    String ValidateAccountInfo(String email,String password);
    void registerDeviceIdGCM(String gcmDeviceid);

}
