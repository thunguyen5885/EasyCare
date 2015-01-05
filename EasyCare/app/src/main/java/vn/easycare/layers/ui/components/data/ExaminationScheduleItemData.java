package vn.easycare.layers.ui.components.data;

import java.io.Serializable;

import vn.easycare.layers.ui.components.data.base.IBaseItemData;
import vn.easycare.utils.AppConstants;

/**
 * Created by phan on 12/24/2014.
 */
public class ExaminationScheduleItemData implements IBaseItemData, Serializable, Comparable {
    String scheduleId;
    String scheduleDate;//yyyy-MM-dd
    String timeFrom;//hh:mm:ss
    String timeTo;
    int timeSlot;
    String doctorAddressId;
    String note;

    AppConstants.SCHEDULE_ACTION action;
    public ExaminationScheduleItemData() {
    }

    public ExaminationScheduleItemData(String scheduleId, String scheduleDate, String timeFrom, String timeTo, int timeSlot, String doctorAddressId, String note) {
        this.scheduleId = scheduleId;
        this.scheduleDate = scheduleDate;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.timeSlot = timeSlot;
        this.doctorAddressId = doctorAddressId;
        this.note = note;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getDoctorAddressId() {
        return doctorAddressId;
    }

    public void setDoctorAddressId(String doctorAddressId) {
        this.doctorAddressId = doctorAddressId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public AppConstants.SCHEDULE_ACTION getAction() {
        return action;
    }

    public void setAction(AppConstants.SCHEDULE_ACTION action) {
        this.action = action;
    }

    public int getHourFrom(){
        if(timeFrom != null && timeFrom.contains(":")) {
            String[] timeArr = timeFrom.split(":");
            return Integer.parseInt(timeArr[0]);
        }
        return 0;

    }
    public int getHourTo(){
        if(timeTo != null && timeTo.contains(":")) {
            String[] timeArr = timeTo.split(":");
            return Integer.parseInt(timeArr[0]);
        }
        return 0;
    }
    public int getMinuteFrom(){
        if(timeFrom != null && timeFrom.contains(":")) {
            String[] timeArr = timeFrom.split(":");
            return Integer.parseInt(timeArr[1]);
        }
        return 0;
    }
    public int getMinuteTo(){
        if(timeTo != null && timeTo.contains(":")) {
            String[] timeArr = timeTo.split(":");
            return Integer.parseInt(timeArr[1]);
        }
        return 0;

    }
    public String getDisplayForHourMinuteFrom(){
        String text = "";
        int hour = getHourFrom();
        int minute = getMinuteFrom();
        if(hour > 9){
            text += hour + ":";
        }else{
            text += "0" + hour + ":";
        }
        if(minute > 9){
            text += minute + "";
        }else{
            text += "0" + minute + "";
        }
        return text;
    }
    public String getDisplayForHourMinuteTo(){
        String text = "";
        int hour = getHourTo();
        int minute = getMinuteTo();
        if(hour > 9){
            text += hour + ":";
        }else{
            text += "0" + hour + ":";
        }
        if(minute > 9){
            text += minute + "";
        }else{
            text += "0" + minute + "";
        }
        return text;
    }

    @Override
    public int compareTo(Object another) {
        if(another instanceof ExaminationScheduleItemData){
            ExaminationScheduleItemData examinationScheduleItemData = (ExaminationScheduleItemData)another;
            int hour = examinationScheduleItemData.getHourFrom();
            int minute = examinationScheduleItemData.getMinuteFrom();

            int selectedHour = getHourFrom();
            int selectedMinute = getMinuteFrom();

            if(hour > selectedHour){
                return -1;
            }else if(hour == selectedHour){
                if(minute > selectedMinute){
                    return -1;
                }else if(minute < selectedMinute){
                    return 1;
                }
            }else{
                return 1;
            }
        }
        return 0;
    }
}
