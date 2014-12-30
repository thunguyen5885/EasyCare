package vn.easycare.layers.ui.components.data;

/**
 * Created by ThuNguyen on 12/30/2014.
 */
public class AppointmentTimeData {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public AppointmentTimeData(){
        year = -1;
        month = -1;
        day = -1;
        hour = -1;
        minute = -1;
    }
    public void set(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public void set(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
