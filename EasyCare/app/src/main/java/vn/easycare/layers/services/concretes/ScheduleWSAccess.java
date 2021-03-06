package vn.easycare.layers.services.concretes;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vn.easycare.R;
import vn.easycare.layers.services.AbstractWSAccess;
import vn.easycare.layers.services.IWebServiceParamModel;
import vn.easycare.layers.services.WSError;
import vn.easycare.layers.services.models.ScheduleWSModel;
import vn.easycare.layers.services.models.ScheduleWSParamModel;
import vn.easycare.layers.services.models.SchedulesListWSModel;
import vn.easycare.layers.services.models.builders.ScheduleWSBuilder;
import vn.easycare.utils.AppConstants;

/**
 * Created by phan on 12/29/2014.
 */
public class ScheduleWSAccess extends AbstractWSAccess<SchedulesListWSModel,ScheduleWSParamModel> {
    private static final String SCHEDULE_URI = WEBSERVICE_HOST + "/doctors/schedules?token=%s&date=%s";
    private static final String CREATE_SCHEDULE_URI = WEBSERVICE_HOST + "/doctors/schedules/create";
    private static final String UPDATE_SCHEDULE_URI = WEBSERVICE_HOST + "/doctors/schedules/update";
    private static final String DELETE_SCHEDULE_URI = WEBSERVICE_HOST + "/doctors/schedules/delete?token=%s&id=%s";
    private static final String VIEW_SCHEDULE_URI = WEBSERVICE_HOST + "/doctors/schedules/%s?token=%s";

    private static final String Res_id = "id";
    private static final String Res_name = "name";
    private static final String Res_date = "date";
    private static final String Res_time_from = "time_from";
    private static final String Res_time_to = "time_to";
    private static final String Res_time_slots = "time_slots";
    private static final String Res_note = "note";
    private static final String Res_doctor_id = "doctor_id";
    private static final String Res_clinic_id = "clinic_id";
    private static final String Res_status = "status";
    private static final String Res_week_day = "week_day";
    private static final String Res_doctor_address_id = "doctor_address_id";
    private static final String Res_updated_at = "updated_at";
    private static final String Res_schedules = "schedules";
    private static final String Res_schedule = "schedule";

    private static final String Param_Token = "token";
    private static final String Param_Id = "id";
    private static final String Param_date = "date";
    private static final String Param_time_from = "time_from";
    private static final String Param_time_to = "time_to";
    private static final String Param_time_slots = "time_slots";
    private static final String Param_doctor_address_id = "doctor_address_id";
    private static final String Param_doctor_notes = "note";
    ScheduleWSParamModel mParam;
    @Override
    public String getWSURL() {
        switch (mParam.getAction()){
            case NONE:
                return String.format(SCHEDULE_URI,mParam.getToken(),mParam.getDate());
            case CREATE:
                return CREATE_SCHEDULE_URI;
            case UPDATE:
                return  UPDATE_SCHEDULE_URI;
            case DELETE:
                return  String.format(DELETE_SCHEDULE_URI,mParam.getToken(),mParam.getScheduleId());
            case VIEWDETAIL:
                return String.format(VIEW_SCHEDULE_URI,mParam.getToken(),mParam.getDate());
            default:
                return "";
        }
    }

    @Override
    public Map<String, String> getWSParams() {
        Map<String,String> params = new HashMap<String, String>();
        switch (mParam.getAction()){
            case CREATE:
                params.put(Param_Token, mParam.getToken());
                params.put(Param_date, mParam.getDate());
                params.put(Param_time_from, mParam.getTime_from());
                params.put(Param_time_to, mParam.getTime_to());
                params.put(Param_time_slots, mParam.getTime_slots());
                params.put(Param_doctor_address_id, mParam.getAddress_doctor_id());
                params.put(Param_doctor_notes, mParam.getDoctor_notes());
                return  params;
            case UPDATE:
                params.put(Param_Token, mParam.getToken());
                params.put(Param_Id, mParam.getScheduleId());
                params.put(Param_date, mParam.getDate());//waiting for confirm......??????????????????????
                params.put(Param_time_from, mParam.getTime_from());
                params.put(Param_time_to, mParam.getTime_to());
                params.put(Param_time_slots, mParam.getTime_slots());
                params.put(Param_doctor_address_id, mParam.getAddress_doctor_id());
                params.put(Param_doctor_notes, mParam.getDoctor_notes());
                return  params;
            /*case DELETE:
                params.put(Param_Token, mParam.getToken());
                params.put(Param_Id, mParam.getScheduleId());
                return  params;*/
            default:
                break;
        }
        return null;

    }

   /* @Override
    public Map<String, String> getWSHeaders() {

        Map<String,String> params = new HashMap<String, String>();
        params.put("Content-Type","application/x-www-form-urlencoded");
        if(mParam.getAction() == AppConstants.SCHEDULE_ACTION.DELETE){
            params.put(Param_Token, mParam.getToken());
            params.put(Param_Id, mParam.getScheduleId());
        }
        return params;
    }*/

    @Override
    public void setWSParams(IWebServiceParamModel params) {
        mParam = (ScheduleWSParamModel)params;
    }

    @Override
    public void onParseJsonResponseOK(String jsonResponse) {
        switch (mParam.getAction()){
            case NONE:
                parseResponseForGetSchedules(jsonResponse);
                break;
            case CREATE:
                parseResponseForCreateSchedule(jsonResponse);
                break;
            case UPDATE:
                parseResponseForUpdateSchedule(jsonResponse);
                break;
            case DELETE:
                parseResponseForDeleteSchedule(jsonResponse);
                break;
            case VIEWDETAIL:
                parseResponseForViewSchedule(jsonResponse);
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
            case CREATE:
                return Request.Method.POST;
            case UPDATE:
                return Request.Method.POST;
            case DELETE:
                return Request.Method.DELETE;
            case VIEWDETAIL:
                return Request.Method.GET;
            default:
                return Request.Method.GET;
        }
    }

    @Override
    public String getRequestTitle() {
        switch (mParam.getAction()){
            case NONE:
                return mContext.getResources().getString(R.string.title_schedule_list);
            case CREATE:
                return mContext.getResources().getString(R.string.title_schedule_create);
            case UPDATE:
                return mContext.getResources().getString(R.string.title_schedule_update);
            case DELETE:
                return mContext.getResources().getString(R.string.title_schedule_delete);
            case VIEWDETAIL:
                return mContext.getResources().getString(R.string.title_schedule_view);
            default:
                return mContext.getResources().getString(R.string.title_schedule_list);
        }

    }
    private void parseResponseForGetSchedules(String jsonResponse){
        try {
            ScheduleWSBuilder modelBuilder = new  ScheduleWSBuilder();
            SchedulesListWSModel listModel = new SchedulesListWSModel();

            JSONObject jsonBigObj = new JSONObject(jsonResponse);
            JSONArray schedules = jsonBigObj.getJSONArray(Res_schedules);

            for (int i = 0 ; i < schedules.length(); i++) {
                JSONObject jsonObj = schedules.getJSONObject(i);
                modelBuilder.Clear();
                modelBuilder.withId(jsonObj.optString(Res_id,""));
                modelBuilder.withName(jsonObj.optString(Res_name,""));
                modelBuilder.withDate(jsonObj.optString(Res_date,""));
                modelBuilder.withTime_from(jsonObj.optString(Res_time_from,""));
                modelBuilder.withTime_to(jsonObj.optString(Res_time_to,""));
                modelBuilder.withTime_slots(Integer.valueOf(jsonObj.optString(Res_time_slots,"0")).intValue());
                modelBuilder.withNote(jsonObj.optString(Res_note,""));
                modelBuilder.withDoctor_id(jsonObj.optString(Res_doctor_id,""));
                modelBuilder.withClinic_id(jsonObj.optString(Res_clinic_id,""));
                modelBuilder.withStatus(Integer.valueOf(jsonObj.optString(Res_status,"0")).intValue());
                modelBuilder.withWeek_day(jsonObj.optString(Res_week_day,""));
                modelBuilder.withDoctor_address_id(jsonObj.optString(Res_doctor_address_id,""));
                listModel.getListSchedules().add(modelBuilder.build());
            }
            if(mCallback!=null)
                mCallback.onWSResponseOK(listModel);
        } catch (JSONException e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage(),getRequestTitle()));
        }
        catch (Exception e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage(),getRequestTitle()));
        }
    }

    private void parseResponseForCreateSchedule(String jsonResponse){
        ScheduleWSBuilder modelBuilder = new  ScheduleWSBuilder();
        modelBuilder.withAction(AppConstants.SCHEDULE_ACTION.CREATE);
        if(mCallback!=null)
            mCallback.onWSResponseOK(modelBuilder.build());
    }

    private void parseResponseForUpdateSchedule(String jsonResponse){
        ScheduleWSBuilder modelBuilder = new  ScheduleWSBuilder();
        modelBuilder.withAction(AppConstants.SCHEDULE_ACTION.UPDATE);
        modelBuilder.withId(mParam.getScheduleId());
        if(mCallback!=null)
            mCallback.onWSResponseOK(modelBuilder.build());
    }

    private void parseResponseForDeleteSchedule(String jsonResponse){
        ScheduleWSBuilder modelBuilder = new  ScheduleWSBuilder();
        modelBuilder.withAction(AppConstants.SCHEDULE_ACTION.DELETE);
        modelBuilder.withId(mParam.getScheduleId());
        if(mCallback!=null)
            mCallback.onWSResponseOK(modelBuilder.build());
    }

    private void parseResponseForViewSchedule(String jsonResponse){
        try {
            ScheduleWSBuilder modelBuilder = new  ScheduleWSBuilder();
            JSONObject jsonBigObj = new JSONObject(jsonResponse);
            JSONObject jsonObj = (JSONObject)jsonBigObj.get(Res_schedule);
            if(jsonObj!=null){
                modelBuilder.withId(jsonObj.optString(Res_id,""));
                modelBuilder.withName(jsonObj.optString(Res_name,""));
                modelBuilder.withDate(jsonObj.optString(Res_date,""));
                modelBuilder.withTime_from(jsonObj.optString(Res_time_from,""));
                modelBuilder.withTime_to(jsonObj.optString(Res_time_to,""));
                modelBuilder.withTime_slots(Integer.valueOf(jsonObj.optString(Res_time_slots,"0")).intValue());
                modelBuilder.withNote(jsonObj.optString(Res_note,""));
                modelBuilder.withDoctor_id(jsonObj.optString(Res_doctor_id,""));
                modelBuilder.withClinic_id(jsonObj.optString(Res_clinic_id,""));
                modelBuilder.withStatus(Integer.valueOf(jsonObj.optString(Res_status,"0")).intValue());
                modelBuilder.withWeek_day(jsonObj.optString(Res_week_day,""));
                modelBuilder.withDoctor_address_id(jsonObj.optString(Res_doctor_address_id,""));
                modelBuilder.withUpdated_at(jsonObj.optString(Res_updated_at,""));
                modelBuilder.withAction(AppConstants.SCHEDULE_ACTION.VIEWDETAIL);
            }
            if(mCallback!=null)
                mCallback.onWSResponseOK(modelBuilder.build());
        } catch (JSONException e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage(),getRequestTitle()));
        }
        catch (Exception e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage(),getRequestTitle()));
        }
    }
}
