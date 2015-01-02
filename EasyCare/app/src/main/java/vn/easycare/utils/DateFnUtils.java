package vn.easycare.utils;

import java.util.Calendar;
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
        result += mDateOfWeekMap.get(dateOfWeek) + ", " + day + "/" + (month + 1) + "/" + year;
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

}
