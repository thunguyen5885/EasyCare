package vn.easycare.layers.ui.components.data;

import vn.easycare.layers.ui.components.data.base.IBaseItemData;
import vn.easycare.utils.AppConstants;

/**
 * Created by phan on 12/24/2014.
 */
public class ExaminationScheduleItemData implements IBaseItemData {
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
}
