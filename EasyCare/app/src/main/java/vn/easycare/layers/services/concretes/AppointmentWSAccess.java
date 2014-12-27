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
import vn.easycare.layers.services.models.CommentAndAssessmentWSParamModel;
import vn.easycare.layers.services.models.builders.AppoinmentWSBuilder;

/**
 * Created by phan on 12/26/2014.
 */
public class AppointmentWSAccess extends AbstractWSAccess<AppointmentListWSModel,AppointmentWSParamModel> {
    private static final String APPOINTMENT_URI = WEBSERVICE_HOST + "/login";
    private static final String Res_appointments = "appointments";
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
    private AppointmentWSParamModel mParam;
    @Override
    public String getWSURL() {
        return APPOINTMENT_URI;
    }

    @Override
    public Map<String, String> getWSParams() {
        Map<String,String> params = new HashMap<String, String>();
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
        return params;
    }

    @Override
    public void setWSParams(IWebServiceParamModel params) {
        mParam = (AppointmentWSParamModel)params;
    }

    @Override
    public int getMethod() {
        return Request.Method.GET;
    }
    @Override
    public void onParseJsonResponseOK(String jsonResponse) {
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

                JSONObject PatientjsonObj = (JSONObject)jsonObj.get(Res_patient);
                modelBuilder.withPatient_full_name(PatientjsonObj.get(Res_full_name).toString());
                modelBuilder.withPatient_gender(Integer.valueOf(jsonObj.get(Res_gender).toString()).intValue());
                modelBuilder.withPatient_phone(PatientjsonObj.get(Res_phone).toString());
                modelBuilder.withPatient_birth_date(PatientjsonObj.get(Res_birth_date).toString());
                modelBuilder.withPatient_email(PatientjsonObj.get(Res_email).toString());
                modelBuilder.withPatient_id(PatientjsonObj.get(Res_id).toString());


                listModel.getListAppointments().add(modelBuilder.build());

            }
            JSONObject pageJsonObj = (JSONObject)jsonBigObj.get(Res_paging);
            listModel.setPage_total(Integer.valueOf(pageJsonObj.get(Res_pagetotal).toString()).intValue());
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
}
