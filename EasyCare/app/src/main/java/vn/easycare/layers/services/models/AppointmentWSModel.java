package vn.easycare.layers.services.models;

import vn.easycare.layers.services.IWebServiceModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phan on 12/26/2014.
 */
public class AppointmentWSModel implements IWebServiceModel {
    String id;
    String doctor_id;
    int status;
    String time;
    String visit_reason;
    int first_visit;
    int visits;
    String code;
    String address;
    String addressId;
    int insurance;
    String insurance_company;
    String doctor_notes;
    String patient_notes;
    String created_at;
    String patient_full_name;
    int patient_gender;
    String patient_phone;
    String patient_birth_date;
    String patient_email;
    String patient_id;
    String patient_avatar;
    String patient_avatarThumb;
    String examine_for;
    AppConstants.APPOINTMENT_ACTION action;

    public AppointmentWSModel(String id, String doctor_id, int status, String time, String visit_reason, int first_visit, int visits, String code, String addressId, String address, int insurance, String insurance_company, String doctor_notes, String patient_notes, String created_at, String patient_full_name, int patient_gender, String patient_phone, String patient_birth_date, String patient_email, String patient_id, String patient_avatar, String patient_avatarThumb, String examine_for, AppConstants.APPOINTMENT_ACTION action) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.status = status;
        this.time = time;
        this.visit_reason = visit_reason;
        this.first_visit = first_visit;
        this.visits = visits;
        this.code = code;
        this.addressId = addressId;
        this.address = address;
        this.insurance = insurance;
        this.insurance_company = insurance_company;
        this.doctor_notes = doctor_notes;
        this.patient_notes = patient_notes;
        this.created_at = created_at;
        this.patient_full_name = patient_full_name;
        this.patient_gender = patient_gender;
        this.patient_phone = patient_phone;
        this.patient_birth_date = patient_birth_date;
        this.patient_email = patient_email;
        this.patient_id = patient_id;
        this.patient_avatar = patient_avatar;
        this.patient_avatarThumb = patient_avatarThumb;
        this.examine_for = examine_for;
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVisit_reason() {
        return visit_reason;
    }

    public void setVisit_reason(String visit_reason) {
        this.visit_reason = visit_reason;
    }

    public int getFirst_visit() {
        return first_visit;
    }

    public void setFirst_visit(int first_visit) {
        this.first_visit = first_visit;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public int getInsurance() {
        return insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
    }

    public String getInsurance_company() {
        return insurance_company;
    }

    public void setInsurance_company(String insurance_company) {
        this.insurance_company = insurance_company;
    }

    public String getDoctor_notes() {
        return doctor_notes;
    }

    public void setDoctor_notes(String doctor_notes) {
        this.doctor_notes = doctor_notes;
    }

    public String getPatient_notes() {
        return patient_notes;
    }

    public void setPatient_notes(String patient_notes) {
        this.patient_notes = patient_notes;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPatient_full_name() {
        return patient_full_name;
    }

    public void setPatient_full_name(String patient_full_name) {
        this.patient_full_name = patient_full_name;
    }

    public int getPatient_gender() {
        return patient_gender;
    }

    public void setPatient_gender(int patient_gender) {
        this.patient_gender = patient_gender;
    }

    public String getPatient_phone() {
        return patient_phone;
    }

    public void setPatient_phone(String patient_phone) {
        this.patient_phone = patient_phone;
    }

    public String getPatient_birth_date() {
        return patient_birth_date;
    }

    public void setPatient_birth_date(String patient_birth_date) {
        this.patient_birth_date = patient_birth_date;
    }

    public String getPatient_email() {
        return patient_email;
    }

    public void setPatient_email(String patient_email) {
        this.patient_email = patient_email;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public AppConstants.APPOINTMENT_ACTION getAction() {
        return action;
    }

    public void setAction(AppConstants.APPOINTMENT_ACTION action) {
        this.action = action;
    }

    public String getPatient_avatar() {
        return patient_avatar;
    }

    public void setPatient_avatar(String patient_avatar) {
        this.patient_avatar = patient_avatar;
    }

    public String getPatient_avatarThumb() {
        return patient_avatarThumb;
    }

    public void setPatient_avatarThumb(String patient_avatarThumb) {
        this.patient_avatarThumb = patient_avatarThumb;
    }

    public String getExamine_for() {
        return examine_for;
    }

    public void setExamine_for(String examine_for) {
        this.examine_for = examine_for;
    }
}
