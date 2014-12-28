package vn.easycare.layers.services.models.builders;

import org.json.JSONException;
import org.json.JSONObject;

import vn.easycare.layers.services.models.PatientWSModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/28/14.
 */
public class PatientWSBuilder {
    String id="";
    String full_name="";
    int gender=0;
    String birthday="";
    String email="";
    String phone="";
    String address="";
    String avatar="";
    String avatar_thumb="";
    int visits=0;
    int cancelVisits=0;
    int numberChangeAppointment=0;
    int totalComment=0;
    int banned = 0;


    public PatientWSBuilder() {
    }

    public PatientWSBuilder(JSONObject resJson) throws JSONException {
        //parse json and set value for properties
    }

    public PatientWSBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public PatientWSBuilder withFull_name(String full_name) {
        this.full_name = full_name;
        return this;
    }

    public PatientWSBuilder withGender(int gender) {
        this.gender = gender;
        return this;
    }

    public PatientWSBuilder withBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public PatientWSBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public PatientWSBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public PatientWSBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public PatientWSBuilder withAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public PatientWSBuilder withAvatar_thumb(String avatar_thumb) {
        this.avatar_thumb = avatar_thumb;
        return this;
    }

    public PatientWSBuilder withVisits(int visits) {
        this.visits = visits;
        return this;
    }

    public PatientWSBuilder withCancelVisits(int cancelVisits) {
        this.cancelVisits = cancelVisits;
        return this;
    }

    public PatientWSBuilder withNumberChangeAppointment(int numberChangeAppointment) {
        this.numberChangeAppointment = numberChangeAppointment;
        return this;
    }

    public PatientWSBuilder withTotalComment(int totalComment) {
        this.totalComment = totalComment;
        return this;
    }

    public PatientWSBuilder withBanned(int banned) {
        this.banned = banned;
        return this;
    }



    public PatientWSModel build(){
        return new PatientWSModel(id, full_name, gender, birthday,
                email, phone, address, avatar,
                avatar_thumb, visits, cancelVisits,
                numberChangeAppointment, totalComment,banned);
    }

    public void Clear(){
        id="";
        full_name="";
        gender=0;
        birthday="";
        email="";
        phone="";
        address="";
        avatar="";
        avatar_thumb="";
        visits=0;
        cancelVisits=0;
        numberChangeAppointment=0;
        totalComment=0;

    }
}
