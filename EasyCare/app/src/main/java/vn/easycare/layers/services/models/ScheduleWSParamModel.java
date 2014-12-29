package vn.easycare.layers.services.models;

import vn.easycare.layers.services.IWebServiceParamModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phan on 12/29/2014.
 */
public class ScheduleWSParamModel implements IWebServiceParamModel{
    String token;
    String id;
    String date;
    String time_from;
    String time_to;
    String time_slots;
    String address_doctor_id;
    String doctor_notes;
    AppConstants.SCHEDULE_ACTION action;

    public ScheduleWSParamModel(String token, String id, String date, String time_from, String time_to, String time_slots, String address_doctor_id, String doctor_notes, AppConstants.SCHEDULE_ACTION action) {
        this.token = token;
        this.id = id;
        this.date = date;
        this.time_from = time_from;
        this.time_to = time_to;
        this.time_slots = time_slots;
        this.address_doctor_id = address_doctor_id;
        this.doctor_notes = doctor_notes;
        this.action = action;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getScheduleId() {
        return id;
    }

    public void setScheduleId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime_from() {
        return time_from;
    }

    public void setTime_from(String time_from) {
        this.time_from = time_from;
    }

    public String getTime_to() {
        return time_to;
    }

    public void setTime_to(String time_to) {
        this.time_to = time_to;
    }

    public String getTime_slots() {
        return time_slots;
    }

    public void setTime_slots(String time_slots) {
        this.time_slots = time_slots;
    }

    public String getAddress_doctor_id() {
        return address_doctor_id;
    }

    public void setAddress_doctor_id(String address_doctor_id) {
        this.address_doctor_id = address_doctor_id;
    }

    public String getDoctor_notes() {
        return doctor_notes;
    }

    public void setDoctor_notes(String doctor_notes) {
        this.doctor_notes = doctor_notes;
    }

    public AppConstants.SCHEDULE_ACTION getAction() {
        return action;
    }

    public void setAction(AppConstants.SCHEDULE_ACTION action) {
        this.action = action;
    }
}
