package vn.easycare.layers.services.models;

import vn.easycare.layers.services.IWebServiceModel;

/**
 * Created by phan on 12/29/2014.
 */
public class ScheduleWSModel  implements IWebServiceModel{
    String id;
    String name;
    String date;
    String time_from;
    String time_to;
    int time_slots;
    String note;
    String doctor_id;
    String clinic_id;
    int status;
    String week_day;
    String doctor_address_id;
    String updated_at;

    public ScheduleWSModel(String id, String name, String date, String time_from, String time_to, int time_slots, String note, String doctor_id, String clinic_id, int status, String week_day, String doctor_address_id, String updated_at) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time_from = time_from;
        this.time_to = time_to;
        this.time_slots = time_slots;
        this.note = note;
        this.doctor_id = doctor_id;
        this.clinic_id = clinic_id;
        this.status = status;
        this.week_day = week_day;
        this.doctor_address_id = doctor_address_id;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getTime_slots() {
        return time_slots;
    }

    public void setTime_slots(int time_slots) {
        this.time_slots = time_slots;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getClinic_id() {
        return clinic_id;
    }

    public void setClinic_id(String clinic_id) {
        this.clinic_id = clinic_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getWeek_day() {
        return week_day;
    }

    public void setWeek_day(String week_day) {
        this.week_day = week_day;
    }

    public String getDoctor_address_id() {
        return doctor_address_id;
    }

    public void setDoctor_address_id(String doctor_address_id) {
        this.doctor_address_id = doctor_address_id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
