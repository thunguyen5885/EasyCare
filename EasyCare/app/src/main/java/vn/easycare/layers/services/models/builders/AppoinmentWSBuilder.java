package vn.easycare.layers.services.models.builders;

import org.json.JSONException;
import org.json.JSONObject;

import vn.easycare.layers.services.models.AppointmentWSModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phan on 12/26/2014.
 */
public class AppoinmentWSBuilder {
    String id ="";
    String doctor_id ="";
    int status = 0;
    String time ="";
    String visit_reason = "";
    int first_visit = 0;
    int visits = 0;
    String code = "";
    String address ="";
    int insurance = 0;
    String insurance_company = "";
    String doctor_notes = "";
    String patient_notes = "";
    String created_at = "";
    String patient_full_name = "";
    int patient_gender = 0;
    String patient_phone = "";
    String patient_birth_date = "";
    String patient_email = "";
    String patient_id ="";
    String patient_avatar = "";
    String patient_avatarThumb = "";
    String examine_for = "";
    AppConstants.APPOINTMENT_ACTION action= AppConstants.APPOINTMENT_ACTION.NONE;
    public AppoinmentWSBuilder() {
    }

    public AppoinmentWSBuilder(JSONObject resJson) throws JSONException {
        //parse json and set value for properties
    }

    public AppoinmentWSBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public AppoinmentWSBuilder withDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
        return this;
    }

    public AppoinmentWSBuilder withStatus(int status) {
        this.status = status;
        return this;
    }

    public AppoinmentWSBuilder withTime(String time) {
        this.time = time;
        return this;
    }

    public AppoinmentWSBuilder withVisit_reason(String visit_reason) {
        this.visit_reason = visit_reason;
        return this;
    }

    public AppoinmentWSBuilder withFirst_visit(int first_visit) {
        this.first_visit = first_visit;
        return this;
    }

    public AppoinmentWSBuilder withVisits(int visits) {
        this.visits = visits;
        return this;
    }

    public AppoinmentWSBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public AppoinmentWSBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public AppoinmentWSBuilder withInsurance(int insurance) {
        this.insurance = insurance;
        return this;
    }

    public AppoinmentWSBuilder withInsurance_company(String insurance_company) {
        this.insurance_company = insurance_company;
        return this;
    }

    public AppoinmentWSBuilder withDoctor_notes(String doctor_notes) {
        this.doctor_notes = doctor_notes;
        return this;
    }

    public AppoinmentWSBuilder withPatient_notes(String patient_notes) {
        this.patient_notes = patient_notes;
        return this;
    }

    public AppoinmentWSBuilder withCreated_at(String created_at) {
        this.created_at = created_at;
        return this;
    }

    public AppoinmentWSBuilder withPatient_full_name(String patient_full_name) {
        this.patient_full_name = patient_full_name;
        return this;
    }

    public AppoinmentWSBuilder withPatient_gender(int patient_gender) {
        this.patient_gender = patient_gender;
        return this;
    }

    public AppoinmentWSBuilder withPatient_phone(String patient_phone) {
        this.patient_phone = patient_phone;
        return this;
    }

    public AppoinmentWSBuilder withPatient_birth_date(String patient_birth_date) {
        this.patient_birth_date = patient_birth_date;
        return this;
    }

    public AppoinmentWSBuilder withPatient_email(String patient_email) {
        this.patient_email = patient_email;
        return this;
    }

    public AppoinmentWSBuilder withPatient_id(String patient_id) {
        this.patient_id = patient_id;
        return this;
    }

    public AppoinmentWSBuilder withAction(AppConstants.APPOINTMENT_ACTION action) {
        this.action = action;
        return  this;
    }

    public AppoinmentWSBuilder withPatient_avatar(String patient_avatar) {
        this.patient_avatar = patient_avatar;
        return  this;
    }

    public AppoinmentWSBuilder withPatient_avatarThumb(String patient_avatarThumb) {
        this.patient_avatarThumb = patient_avatarThumb;
        return  this;
    }

    public AppoinmentWSBuilder withExamine_for(String examine_for) {
        this.examine_for = examine_for;
        return  this;
    }

    public AppointmentWSModel build(){
        return new AppointmentWSModel( id,  doctor_id,  status,  time,  visit_reason,
                first_visit,  visits,  code,  address,  insurance,
                insurance_company,  doctor_notes,  patient_notes,
                created_at,  patient_full_name,  patient_gender,
                patient_phone, patient_birth_date, patient_email, patient_id,patient_avatar,patient_avatarThumb,examine_for,action);
    }

    public void Clear(){
        id ="";
        doctor_id ="";
        status = 0;
        time ="";
        visit_reason = "";
        first_visit = 0;
        visits = 0;
        code = "";
        address ="";
        insurance = 0;
        insurance_company = "";
        doctor_notes = "";
        patient_notes = "";
        created_at = "";
        patient_full_name = "";
        patient_gender = 0;
        patient_phone = "";
        patient_birth_date = "";
        patient_email = "";
        patient_id ="";
        patient_avatar = "";
        patient_avatarThumb = "";
        examine_for = "";
        AppConstants.APPOINTMENT_ACTION action= AppConstants.APPOINTMENT_ACTION.NONE;
    }
}
