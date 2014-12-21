package vn.easycare.layers.services;

/**
 * Created by phannguyen on 12/21/14.
 */
public interface IWSResponse {
    void onWSResponseOK(IWebServiceModel result);
    void onWSResponseFailed(WSError error);
}
