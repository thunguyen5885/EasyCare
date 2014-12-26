package vn.easycare.utils;

/**
 * Created by phan on 12/9/2014.
 */
public class AppConstants {
    public static final int UNDEFINED_ID = -1;

    public static enum WSMethod {
        GET, POST, PUT, DELETE
    }

    public static enum EXAMINATION_STATUS {
        WAITING, ACCEPTED, CANCEL
    }
    public static final String DEFAULT_ENCODING = "UTF-8";

    public static String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    /* Define all keys for screen transition*/
    public static final String APPOINTMENT_ID_KEY = "appointment_id_key";
    public static final String PATIENT_DETAIL_KEY = "patient_detail_key";
}
