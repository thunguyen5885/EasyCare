package vn.easycare.layers.services.models.builders;

import org.json.JSONException;
import org.json.JSONObject;

import vn.easycare.layers.services.models.AuthorizationWSModel;

/**
 * Created by phannguyen on 12/21/14.
 */
public class AuthorizationWSBuilder {
    String currentSessionToken = "";
    long currentSessionTokenExpireAt = 0;

    public AuthorizationWSBuilder() {
    }

    public AuthorizationWSBuilder(JSONObject productJson) throws JSONException {
        //parse json and set value for properties
    }
    public AuthorizationWSBuilder withSessionToken(String sessionToken) {
        this.currentSessionToken = sessionToken;
        return this;
    }
    public AuthorizationWSBuilder withSessionTokenExpire(long sessionTokenExpireAt) {
        this.currentSessionTokenExpireAt = sessionTokenExpireAt;
        return this;
    }

    public AuthorizationWSModel build(){
        return new AuthorizationWSModel(currentSessionToken, currentSessionTokenExpireAt);
    }
}
