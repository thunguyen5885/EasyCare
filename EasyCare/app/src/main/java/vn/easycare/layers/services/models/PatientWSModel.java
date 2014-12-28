package vn.easycare.layers.services.models;

import vn.easycare.layers.services.IWebServiceModel;

/**
 * Created by phannguyen on 12/28/14.
 */
public class PatientWSModel implements IWebServiceModel{
    String id;
    String full_name;
    int gender;
    String birthday;
    String email;
    String phone;
    String address;
    String avatar;
    String avatar_thumb;
    int visits;
    int cancelVisits;
    int numberChangeAppointment;
    int totalComment;
    int banned;

    public PatientWSModel(String id, String full_name, int gender, String birthday, String email, String phone, String address, String avatar, String avatar_thumb, int visits, int cancelVisits, int numberChangeAppointment, int totalComment, int banned) {
        this.id = id;
        this.full_name = full_name;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.avatar = avatar;
        this.avatar_thumb = avatar_thumb;
        this.visits = visits;
        this.cancelVisits = cancelVisits;
        this.numberChangeAppointment = numberChangeAppointment;
        this.totalComment = totalComment;
        this.banned = banned;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar_thumb() {
        return avatar_thumb;
    }

    public void setAvatar_thumb(String avatar_thumb) {
        this.avatar_thumb = avatar_thumb;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public int getCancelVisits() {
        return cancelVisits;
    }

    public void setCancelVisits(int cancelVisits) {
        this.cancelVisits = cancelVisits;
    }

    public int getNumberChangeAppointment() {
        return numberChangeAppointment;
    }

    public void setNumberChangeAppointment(int numberChangeAppointment) {
        this.numberChangeAppointment = numberChangeAppointment;
    }

    public int getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(int totalComment) {
        this.totalComment = totalComment;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }
}
