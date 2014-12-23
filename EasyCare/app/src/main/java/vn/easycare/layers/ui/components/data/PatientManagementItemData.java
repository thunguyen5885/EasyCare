package vn.easycare.layers.ui.components.data;

import android.graphics.Bitmap;

/**
 * Created by phan on 12/16/2014.
 */
public class PatientManagementItemData {
    String PatientId;
    String PatientAvatar;
    String PatientName;
    String PatientPhoneNumber;
    String PatientEmailAddress;
    boolean IsPatientBlocked;

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String patientId) {
        PatientId = patientId;
    }

    public String getPatientAvatar() {
        return PatientAvatar;
    }

    public void setPatientAvatar(String patientAvatar) {
        PatientAvatar = patientAvatar;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getPatientPhoneNumber() {
        return PatientPhoneNumber;
    }

    public void setPatientPhoneNumber(String patientPhoneNumber) {
        PatientPhoneNumber = patientPhoneNumber;
    }

    public String getPatientEmailAddress() {
        return PatientEmailAddress;
    }

    public void setPatientEmailAddress(String patientEmailAddress) {
        PatientEmailAddress = patientEmailAddress;
    }

    public boolean isPatientBlocked() {
        return IsPatientBlocked;
    }

    public void setPatientBlocked(boolean isPatientBlocked) {
        IsPatientBlocked = isPatientBlocked;
    }
}
