package vn.easycare.layers.services.models;

import vn.easycare.layers.services.IWebServiceModel;

/**
 * Created by phan on 12/30/2014.
 */
public class ClinicAddressWSModel implements IWebServiceModel{
    String clinicAddress;
    String clinicAddressId;

    public ClinicAddressWSModel(String clinicAddress, String clinicAddressId) {
        this.clinicAddress = clinicAddress;
        this.clinicAddressId = clinicAddressId;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    public String getClinicAddressId() {
        return clinicAddressId;
    }

    public void setClinicAddressId(String clinicAddressId) {
        this.clinicAddressId = clinicAddressId;
    }
}
