package vn.easycare.layers.services.models;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.layers.services.IWebServiceModel;

/**
 * Created by phan on 12/30/2014.
 */
public class ClinicAddressListWSModel implements IWebServiceModel{
    List<ClinicAddressWSModel> clinicAddressesList;

    public ClinicAddressListWSModel() {
        clinicAddressesList = new ArrayList<ClinicAddressWSModel>();
    }

    public ClinicAddressListWSModel(List<ClinicAddressWSModel> clinicAddressesList) {
        this.clinicAddressesList = clinicAddressesList;
    }

    public List<ClinicAddressWSModel> getClinicAddressesList() {
        return clinicAddressesList;
    }

    public void setClinicAddressesList(List<ClinicAddressWSModel> clinicAddressesList) {
        this.clinicAddressesList = clinicAddressesList;
    }
}
