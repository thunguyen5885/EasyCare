package vn.easycare.layers.services.models.builders;

import org.json.JSONException;
import org.json.JSONObject;

import vn.easycare.layers.services.models.ClinicAddressWSModel;

/**
 * Created by phan on 12/30/2014.
 */
public class ClinicAddressWSBuilder {
    String clinicAddress="";
    String clinicAddressId="";
    public ClinicAddressWSBuilder() {

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
