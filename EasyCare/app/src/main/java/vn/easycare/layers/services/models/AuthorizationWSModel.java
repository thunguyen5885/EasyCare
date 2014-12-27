package vn.easycare.layers.services.models;


import vn.easycare.layers.services.IWebServiceModel;

/**
 * Created by phan on 12/9/2014.
 */
public class AuthorizationWSModel implements IWebServiceModel {
    String currentSessionToken;
    String userId;
    String userEmail;
    String userFullname;
    String userAvatar;
    String userAvatarThumb;
    String errorMessageIfAny;

    public AuthorizationWSModel(String currentSessionToken, String userId, String userEmail, String userFullname, String userAvatar, String userAvatarThumb, String errorMessageIfAny) {
        this.currentSessionToken = currentSessionToken;
        this.userId = userId;
        this.userEmail = userEmail;
        this.userFullname = userFullname;
        this.userAvatar = userAvatar;
        this.userAvatarThumb = userAvatarThumb;
        this.errorMessageIfAny = errorMessageIfAny;
    }

    public String getCurrentSessionToken() {
        return currentSessionToken;
    }

    public void setCurrentSessionToken(String currentSessionToken) {
        this.currentSessionToken = currentSessionToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserAvatarThumb() {
        return userAvatarThumb;
    }

    public void setUserAvatarThumb(String userAvatarThumb) {
        this.userAvatarThumb = userAvatarThumb;
    }

    public String getErrorMessageIfAny() {
        return errorMessageIfAny;
    }

    public void setErrorMessageIfAny(String errorMessageIfAny) {
        this.errorMessageIfAny = errorMessageIfAny;
    }
}
