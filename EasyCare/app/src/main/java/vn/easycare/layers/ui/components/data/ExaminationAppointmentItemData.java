package vn.easycare.layers.ui.components.data;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;

import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/13/14.
 */
public class ExaminationAppointmentItemData implements Serializable{
    String ExaminationId;
    String PatientAvatar;
    String PatientName;
    String PatientGender;
    String PatientPhone;
    String PatientEmail;

    String ExaminationDateTime;
    String ExaminationReason;
    String ExaminationCode;
    String ExaminationAddress;
    String ExaminationByPerson;

    String ExaminationDateTimeAppointmentCreated;
    String ExaminationState;
    String ExaminationExtraInfo;
    String ExaminationDoctorNote;

    boolean ExaminationFirstVisit;

    AppConstants.EXAMINATION_STATUS ExaminationStatus;

    public ExaminationAppointmentItemData() {
    }

    public ExaminationAppointmentItemData(String examinationId, String patientAvatar, String patientName, String examinationDateTime, String examinationReasion, AppConstants.EXAMINATION_STATUS examinationStatus) {
        ExaminationId = examinationId;
        PatientAvatar = patientAvatar;
        PatientName = patientName;
        ExaminationDateTime = examinationDateTime;
        ExaminationReason = examinationReasion;
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

    public String getExaminationDateTime() {
        return ExaminationDateTime;
    }

    public void setExaminationDateTime(String examinationDateTime) {
        ExaminationDateTime = examinationDateTime;
    }

    public String getExaminationReason() {
        return ExaminationReason;
    }

    public void setExaminationReason(String examinationReason) {
        ExaminationReason = examinationReason;
    }

    public AppConstants.EXAMINATION_STATUS getExaminationStatus() {
        return ExaminationStatus;
    }

    public void setExaminationStatus(AppConstants.EXAMINATION_STATUS examinationStatus) {
        ExaminationStatus = examinationStatus;
    }

    public String getPatientGender() {
        return PatientGender;
    }

    public void setPatientGender(String patientGender) {
        PatientGender = patientGender;
    }

    public String getPatientPhone() {
        return PatientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        PatientPhone = patientPhone;
    }

    public String getPatientEmail() {
        return PatientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        PatientEmail = patientEmail;
    }

    public String getExaminationCode() {
        return ExaminationCode;
    }

    public void setExaminationCode(String examinationCode) {
        ExaminationCode = examinationCode;
    }

    public String getExaminationAddress() {
        return ExaminationAddress;
    }

    public void setExaminationAddress(String examinationAddress) {
        ExaminationAddress = examinationAddress;
    }

    public String getExaminationByPerson() {
        return ExaminationByPerson;
    }

    public void setExaminationByPerson(String examinationByPerson) {
        ExaminationByPerson = examinationByPerson;
    }

    public String getExaminationDateTimeAppointmentCreated() {
        return ExaminationDateTimeAppointmentCreated;
    }

    public void setExaminationDateTimeAppointmentCreated(String examinationDateTimeAppointmentCreated) {
        ExaminationDateTimeAppointmentCreated = examinationDateTimeAppointmentCreated;
    }

    public String getExaminationState() {
        return ExaminationState;
    }

    public void setExaminationState(String examinationState) {
        ExaminationState = examinationState;
    }

    public String getExaminationExtraInfo() {
        return ExaminationExtraInfo;
    }

    public void setExaminationExtraInfo(String examinationExtraInfo) {
        ExaminationExtraInfo = examinationExtraInfo;
    }

    public String getExaminationDoctorNote() {
        return ExaminationDoctorNote;
    }

    public void setExaminationDoctorNote(String examinationDoctorNote) {
        ExaminationDoctorNote = examinationDoctorNote;
    }

    public boolean isExaminationFirstVisit() {
        return ExaminationFirstVisit;
    }

    public void setExaminationFirstVisit(boolean examinationFirstVisit) {
        ExaminationFirstVisit = examinationFirstVisit;
    }
}
