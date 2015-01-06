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
import vn.easycare.layers.services.models.AppointmentListWSModel;
import vn.easycare.layers.services.models.AppointmentWSParamModel;
import vn.easycare.layers.services.models.builders.AppoinmentWSBuilder;
import vn.easycare.utils.AppConstants;

/**
 * Created by phan on 12/26/2014.
 */
public class AppointmentWSAccess extends AbstractWSAccess<AppointmentListWSModel,AppointmentWSParamModel> {
    private static final String APPOINTMENT_URI = WEBSERVICE_HOST +
    "/doctors/appointments?token=%s&appointmentCode=%s&appointmentStatus=%s&patientName=%s&appointmentDate=%s&startDate=%s&endDate=%s&patientId=%s&numberOfRecords=%s&page=%s";
    private static final String APPOINTMENT_ACCEPT_URI = WEBSERVICE_HOST + "/doctors/appointments/accept";
    private static final String APPOINTMENT_CANCEL_URI = WEBSERVICE_HOST + "/doctors/appointments/reject";
    private static final String APPOINTMENT_CHANGE_URI = WEBSERVICE_HOST + "/doctors/appointments/update";
    private static final String APPOINTMENT_VIEW_URI = WEBSERVICE_HOST + "/doctors/appointments/%s?token=%s";
    private static final String DOCTOR_UPDATE_URI = WEBSERVICE_HOST + "/doctors/appointments/update_doctor_note";

    private static final String Res_appointments = "appointments";
    private static final String Res_appointment = "appointment";
    private static final String Res_id = "id";
    private static final String Res_doctor_id = "doctor_id";
    private static final String Res_status = "status";
    private static final String Res_time = "time";
    private static final String Res_visit_reason = "visit_reason";
    private static final String Res_first_visit = "first_visit";
    private static final String Res_visits = "visits";
    private static final String Res_code = "code";
    private static final String Res_address = "address";
    private static final String Res_insurance = "insurance";
    private static final String Res_insurance_company = "insurance_company";
    private static final String Res_doctor_notes = "doctor_notes";
    private static final String Res_patient_notes = "patient_notes";
    private static final String Res_created_at = "created_at";
    private static final String Res_patient = "patient";
    private static final String Res_full_name = "full_name";
    private static final String Res_gender = "gender";
    private static final String Res_phone = "phone";
    private static final String Res_birth_date = "birth_date";
    private static final String Res_email = "email";
    private static final String Res_paging = "paging";
    private static final String Res_pagetotal = "total";
    private static final String Res_currentPage = "currentPage";
    private static final String Res_lastPage = "lastPage";
    private static final String Res_itemsPerPage = "itemsPerPage";
    private static final String Res_avatar = "avatar";
    private static final String Res_avatar_thumb = "avatar_thumb";
    private static final String Res_examine_for = "examine_for";

    private static final String Param_Token = "token";
    private static final String Param_appointmentCode = "appointmentCode";
    private static final String Param_appointmentStatus = "appointmentStatus";
    private static final String Param_patientName = "patientName";
    private static final String Param_appointmentDate = "appointmentDate";
    private static final String Param_startDate = "startDate";
    private static final String Param_endDate = "endDate";
    private static final String Param_patientId = "patientId";
    private static final String Param_numberOfRecords = "numberOfRecords";
    private static final String Param_page = "page";
    private static final String Param_date = "date";
    private static final String Param_time = "time";
    private static final String Param_address = "address";
    private static final String Param_id = "id";
    private static final String Param_doctor_notes = "doctor_notes";
    private AppointmentWSParamModel mParam;
    @Override
    public String getWSURL() {

        switch (mParam.getAction()){
            case NONE:
                return String.format(APPOINTMENT_URI,
                        mParam.getToken(),
                        mParam.getAppointmentCode(),
                        mParam.getAppointmentStatus(),
                        mParam.getPatientName(),
                        mParam.getAppointmentDate(),
                        mParam.getStartDate(),
                        mParam.getEndDate(),
                        mParam.getPatientId(),
                        mParam.getNumberOfRecords(),
                        mParam.getPage());
            case ACCEPT:
                return APPOINTMENT_ACCEPT_URI;
            case CANCEL:
                return  APPOINTMENT_CANCEL_URI;
            case CHANGE:
                return APPOINTMENT_CHANGE_URI;
            case VIEWDETAIL:
                return  String.format(APPOINTMENT_VIEW_URI,mParam.getAppointmentId(),mParam.getToken());
            case UPDATE_DOCTOR_NOTE:
                return  DOCTOR_UPDATE_URI;
            default:
                return "";
        }

    }

    @Override
    public Map<String, String> getWSParams() {
      /*  Map<String,String> params = new HashMap<String, String>();
        if(mParam!=null) {
            params.put(Param_Token, mParam.getToken());
            params.put(Param_page, mParam.getPage());
            if(mParam.getAppointmentCode()!=null && mParam.getAppointmentCode()!="")
                params.put(Param_appointmentCode,  mParam.getAppointmentCode());
            if(mParam.getAppointmentStatus()!=null && mParam.getAppointmentStatus()!="")
                params.put(Param_appointmentStatus, mParam.getAppointmentStatus());
            if(mParam.getPatientName()!=null && mParam.getPatientName()!="")
                params.put(Param_patientName, mParam.getPatientName());
            if(mParam.getAppointmentDate()!=null && mParam.getAppointmentDate()!="")
                params.put(Param_appointmentDate, mParam.getAppointmentDate());
            if(mParam.getStartDate()!=null && mParam.getStartDate()!="")
                params.put(Param_startDate, mParam.getStartDate());
            if(mParam.getEndDate()!=null && mParam.getEndDate()!="")
                params.put(Param_endDate, mParam.getEndDate());
            if(mParam.getNumberOfRecords()!=null && mParam.getNumberOfRecords()!="")
                params.put(Param_numberOfRecords, mParam.getNumberOfRecords());
            if(mParam.getPatientId()!=null && mParam.getPatientId()!="")
                params.put(Param_patientId, mParam.getPatientId());
        }
        return params;*/
        if(mParam.getAction()== AppConstants.APPOINTMENT_ACTION.ACCEPT || mParam.getAction()== AppConstants.APPOINTMENT_ACTION.CANCEL){
            Map<String,String> params = new HashMap<String, String>();
            params.put(Param_Token, mParam.getToken());
            params.put(Param_id, mParam.getAppointmentId());
            return  params;
        }else if(mParam.getAction()== AppConstants.APPOINTMENT_ACTION.CHANGE){
            Map<String,String> params = new HashMap<String, String>();
            params.put(Param_Token, mParam.getToken());
            params.put(Param_id, mParam.getAppointmentId());
            params.put(Param_date, mParam.getDate());
            params.put(Param_time, mParam.getTime());
            params.put(Param_address, mParam.getAddress());
            return  params;
        }else if(mParam.getAction()== AppConstants.APPOINTMENT_ACTION.UPDATE_DOCTOR_NOTE){
            Map<String,String> params = new HashMap<String, String>();
            params.put(Param_Token, mParam.getToken());
            params.put(Param_id, mParam.getAppointmentId());
            params.put(Param_doctor_notes, mParam.getDoctor_notes());
            return  params;
        }else
            return null;
    }

    @Override
    public void setWSParams(IWebServiceParamModel params) {
        mParam = (AppointmentWSParamModel)params;
    }

    @Override
    public int getMethod() {
        switch (mParam.getAction()){
            case NONE:
                return Request.Method.GET;
            case ACCEPT:
                return Request.Method.POST;
            case CANCEL:
                return Request.Method.POST;
            case CHANGE:
                return Request.Method.POST;
            case VIEWDETAIL:
                return Request.Method.GET;
            case UPDATE_DOCTOR_NOTE:
                return Request.Method.POST;
            default:
                return Request.Method.GET;
        }
    }

    @Override
    public String getRequestTitle() {
        switch (mParam.getAction()){
            case NONE:
                return mContext.getResources().getString(R.string.title_appointment_list);
            case ACCEPT:
                return mContext.getResources().getString(R.string.title_appointment_accept);
            case CANCEL:
                return mContext.getResources().getString(R.string.title_appointment_cancel);
            case CHANGE:
                return mContext.getResources().getString(R.string.title_appointment_change);
            case VIEWDETAIL:
                return mContext.getResources().getString(R.string.title_appointment_detailview);
            case UPDATE_DOCTOR_NOTE:
                return mContext.getResources().getString(R.string.title_appointment_update_doctor_note);
            default:
                return mContext.getResources().getString(R.string.title_appointment_list);
        }
    }

    @Override
    public void onParseJsonResponseOK(String jsonResponse) {
        switch (mParam.getAction()){
            case NONE:
                parseResponseForLoadAppointments(jsonResponse);
                break;
            case ACCEPT:
                parseResponseForAcceptAppointments(jsonResponse);
                break;
            case CANCEL:
                parseResponseForCancelAppointments(jsonResponse);
                break;
            case CHANGE:
                parseResponseForChangeAppointments(jsonResponse);
                break;
            case VIEWDETAIL:
                parseResponseForViewAppointments(jsonResponse);
                break;
            case UPDATE_DOCTOR_NOTE:
                parseResponseForUpdateDoctorNote(jsonResponse);
                break;
            default:
                break;
        }
    }

    private void parseResponseForLoadAppointments(String jsonResponse){
        try {
            AppoinmentWSBuilder modelBuilder = new  AppoinmentWSBuilder();
            AppointmentListWSModel listModel = new AppointmentListWSModel();

            JSONObject jsonBigObj = new JSONObject(jsonResponse);
            JSONArray appointments = jsonBigObj.getJSONArray(Res_appointments);

            for (int i = 0 ; i < appointments.length(); i++) {
                JSONObject jsonObj = appointments.getJSONObject(i);
                modelBuilder.Clear();
                modelBuilder.withId(jsonObj.optString(Res_id,""));
                modelBuilder.withDoctor_id(jsonObj.optString(Res_doctor_id,""));
                modelBuilder.withStatus(Integer.valueOf(jsonObj.optString(Res_status,"0")).intValue());
                modelBuilder.withTime(jsonObj.optString(Res_time,""));
                modelBuilder.withVisit_reason(jsonObj.optString(Res_visit_reason,""));
                modelBuilder.withFirst_visit(Integer.valueOf(jsonObj.optString(Res_first_visit,"0")).intValue());
                modelBuilder.withVisits(Integer.valueOf(jsonObj.optString(Res_visits,"0")).intValue());

                modelBuilder.withCode(jsonObj.optString(Res_code,""));
                modelBuilder.withAddress(jsonObj.optString(Res_address,""));
                modelBuilder.withInsurance(Integer.valueOf(jsonObj.optString(Res_insurance,"0")).intValue());
                modelBuilder.withInsurance_company(jsonObj.optString(Res_insurance_company,""));
                modelBuilder.withDoctor_notes(jsonObj.optString(Res_doctor_notes,""));
                modelBuilder.withPatient_notes(jsonObj.optString(Res_patient_notes,""));
                modelBuilder.withCreated_at(jsonObj.optString(Res_created_at,""));

                Object PatientObj = jsonObj.get(Res_patient);
                if(PatientObj instanceof JSONObject) {
                    JSONObject PatientjsonObj = (JSONObject) PatientObj;
                    modelBuilder.withPatient_full_name(PatientjsonObj.optString(Res_full_name,""));
                    modelBuilder.withPatient_gender(Integer.valueOf(PatientjsonObj.optString(Res_gender,"0")).intValue());
                    modelBuilder.withPatient_phone(PatientjsonObj.optString(Res_phone,""));
                    modelBuilder.withPatient_birth_date(PatientjsonObj.optString(Res_birth_date,""));
                    modelBuilder.withPatient_email(PatientjsonObj.optString(Res_email,""));
                    modelBuilder.withPatient_id(PatientjsonObj.optString(Res_id,""));
                    modelBuilder.withPatient_avatar(PatientjsonObj.optString(Res_avatar,""));
                    modelBuilder.withPatient_avatarThumb(PatientjsonObj.optString(Res_avatar_thumb,""));
                }

                listModel.getListAppointments().add(modelBuilder.build());

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
                mCallback.onWSResponseFailed(new WSError(e.getMessage(),getRequestTitle()));
        }
        catch (Exception e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage(),getRequestTitle()));
        }
    }

    private void parseResponseForAcceptAppointments(String jsonResponse){
        AppoinmentWSBuilder modelBuilder = new  AppoinmentWSBuilder();
        modelBuilder.withAction(AppConstants.APPOINTMENT_ACTION.ACCEPT);
        modelBuilder.withId(mParam.getAppointmentId());
        if(mCallback!=null)
            mCallback.onWSResponseOK(modelBuilder.build());
    }

    private void parseResponseForCancelAppointments(String jsonResponse){
        AppoinmentWSBuilder modelBuilder = new  AppoinmentWSBuilder();
        modelBuilder.withAction(AppConstants.APPOINTMENT_ACTION.CANCEL);
        modelBuilder.withId(mParam.getAppointmentId());
        if(mCallback!=null)
            mCallback.onWSResponseOK(modelBuilder.build());
    }

    private void parseResponseForChangeAppointments(String jsonResponse){
        AppoinmentWSBuilder modelBuilder = new  AppoinmentWSBuilder();
        modelBuilder.withAction(AppConstants.APPOINTMENT_ACTION.CHANGE);
        modelBuilder.withId(mParam.getAppointmentId());
        if(mCallback!=null)
            mCallback.onWSResponseOK(modelBuilder.build());
    }

    private void parseResponseForViewAppointments(String jsonResponse){
        try {
            AppoinmentWSBuilder modelBuilder = new  AppoinmentWSBuilder();

            JSONObject jsonBigObj = new JSONObject(jsonResponse);
            JSONObject jsonObj = (JSONObject)jsonBigObj.get(Res_appointment);
            if(jsonObj!=null){
                modelBuilder.withId(jsonObj.optString(Res_id,""));
                modelBuilder.withStatus(Integer.valueOf(jsonObj.optString(Res_status,"0")).intValue());
                modelBuilder.withTime(jsonObj.optString(Res_time,""));
                modelBuilder.withVisit_reason(jsonObj.optString(Res_visit_reason,""));
                modelBuilder.withFirst_visit(Integer.valueOf(jsonObj.optString(Res_first_visit,"0")).intValue());

                modelBuilder.withCode(jsonObj.optString(Res_code,""));
                modelBuilder.withAddress(jsonObj.optString(Res_address,""));
                modelBuilder.withInsurance(Integer.valueOf(jsonObj.optString(Res_insurance,"0")).intValue());
                modelBuilder.withInsurance_company(jsonObj.optString(Res_insurance_company,""));
                modelBuilder.withDoctor_notes(jsonObj.optString(Res_doctor_notes,""));
                modelBuilder.withPatient_notes(jsonObj.optString(Res_patient_notes,""));
                modelBuilder.withCreated_at(jsonObj.optString(Res_created_at,""));
                modelBuilder.withExamine_for(jsonObj.optString(Res_examine_for,""));
                modelBuilder.withAction(AppConstants.APPOINTMENT_ACTION.VIEWDETAIL);
                Object PatientObj = jsonObj.get(Res_patient);
                if(PatientObj instanceof JSONObject) {
                    JSONObject PatientjsonObj = (JSONObject) PatientObj;
                    modelBuilder.withPatient_full_name(PatientjsonObj.optString(Res_full_name,""));
                    modelBuilder.withPatient_gender(Integer.valueOf(PatientjsonObj.optString(Res_gender,"0")).intValue());
                    modelBuilder.withPatient_phone(PatientjsonObj.optString(Res_phone,""));
                    modelBuilder.withPatient_birth_date(PatientjsonObj.optString(Res_birth_date,""));
                    modelBuilder.withPatient_email(PatientjsonObj.optString(Res_email,""));
                    modelBuilder.withPatient_id(PatientjsonObj.optString(Res_id,""));
                    modelBuilder.withPatient_avatar(PatientjsonObj.optString(Res_avatar,""));
                    modelBuilder.withPatient_avatarThumb(PatientjsonObj.optString(Res_avatar_thumb,""));
                }

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

    private void parseResponseForUpdateDoctorNote(String jsonResponse){
        AppoinmentWSBuilder modelBuilder = new  AppoinmentWSBuilder();
        modelBuilder.withAction(AppConstants.APPOINTMENT_ACTION.UPDATE_DOCTOR_NOTE);
        modelBuilder.withId(mParam.getAppointmentId());
        if(mCallback!=null)
            mCallback.onWSResponseOK(modelBuilder.build());
    }
}
