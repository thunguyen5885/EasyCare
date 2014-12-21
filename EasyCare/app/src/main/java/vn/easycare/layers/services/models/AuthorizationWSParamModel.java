package vn.easycare.layers.services.models;

import vn.easycare.layers.services.IWebServiceParamModel;

/**
 * Created by phannguyen on 12/20/14.
 */
public class AuthorizationWSParamModel implements IWebServiceParamModel {
    String username;
    String password;

    public AuthorizationWSParamModel(String username, String password) {
        this.username = username;
        this.password = password;
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
}
