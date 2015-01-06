package vn.easycare.layers.services.models;

import vn.easycare.layers.services.IWebServiceParamModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/20/14.
 */
public class AuthorizationWSParamModel implements IWebServiceParamModel {
    String username;
    String password;
    String deviceRegistrationId="";
    AppConstants.AUTHORIZATION_ACTION action;
    String token;

    public AuthorizationWSParamModel(String username, String password, String deviceRegistrationId, AppConstants.AUTHORIZATION_ACTION action, String token) {
        this.username = username;
        this.password = password;
        this.deviceRegistrationId = deviceRegistrationId;
        this.action = action;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceRegistrationId() {
        return deviceRegistrationId;
    }

    public void setDeviceRegistrationId(String deviceRegistrationId) {
        this.deviceRegistrationId = deviceRegistrationId;
    }

    public AppConstants.AUTHORIZATION_ACTION getAction() {
        return action;
    }

    public void setAction(AppConstants.AUTHORIZATION_ACTION action) {
        this.action = action;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
