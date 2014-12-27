package vn.easycare.layers.services.models.builders;

import org.json.JSONException;
import org.json.JSONObject;

import vn.easycare.layers.services.models.AuthorizationWSModel;

/**
 * Created by phannguyen on 12/21/14.
 */
public class AuthorizationWSBuilder {
    String currentSessionToken="";
    String userId="";
    String userEmail="";
    String userFullname="";
    String userAvatar="";
    String userAvatarThumb="";
    String errorMessageIfAny="";

    public AuthorizationWSBuilder() {

    }

    public AuthorizationWSBuilder(JSONObject resJson) throws JSONException {
        //parse json and set value for properties
    }

    public AuthorizationWSBuilder withSessionToken(String sessionToken) {
        this.currentSessionToken = sessionToken;
        return this;
    }

    public AuthorizationWSBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public AuthorizationWSBuilder withUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public AuthorizationWSBuilder withUserFullname(String userFullname) {
        this.userFullname = userFullname;
        return this;
    }

    public AuthorizationWSBuilder withUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
        return this;
    }

    public AuthorizationWSBuilder withUserAvatarThumb(String userAvatarThumb) {
        this.userAvatarThumb = userAvatarThumb;
        return this;
    }

    public AuthorizationWSBuilder withErrorMessageIfAny(String errorMessageIfAny) {
        this.errorMessageIfAny = errorMessageIfAny;
        return this;
    }

    public AuthorizationWSModel build(){
        return new AuthorizationWSModel(currentSessionToken, userId, userEmail, userFullname, userAvatar, userAvatarThumb, errorMessageIfAny);
    }
}
