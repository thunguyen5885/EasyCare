package vn.easycare.layers.ui.components.data;

import android.graphics.Bitmap;

import java.util.Date;

import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/13/14.
 */
public class ExaminationAppointmentItemData {
    Bitmap PatientAvatar;
    String PatientName;
    Date ExaminationDateTime;
    String ExaminationReasion;
    AppConstants.EXAMINATION_STATUS ExaminationStatus;

    public ExaminationAppointmentItemData() {
    }

    public ExaminationAppointmentItemData(Bitmap patientAvatar, String patientName, Date examinationDateTime, String examinationReasion, AppConstants.EXAMINATION_STATUS examinationStatus) {
        PatientAvatar = patientAvatar;
        PatientName = patientName;
        ExaminationDateTime = examinationDateTime;
        ExaminationReasion = examinationReasion;
        ExaminationStatus = examinationStatus;
    }

    public Bitmap getPatientAvatar() {
        return PatientAvatar;
    }

    public void setPatientAvatar(Bitmap patientAvatar) {
        PatientAvatar = patientAvatar;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public Date getExaminationDateTime() {
        return ExaminationDateTime;
    }

    public void setExaminationDateTime(Date examinationDateTime) {
        ExaminationDateTime = examinationDateTime;
    }

    public String getExaminationReasion() {
        return ExaminationReasion;
    }

    public void setExaminationReasion(String examinationReasion) {
        ExaminationReasion = examinationReasion;
    }

    public AppConstants.EXAMINATION_STATUS getExaminationStatus() {
        return ExaminationStatus;
    }

    public void setExaminationStatus(AppConstants.EXAMINATION_STATUS examinationStatus) {
        ExaminationStatus = examinationStatus;
    }
}
