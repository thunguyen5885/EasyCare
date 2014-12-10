package vn.easycare.layers.services.concretes;

import java.net.URI;

import vn.easycare.layers.services.AbstractWSAccess;
import vn.easycare.layers.services.WSRequest;
import vn.easycare.layers.services.models.AuthorizationWSModel;

/**
 * Created by phan on 12/9/2014.
 */
public class LoginWSAccess extends AbstractWSAccess<AuthorizationWSModel> {
    private static final URI LOGIN_URI = URI.create(WEBSERVICE_HOST + "/login");

    @Override
    protected WSRequest buildRequest() {
        return null;
    }

    @Override
    protected AuthorizationWSModel parseResponseBody(String responseBody) throws Exception {
        return null;
    }
}
