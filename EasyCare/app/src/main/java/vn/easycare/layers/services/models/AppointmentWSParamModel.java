package vn.easycare.layers.services.models;

import vn.easycare.layers.services.IWebServiceParamModel;

/**
 * Created by phan on 12/26/2014.
 */
public class AppointmentWSParamModel implements IWebServiceParamModel{
    String token;
    String page;
    String appointmentCode;
    String appointmentStatus;
    String patientName;
    String appointmentDate;
    String startDate;
    String endDate;
    String patientId;
    String numberOfRecords;

    public AppointmentWSParamModel(String token, String page, String appointmentCode, String appointmentStatus, String patientName, String appointmentDate, String startDate, String endDate, String patientId, String numberOfRecords) {
        this.token = token;
        this.page = page;
        this.appointmentCode = appointmentCode;
        this.appointmentStatus = appointmentStatus;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.patientId = patientId;
        this.numberOfRecords = numberOfRecords;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getAppointmentCode() {
        return appointmentCode;
    }

    public void setAppointmentCode(String appointmentCode) {
        this.appointmentCode = appointmentCode;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getNumberOfRecords() {
        return numberOfRecords;
    }

    public void setNumberOfRecords(String numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }
}
