package vn.easycare.layers.services.models;

import vn.easycare.layers.services.IWebServiceParamModel;

/**
 * Created by phan on 12/30/2014.
 */
public class ClinicAddressWSParamModel implements IWebServiceParamModel{
    String token;

    public ClinicAddressWSParamModel(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
