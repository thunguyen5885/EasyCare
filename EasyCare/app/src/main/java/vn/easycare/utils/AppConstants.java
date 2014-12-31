package vn.easycare.utils;

/**
 * Created by phan on 12/9/2014.
 */
public class AppConstants {
    public static final int UNDEFINED_ID = -1;

    public static enum WSMethod {
        GET, POST, PUT, DELETE
    }

    public static enum PATIENT_ACTION {
        NONE, BAN, UNBAN
    }

    public static enum APPOINTMENT_ACTION {
        NONE, ACCEPT, CANCEL, CHANGE, VIEWDETAIL
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
}
