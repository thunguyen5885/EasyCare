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
    private static final String APPOINTMENT_CHANGE_URI = WEBSERVICE_HOST + "/doctors/appointments/update?token=%s&id=%s&date=%s&time=%s&address=%s";
    private static final String APPOINTMENT_VIEW_URI = WEBSERVICE_HOST + "/doctors/appointments/%s?token=%s";

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
    private static final String Param_id = "id";
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
                return  String.format(APPOINTMENT_CHANGE_URI,mParam.getToken(),mParam.getAppointmentId(),mParam.getDate(),mParam.getTime(),mParam.getAddress());
            case VIEWDETAIL:
                return  String.format(APPOINTMENT_VIEW_URI,mParam.getAppointmentId(),mParam.getToken());
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
                return Request.Method.GET;
            case VIEWDETAIL:
                return Request.Method.GET;
            default:
                return Request.Method.GET;
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
                modelBuilder.withId(jsonObj.get(Res_id).toString());
                modelBuilder.withDoctor_id(jsonObj.get(Res_doctor_id).toString());
                modelBuilder.withStatus(Integer.valueOf(jsonObj.get(Res_status).toString()).intValue());
                modelBuilder.withTime(jsonObj.get(Res_time).toString());
                modelBuilder.withVisit_reason(jsonObj.get(Res_visit_reason).toString());
                modelBuilder.withFirst_visit(Integer.valueOf(jsonObj.get(Res_first_visit).toString()).intValue());
                modelBuilder.withVisits(Integer.valueOf(jsonObj.get(Res_visits).toString()).intValue());

                modelBuilder.withCode(jsonObj.get(Res_code).toString());
                modelBuilder.withAddress(jsonObj.get(Res_address).toString());
                modelBuilder.withInsurance(Integer.valueOf(jsonObj.get(Res_insurance).toString()).intValue());
                modelBuilder.withInsurance_company(jsonObj.get(Res_insurance_company).toString());
                modelBuilder.withDoctor_notes(jsonObj.get(Res_doctor_notes).toString());
                modelBuilder.withPatient_notes(jsonObj.get(Res_patient_notes).toString());
                modelBuilder.withCreated_at(jsonObj.get(Res_created_at).toString());

                Object PatientObj = jsonObj.get(Res_patient);
                if(PatientObj instanceof JSONObject) {
                    JSONObject PatientjsonObj = (JSONObject) PatientObj;
                    modelBuilder.withPatient_full_name(PatientjsonObj.get(Res_full_name).toString());
                    modelBuilder.withPatient_gender(Integer.valueOf(PatientjsonObj.get(Res_gender).toString()).intValue());
                    modelBuilder.withPatient_phone(PatientjsonObj.get(Res_phone).toString());
                    modelBuilder.withPatient_birth_date(PatientjsonObj.get(Res_birth_date).toString());
                    modelBuilder.withPatient_email(PatientjsonObj.get(Res_email).toString());
                    modelBuilder.withPatient_id(PatientjsonObj.get(Res_id).toString());
                    modelBuilder.withPatient_avatar(PatientjsonObj.get(Res_avatar).toString());
                    modelBuilder.withPatient_avatarThumb(PatientjsonObj.get(Res_avatar_thumb).toString());
                }

                listModel.getListAppointments().add(modelBuilder.build());

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
                modelBuilder.withId(jsonObj.get(Res_id).toString());
                modelBuilder.withStatus(Integer.valueOf(jsonObj.get(Res_status).toString()).intValue());
                modelBuilder.withTime(jsonObj.get(Res_time).toString());
                modelBuilder.withVisit_reason(jsonObj.get(Res_visit_reason).toString());
                modelBuilder.withFirst_visit(Integer.valueOf(jsonObj.get(Res_first_visit).toString()).intValue());

                modelBuilder.withCode(jsonObj.get(Res_code).toString());
                modelBuilder.withAddress(jsonObj.get(Res_address).toString());
                modelBuilder.withInsurance(Integer.valueOf(jsonObj.get(Res_insurance).toString()).intValue());
                modelBuilder.withInsurance_company(jsonObj.get(Res_insurance_company).toString());
                modelBuilder.withDoctor_notes(jsonObj.get(Res_doctor_notes).toString());
                modelBuilder.withPatient_notes(jsonObj.get(Res_patient_notes).toString());
                modelBuilder.withCreated_at(jsonObj.get(Res_created_at).toString());
                modelBuilder.withExamine_for(jsonObj.get(Res_examine_for).toString());
                Object PatientObj = jsonObj.get(Res_patient);
                if(PatientObj instanceof JSONObject) {
                    JSONObject PatientjsonObj = (JSONObject) PatientObj;
                    modelBuilder.withPatient_full_name(PatientjsonObj.get(Res_full_name).toString());
                    modelBuilder.withPatient_gender(Integer.valueOf(PatientjsonObj.get(Res_gender).toString()).intValue());
                    modelBuilder.withPatient_phone(PatientjsonObj.get(Res_phone).toString());
                    modelBuilder.withPatient_birth_date(PatientjsonObj.get(Res_birth_date).toString());
                    modelBuilder.withPatient_email(PatientjsonObj.get(Res_email).toString());
                    modelBuilder.withPatient_id(PatientjsonObj.get(Res_id).toString());
                    modelBuilder.withPatient_avatar(PatientjsonObj.get(Res_avatar).toString());
                    modelBuilder.withPatient_avatarThumb(PatientjsonObj.get(Res_avatar_thumb).toString());
                }

            }
            if(mCallback!=null)
                mCallback.onWSResponseOK(modelBuilder.build());
        } catch (JSONException e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage()));
        }
        catch (Exception e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage()));
        }
    }
}
