package vn.easycare.layers.services.models;


import vn.easycare.layers.services.IWebServiceModel;

/**
 * Created by phan on 12/9/2014.
 */
public class AuthorizationWSModel implements IWebServiceModel {
    String currentSessionToken;
    long currentSessionTokenExpireAt;

    public AuthorizationWSModel(String currentSessionToken, long currentSessionTokenExpireAt) {
        this.currentSessionToken = currentSessionToken;
        this.currentSessionTokenExpireAt = currentSessionTokenExpireAt;
    }

    public String getCurrentSessionToken() {
        return currentSessionToken;
    }

    public void setCurrentSessionToken(String currentSessionToken) {
        this.currentSessionToken = currentSessionToken;
    }

    public long getCurrentSessionTokenExpireAt() {
        return currentSessionTokenExpireAt;
    }

    public void setCurrentSessionTokenExpireAt(long currentSessionTokenExpireAt) {
        this.currentSessionTokenExpireAt = currentSessionTokenExpireAt;
    }
}
