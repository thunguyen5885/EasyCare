package vn.easycare.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import vn.easycare.layers.ui.components.data.AppointmentTimeData;
import vn.easycare.layers.ui.fragments.CalendarCreatingFragment;

/**
 * Created by ThuNguyen on 12/21/2014.
 */
public class DateFnUtils {
    private static HashMap<Integer, String> mDateOfWeekMap = new HashMap<Integer, String>();
    public DateFnUtils(){

    }
    private static void init(){
        if(mDateOfWeekMap.size() == 0){
            mDateOfWeekMap.put(Calendar.SUNDAY, "Chủ nhật");
            mDateOfWeekMap.put(Calendar.MONDAY, "Thứ 2");
            mDateOfWeekMap.put(Calendar.TUESDAY, "Thứ 3");
            mDateOfWeekMap.put(Calendar.WEDNESDAY, "Thứ 4");
            mDateOfWeekMap.put(Calendar.THURSDAY, "Thứ 5");
            mDateOfWeekMap.put(Calendar.FRIDAY, "Thứ 6");
            mDateOfWeekMap.put(Calendar.SATURDAY, "Thứ 7");
        }
    }
    /**
     * Return string as "Thứ 2, 19/11/2014"
     * @param year
     * @param month
     * @param day
     * @return
     */

    public static String getDateTimeStringWithDayOfWeekByVietnamese(int year, int month, int day){
        init();
        String result = "";
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        int dateOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String dateStr = (day > 9)?(day + ""):("0" + day);
        String monthStr = ((month + 1) > 9) ? ((month + 1) + "") : ("0" + (month + 1));
        result += mDateOfWeekMap.get(dateOfWeek) + ", " + dateStr + "/" + monthStr + "/" + year;
        return result;
    }

    public static void nextDate(AppointmentTimeData myDate){
        Calendar calendar = Calendar.getInstance();
        calendar.set(myDate.getYear(), myDate.getMonth() , myDate.getDay());
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        myDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }
    public static void previousDate(AppointmentTimeData myDate){
        Calendar calendar = Calendar.getInstance();
        calendar.set(myDate.getYear(), myDate.getMonth() , myDate.getDay());
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        myDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }
    public static void getCurrentDate(AppointmentTimeData myDate){
        Calendar calendar = Calendar.getInstance();
        myDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }
    public static void getDateRangeOfWeek(AppointmentTimeData fromDate, AppointmentTimeData toDate){
        Calendar calendar = Calendar.getInstance();
        int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        // Because week start from MONDAY so calculate the SUNDAY with value = 1 separate
        int dayNeedToSub = 0;
        int dayNeedToAdd = 0;
        if(currentDayOfWeek == Calendar.SUNDAY){
            dayNeedToSub = 6;
            dayNeedToAdd = 0;
        }else{
            dayNeedToSub = currentDayOfWeek - Calendar.MONDAY;
            dayNeedToAdd = Calendar.DAY_OF_WEEK - currentDayOfWeek + 1;
        }
        // For from date
        calendar.add(Calendar.DAY_OF_MONTH, -dayNeedToSub);
        fromDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        // For to date
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, dayNeedToAdd);
        toDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static void getDateRangeOfMonth(AppointmentTimeData fromDate, AppointmentTimeData toDate){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = 1;
        // For from date
        fromDate.set(year, month, day);

        // For to date
        if(month == Calendar.JANUARY || month == Calendar.MARCH ||
                month == Calendar.MAY || month == Calendar.JULY ||
                month == Calendar.AUGUST || month == Calendar.OCTOBER ||
                month == Calendar.DECEMBER){
            day = 31;
        }else if(month == Calendar.APRIL || month == Calendar.JUNE ||
                month == Calendar.SEPTEMBER || month == Calendar.NOVEMBER){
            day = 30;
        }else{ // February
            // Check leap year
            if(((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)){
                day = 29;
            }else{
                day = 28;
            }
        }

        toDate.set(year, month, day);
    }
}
