package vn.easycare.layers.services.models.builders;

import org.json.JSONException;
import org.json.JSONObject;

import vn.easycare.layers.services.models.ScheduleWSModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phan on 12/29/2014.
 */
public class ScheduleWSBuilder {
    String id = "";
    String name = "";
    String date ="";
    String time_from ="";
    String time_to ="'";
    int time_slots = 0;
    String note ="";
    String doctor_id ="";
    String clinic_id ="";
    int status = 0;
    String week_day ="";
    String doctor_address_id ="";
    String updated_at = "";
    AppConstants.SCHEDULE_ACTION action;
    public ScheduleWSBuilder() {
    }

    public ScheduleWSBuilder(JSONObject resJson) throws JSONException {
        //parse json and set value for properties
    }

    public ScheduleWSBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ScheduleWSBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ScheduleWSBuilder withDate(String date) {
        this.date = date;
        return this;
    }

    public ScheduleWSBuilder withTime_from(String time_from) {
        this.time_from = time_from;
        return this;
    }

    public ScheduleWSBuilder withTime_to(String time_to) {
        this.time_to = time_to;
        return this;
    }

    public ScheduleWSBuilder withTime_slots(int time_slots) {
        this.time_slots = time_slots;
        return this;
    }

    public ScheduleWSBuilder withNote(String note) {
        this.note = note;
        return this;
    }

    public ScheduleWSBuilder withDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
        return this;
    }

    public ScheduleWSBuilder withClinic_id(String clinic_id) {
        this.clinic_id = clinic_id;
        return this;
    }

    public ScheduleWSBuilder withStatus(int status) {
        this.status = status;
        return this;
    }

    public ScheduleWSBuilder withWeek_day(String week_day) {
        this.week_day = week_day;
        return this;
    }

    public ScheduleWSBuilder withDoctor_address_id(String doctor_address_id) {
        this.doctor_address_id = doctor_address_id;
        return this;
    }

    public ScheduleWSBuilder withUpdated_at(String updated_at) {
        this.updated_at = updated_at;
        return this;
    }

    public ScheduleWSBuilder withAction(AppConstants.SCHEDULE_ACTION action) {
        this.action = action;
        return this;
    }

    public ScheduleWSModel build(){
       return new ScheduleWSModel(id, name, date, time_from, time_to, time_slots, note, doctor_id, clinic_id, status, week_day,  doctor_address_id,updated_at);
    }

    public void Clear(){
        id = "";
        name = "";
        date ="";
        time_from ="";
        time_to ="'";
        time_slots = 0;
        note ="";
        doctor_id ="";
        clinic_id ="";
        status = 0;
        week_day ="";
        doctor_address_id ="";
        updated_at = "";
    }
}
