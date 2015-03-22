package vn.easycare.layers.services.models.builders;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import vn.easycare.layers.services.models.ClinicAddressWSModel;

/**
 * Created by phan on 12/30/2014.
 */
public class ClinicAddressWSBuilder {
    String clinicAddress="";
    String clinicAddressId="";
    Context mContext;
    public ClinicAddressWSBuilder() {

    }
    public ClinicAddressWSBuilder(Context context) {
        mContext = context;
    }
    public ClinicAddressWSBuilder(JSONObject resJson) throws JSONException {
        //parse json and set value for properties
    }

    public ClinicAddressWSBuilder withClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
        return this;
    }

    public ClinicAddressWSBuilder withClinicAddressId(String clinicAddressId) {
        this.clinicAddressId = clinicAddressId;
        return this;
    }

    public ClinicAddressWSModel build(){
        return new ClinicAddressWSModel(clinicAddress,clinicAddressId);
    }
    public void Clear(){
        clinicAddress="";
        clinicAddressId="";
    }
}
