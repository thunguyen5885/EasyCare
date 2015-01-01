package vn.easycare.layers.services.concretes;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vn.easycare.layers.services.AbstractWSAccess;
import vn.easycare.layers.services.IWebServiceParamModel;
import vn.easycare.layers.services.WSError;
import vn.easycare.layers.services.models.PatientListWSModel;
import vn.easycare.layers.services.models.PatientWSParamModel;
import vn.easycare.layers.services.models.builders.PatientWSBuilder;
import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/28/14.
 */
public class PatientWSAccess extends AbstractWSAccess<PatientListWSModel,PatientWSParamModel> {
    private static final String PATIENT_URI = WEBSERVICE_HOST + "/doctors/patients?token=%s&is_banned=%s&page%s";
    private static final String BAN_PATIENT_URI = WEBSERVICE_HOST + "/doctors/patients/ban";
    private static final String NOT_BAN_PATIENT_URI = WEBSERVICE_HOST + "/doctors/patients/not_ban";
    private static final String Res_id = "id";
    private static final String Res_full_name = "full_name";
    private static final String Res_gender = "gender";
    private static final String Res_birthday = "birthday";
    private static final String Res_email = "email";
    private static final String Res_phone = "phone";
    private static final String Res_address = "address";
    private static final String Res_avatar = "avatar";
    private static final String Res_avatar_thumb = "avatar_thumb";
    private static final String Res_visits = "visits";
    private static final String Res_cancelVisits = "cancelVisits";
    private static final String Res_numberChangeAppointment = "numberChangeAppointment";
    private static final String Res_totalComment = "totalComment";
    private static final String Res_patients = "patients";
    private static final String Res_paging = "paging";
    private static final String Res_pagetotal = "total";
    private static final String Res_currentPage = "currentPage";
    private static final String Res_lastPage = "lastPage";
    private static final String Res_itemsPerPage = "itemsPerPage";
    private static final String Param_Token = "token";
    private static final String Param_Id = "id";
    PatientWSParamModel mParam;
    @Override
    public String getWSURL() {
        switch (mParam.getAction()){
            case NONE:
                return String.format(PATIENT_URI,mParam.getToken(),mParam.getIsbanned(),mParam.getPage());
            case BAN:
                return BAN_PATIENT_URI;
            case UNBAN:
                return  NOT_BAN_PATIENT_URI;
            default:
                return "";
        }
    }

    @Override
    public Map<String, String> getWSParams() {
        if(mParam.getAction()== AppConstants.PATIENT_ACTION.BAN || mParam.getAction()== AppConstants.PATIENT_ACTION.UNBAN){
            Map<String,String> params = new HashMap<String, String>();
            params.put(Param_Token, mParam.getToken());
            params.put(Param_Id, mParam.getPatientId());
            return  params;
        }else
            return null;
    }

    @Override
    public void setWSParams(IWebServiceParamModel params) {
        mParam = (PatientWSParamModel)params;
    }

    @Override
    public void onParseJsonResponseOK(String jsonResponse) {
        switch (mParam.getAction()){
            case NONE:
                parseResponseForGetAllPatient(jsonResponse);
                break;
            case BAN:
                parseResponseForBanPatient(jsonResponse);
                break;
            case UNBAN:
                parseResponseForUnBanPatient(jsonResponse);
               break;
            default:
                break;
        }

    }

    @Override
    public int getMethod() {
        switch (mParam.getAction()){
            case NONE:
                return Request.Method.GET;
            case BAN:
                return Request.Method.POST;
            case UNBAN:
                return Request.Method.POST;
            default:
                return Request.Method.GET;
        }
    }

    private void parseResponseForGetAllPatient(String jsonResponse){
        try {
            PatientWSBuilder modelBuilder = new  PatientWSBuilder();
            PatientListWSModel listModel = new PatientListWSModel();

            JSONObject jsonBigObj = new JSONObject(jsonResponse);
            JSONArray patients = jsonBigObj.getJSONArray(Res_patients);

            for (int i = 0 ; i < patients.length(); i++) {
                JSONObject jsonObj = patients.getJSONObject(i);
                modelBuilder.Clear();
                modelBuilder.withId(jsonObj.optString(Res_id,""));
                modelBuilder.withFull_name(jsonObj.optString(Res_full_name,""));
                modelBuilder.withGender(Integer.valueOf(jsonObj.optString(Res_gender,"0")).intValue());
                modelBuilder.withBirthday(jsonObj.optString(Res_birthday,""));
                modelBuilder.withEmail(jsonObj.optString(Res_email,""));
                modelBuilder.withPhone(jsonObj.optString(Res_phone,""));
                modelBuilder.withAddress(jsonObj.optString(Res_address,""));
                modelBuilder.withAvatar(jsonObj.optString(Res_avatar,""));
                modelBuilder.withAvatar_thumb(jsonObj.optString(Res_avatar_thumb,""));
                modelBuilder.withVisits(Integer.valueOf(jsonObj.optString(Res_visits,"0")).intValue());
                modelBuilder.withCancelVisits(Integer.valueOf(jsonObj.optString(Res_cancelVisits,"0")).intValue());
                modelBuilder.withNumberChangeAppointment(Integer.valueOf(jsonObj.optString(Res_numberChangeAppointment,"0")).intValue());
                modelBuilder.withTotalComment(Integer.valueOf(jsonObj.optString(Res_totalComment,"0")).intValue());
               ////////
                modelBuilder.withBanned(Integer.valueOf(mParam.getIsbanned()).intValue());
                /////////
                //PatientWSModel model = modelBuilder.build();
                listModel.getListPatients().add(modelBuilder.build());
            }

            JSONObject pageJsonObj = (JSONObject)jsonBigObj.get(Res_paging);
            listModel.setItems_total(Integer.valueOf(pageJsonObj.optString(Res_pagetotal,"0")).intValue());
            listModel.setPage_currentPage(Integer.valueOf(pageJsonObj.optString(Res_currentPage,"0")).intValue());
            listModel.setLastPage(Integer.valueOf(pageJsonObj.optString(Res_lastPage,"0")).intValue());
            listModel.setItemsPerPage(Integer.valueOf(pageJsonObj.optString(Res_itemsPerPage,"0")).intValue());


            if(mCallback!=null)
                mCallback.onWSResponseOK(listModel);
        } catch (JSONException e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage()));
        }
        catch (Exception e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage()));
        }
    }

    private void parseResponseForBanPatient(String jsonResponse){
        PatientWSBuilder modelBuilder = new  PatientWSBuilder();
        modelBuilder.withBanned(1);
        modelBuilder.withId(mParam.getPatientId());
        if(mCallback!=null)
            mCallback.onWSResponseOK(modelBuilder.build());
    }

    private void parseResponseForUnBanPatient(String jsonResponse){
        PatientWSBuilder modelBuilder = new  PatientWSBuilder();
        modelBuilder.withBanned(0);
        modelBuilder.withId(mParam.getPatientId());
        if(mCallback!=null)
            mCallback.onWSResponseOK(modelBuilder.build());
    }

}
