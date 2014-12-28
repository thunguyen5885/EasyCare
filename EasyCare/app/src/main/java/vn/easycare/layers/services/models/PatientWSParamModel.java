package vn.easycare.layers.services.models;

import vn.easycare.layers.services.IWebServiceParamModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/28/14.
 */
public class PatientWSParamModel implements IWebServiceParamModel{
    String token;;
    String isbanned;
    String page;
    AppConstants.PATIENT_ACTION action;
    String patientId;

    public PatientWSParamModel(String token, String isbanned, String page, AppConstants.PATIENT_ACTION action, String patientId) {
        this.token = token;
        this.isbanned = isbanned;
        this.page = page;
        this.action = action;
        this.patientId = patientId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIsbanned() {
        return isbanned;
    }

    public void setIsbanned(String isbanned) {
        this.isbanned = isbanned;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public AppConstants.PATIENT_ACTION getAction() {
        return action;
    }

    public void setAction(AppConstants.PATIENT_ACTION action) {
        this.action = action;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
