package vn.easycare.layers.services.concretes;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

import vn.easycare.layers.services.AbstractWSAccess;
import vn.easycare.layers.services.IWebServiceParamModel;
import vn.easycare.layers.services.WSError;
import vn.easycare.layers.services.models.ClinicAddressListWSModel;
import vn.easycare.layers.services.models.ClinicAddressWSParamModel;
import vn.easycare.layers.services.models.builders.ClinicAddressWSBuilder;

/**
 * Created by phan on 12/30/2014.
 */
public class ClinicAddressWSAccess extends AbstractWSAccess<ClinicAddressListWSModel,ClinicAddressWSParamModel> {
    private static final String CLINIC_ADDRESS_URI = WEBSERVICE_HOST + "/doctors/addresses?token=%s";
    private static final String Res_addresses = "addresses";
    ClinicAddressWSParamModel mParam;
    @Override
    public String getWSURL() {
        return String.format(CLINIC_ADDRESS_URI,mParam.getToken());
    }

    @Override
    public Map<String, String> getWSParams() {
        return null;
    }

    @Override
    public void setWSParams(IWebServiceParamModel params) {
        mParam = (ClinicAddressWSParamModel) params;
    }

    @Override
    public void onParseJsonResponseOK(String jsonResponse) {
        try {
            ClinicAddressWSBuilder modelBuilder = new  ClinicAddressWSBuilder();
            ClinicAddressListWSModel listModel = new ClinicAddressListWSModel();
            JSONObject jsonBigObj = new JSONObject(jsonResponse);
            JSONObject addressesObj = (JSONObject)jsonBigObj.get(Res_addresses);
            if(addressesObj!=null) {
                Iterator<?> keys = addressesObj.keys();
                while( keys.hasNext() ){
                    String key = (String)keys.next();
                    modelBuilder.Clear();
                    modelBuilder.withClinicAddress(addressesObj.get(key).toString());
                    modelBuilder.withClinicAddressId(key);
                    listModel.getClinicAddressesList().add(modelBuilder.build());
                }
            }
            if(mCallback!=null)
                mCallback.onWSResponseOK(listModel);
        } catch (JSONException e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage()));
        }
        catch (Exception e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage()));
        }
    }

    @Override
    public int getMethod() {
        return Request.Method.GET;
    }
}
