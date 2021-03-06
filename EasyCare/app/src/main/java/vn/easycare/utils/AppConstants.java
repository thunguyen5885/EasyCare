package vn.easycare.utils;

/**
 * Created by phan on 12/9/2014.
 */
public class AppConstants {
    public static final int UNDEFINED_ID = -1;
    public  static final String NOTHING_TEXTDATA = "nothing_textdata";
    public static enum WSMethod {
        GET, POST, PUT, DELETE
    }

    public static enum PATIENT_ACTION {
        NONE, BAN, UNBAN
    }

    public static enum AUTHORIZATION_ACTION {
        LOGIN, REGISTER_DEVICE_ID
    }

    public static enum APPOINTMENT_ACTION {
        NONE, ACCEPT, CANCEL, CHANGE, VIEWDETAIL,UPDATE_DOCTOR_NOTE
    }

    public static enum SCHEDULE_ACTION {
        NONE, CREATE, UPDATE, DELETE, VIEWDETAIL
    }

    public static enum EXAMINATION_STATUS {
        WAITING(0), ACCEPTED(1)/*3*/, CANCEL(-1)/*-2,-3*/, MISSING(-4);
        private int value;

        private EXAMINATION_STATUS(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    public static final String DEFAULT_ENCODING = "UTF-8";

    public static String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    /*Define string for sender_id for push notification*/
   public static final String SENDER_ID = "113119615392";
   //public static final String SENDER_ID = "338065318085";
   public static final String INTENT_FILTER_UPDATE_NOTIFICATION_COUNT = "vn.easycare.notification";

    /*Define start time and end time for calendar creating*/
    public static final int START_TIME = 7;
    public static final int END_TIME = 22;

    /*Define all date format*/
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_DD_MM_YYYY_HH_MM = "dd/MM/yyyy, HH:mm";
    public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String TIME_FORMAT_HH_MM = "HH:mm";

    /* Define all keys for screen transition*/
    public static final String APPOINTMENT_ID_KEY = "appointment_id_key";
    public static final String PATIENT_DETAIL_KEY = "patient_detail_key";
    public static final String EXAMINATION_TYPE_KEY = "examination_type_key";
    public static final String CALENDAR_ID_KEY = "calendar_id_key";
    public static final String APPOINTMENT_NOTIFICATION_KEY = "appointment_notification_key";
    public static final String APPOINTMENT_UPDATE_NOTIFICATION_COUNT_KEY = "appointment_update_notification_count_key";

    public static final int HTTP_STATUS_UNAUTHORIZED = 401;
    public static final int APP_EXCEPTION_STATUS_CODE = -100;
    public static final int APP_UNDEFINE_ERROR_STATUS_CODE = -101;



}
