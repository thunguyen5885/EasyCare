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
                modelBuilder.withId(jsonObj.get(Res_id).toString());
                modelBuilder.withFull_name(jsonObj.get(Res_full_name).toString());
                modelBuilder.withGender(Integer.valueOf(jsonObj.get(Res_gender).toString()).intValue());
                modelBuilder.withBirthday(jsonObj.get(Res_birthday).toString());
                modelBuilder.withEmail(jsonObj.get(Res_email).toString());
                modelBuilder.withPhone(jsonObj.get(Res_phone).toString());
                modelBuilder.withAddress(jsonObj.get(Res_address).toString());
                modelBuilder.withAvatar(jsonObj.get(Res_avatar).toString());
                modelBuilder.withAvatar_thumb(jsonObj.get(Res_avatar_thumb).toString());
                modelBuilder.withVisits(Integer.valueOf(jsonObj.get(Res_visits).toString()).intValue());
                modelBuilder.withCancelVisits(Integer.valueOf(jsonObj.get(Res_cancelVisits).toString()).intValue());
                modelBuilder.withNumberChangeAppointment(Integer.valueOf(jsonObj.get(Res_numberChangeAppointment).toString()).intValue());
                modelBuilder.withTotalComment(Integer.valueOf(jsonObj.get(Res_totalComment).toString()).intValue());
               ////////
                modelBuilder.withBanned(Integer.valueOf(mParam.getIsbanned()).intValue());
                /////////
                //PatientWSModel model = modelBuilder.build();
                listModel.getListPatients().add(modelBuilder.build());
            }

            JSONObject pageJsonObj = (JSONObject)jsonBigObj.get(Res_paging);
            listModel.setItems_total(Integer.valueOf(pageJsonObj.get(Res_pagetotal).toString()).intValue());
            listModel.setPage_currentPage(Integer.valueOf(pageJsonObj.get(Res_currentPage).toString()).intValue());
            listModel.setLastPage(Integer.valueOf(pageJsonObj.get(Res_lastPage).toString()).intValue());
            listModel.setItemsPerPage(Integer.valueOf(pageJsonObj.get(Res_itemsPerPage).toString()).intValue());


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
