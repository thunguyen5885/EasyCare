package vn.easycare.layers.ui.components.data;

import vn.easycare.layers.ui.components.data.base.IBaseItemData;

/**
 * Created by phan on 12/24/2014.
 */
public class DoctorClinicAddressItemData implements IBaseItemData {
    String doctorId;
    String clinicAddress;
    String clinicAddressId;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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

    @Override
    public String toString() {
        return clinicAddress;
    }
}
