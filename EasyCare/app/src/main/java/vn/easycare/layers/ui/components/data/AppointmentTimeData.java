package vn.easycare.layers.ui.components.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    public void set(Calendar calendar){
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
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

    public String generateDateString(String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return sdf.format(calendar.getTime());

    }
    public String generateTimeString(String timeFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return sdf.format(calendar.getTime());
    }
    public boolean isValidateDate(){
        return year != -1 && month != -1 && day != -1;
    }
}
