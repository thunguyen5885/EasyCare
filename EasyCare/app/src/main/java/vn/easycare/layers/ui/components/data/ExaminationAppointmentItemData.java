package vn.easycare.layers.ui.components.data;

import android.graphics.Bitmap;

import java.util.Date;

import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/13/14.
 */
public class ExaminationAppointmentItemData {
    String ExaminationId;
    String PatientAvatar;
    String PatientName;
    Date ExaminationDateTime;
    String ExaminationReasion;
    AppConstants.EXAMINATION_STATUS ExaminationStatus;

    public ExaminationAppointmentItemData() {
    }

    public ExaminationAppointmentItemData(String examinationId, String patientAvatar, String patientName, Date examinationDateTime, String examinationReasion, AppConstants.EXAMINATION_STATUS examinationStatus) {
        ExaminationId = examinationId;
        PatientAvatar = patientAvatar;
        PatientName = patientName;
        ExaminationDateTime = examinationDateTime;
        ExaminationReasion = examinationReasion;
        ExaminationStatus = examinationStatus;
    }
    public String getExaminationId(){return ExaminationId;}
    public void setExaminationId(String examinationId){ExaminationId = examinationId;}
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
