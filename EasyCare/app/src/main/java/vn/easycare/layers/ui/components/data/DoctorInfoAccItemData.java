package vn.easycare.layers.ui.components.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by phannguyen on 1/10/15.
 */
public class DoctorInfoAccItemData {
    private String sessionToken="";
    private String doctorFullName="";
    private String doctorAvatar="";
    private String doctorId="";
    private String doctorEmail="";
    private String doctorAvatarThumb="";

    public static final String Res_sessionToken = "sessionToken";
    public static final String Res_doctorFullName = "doctorFullName";
    public static final String Res_doctorAvatar = "doctorAvatar";
    public static final String Res_doctorId = "doctorId";
    public static final String Res_doctorEmail = "doctorEmail";
    public static final String Res_doctorAvatarThumb = "doctorAvatarThumb";

    public DoctorInfoAccItemData(String sessionToken, String doctorFullName, String doctorAvatar, String doctorId, String doctorEmail, String doctorAvatarThumb) {
        this.sessionToken = sessionToken;
        this.doctorFullName = doctorFullName;
        this.doctorAvatar = doctorAvatar;
        this.doctorId = doctorId;
        this.doctorEmail = doctorEmail;
        this.doctorAvatarThumb = doctorAvatarThumb;
    }

    public DoctorInfoAccItemData() {
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    public String getDoctorAvatar() {
        return doctorAvatar;
    }

    public void setDoctorAvatar(String doctorAvatar) {
        this.doctorAvatar = doctorAvatar;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorAvatarThumb() {
        return doctorAvatarThumb;
    }

    public void setDoctorAvatarThumb(String doctorAvatarThumb) {
        this.doctorAvatarThumb = doctorAvatarThumb;
    }

    public void initWithJsonString(String json){
        try {
            JSONObject docJsonObj = new JSONObject(json);
            if(docJsonObj!=null){
                this.sessionToken = docJsonObj.optString(Res_sessionToken,"");
                this.doctorFullName = docJsonObj.optString(Res_doctorFullName,"");
                this.doctorAvatar = docJsonObj.optString(Res_doctorAvatar,"");
                this.doctorId = docJsonObj.optString(Res_doctorId,"");
                this.doctorEmail = docJsonObj.optString(Res_doctorEmail,"");
                this.doctorAvatarThumb = docJsonObj.optString(Res_doctorAvatarThumb,"");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String buildJsonStringFromDoctorInfo() {
        JSONObject jo = new JSONObject();
        try {
            jo.put(Res_sessionToken, sessionToken);
            jo.put(Res_doctorFullName, doctorFullName);
            jo.put(Res_doctorAvatar, doctorAvatar);
            jo.put(Res_doctorId, doctorId);
            jo.put(Res_doctorEmail, doctorEmail);
            jo.put(Res_doctorAvatarThumb, doctorAvatarThumb);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return jo.toString();
    }


}
