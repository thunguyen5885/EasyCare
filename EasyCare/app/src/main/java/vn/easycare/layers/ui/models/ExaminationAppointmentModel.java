package vn.easycare.layers.ui.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.services.IWSResponse;
import vn.easycare.layers.services.IWebServiceAccess;
import vn.easycare.layers.services.IWebServiceModel;
import vn.easycare.layers.services.WSAccessFactory;
import vn.easycare.layers.services.WSDataSingleton;
import vn.easycare.layers.services.WSError;
import vn.easycare.layers.services.concretes.AppointmentWSAccess;
import vn.easycare.layers.services.concretes.ClinicAddressWSAccess;
import vn.easycare.layers.services.models.AppointmentListWSModel;
import vn.easycare.layers.services.models.AppointmentWSModel;
import vn.easycare.layers.services.models.AppointmentWSParamModel;
import vn.easycare.layers.services.models.ClinicAddressListWSModel;
import vn.easycare.layers.services.models.ClinicAddressWSModel;
import vn.easycare.layers.services.models.ClinicAddressWSParamModel;
import vn.easycare.layers.ui.components.data.DoctorClinicAddressItemData;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.models.base.IExaminationAppointmentModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/13/14.
 */
public class ExaminationAppointmentModel implements IExaminationAppointmentModel,IWSResponse {
    private Context mContext;
    private IResponseUIDataCallback mCallback;

    public  ExaminationAppointmentModel(Context context,IResponseUIDataCallback callback){
        mContext = context;
        mCallback = callback;
    }
    @Override
    public void getExaminationAppointmentsForDoctor(AppConstants.EXAMINATION_STATUS status, String patientId, int page) {
        try {
            IWebServiceAccess<AppointmentListWSModel,AppointmentWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    AppointmentWSAccess.class,
                    mContext,
                    this,
                    new AppointmentWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken(), page+"",
                            "",status.getValue()+"", "", "","", "", patientId, "10","","","","","",AppConstants.APPOINTMENT_ACTION.NONE));
            WS.sendRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private List<ExaminationAppointmentItemData> fakeDataTesting(AppConstants.EXAMINATION_STATUS status, int page){
        List<ExaminationAppointmentItemData> itemDataList = new ArrayList<ExaminationAppointmentItemData>();
        int count = 5;
        if(status == AppConstants.EXAMINATION_STATUS.WAITING){
            count = 10;
        }else if(status == AppConstants.EXAMINATION_STATUS.ACCEPTED){
            count = 15;
        }else if(status == AppConstants.EXAMINATION_STATUS.CANCEL){
            count = 20;
        }
        if(page < 3) {
            for (int index = 0; index < count; index++) {
                ExaminationAppointmentItemData itemData = new ExaminationAppointmentItemData();
                itemData.setPatientAvatar("http://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/thumbs/look_me_in_the_eye.jpg");
                itemData.setPatientName("Bui Hieu");
                itemData.setPatientGender("Nam");
                itemData.setPatientPhone("0589452478");
                itemData.setPatientEmail("abc@easycare.vn");
                itemData.setExaminationDateTime("19/11/2014 11:45");
                itemData.setExaminationCode("CODE123456");
                itemData.setExaminationByPerson("Bui Hieu");
                itemData.setExaminationAddress("Phong kham Bach Mai");
                itemData.setExaminationReason("Viem co xuong");
                itemData.setExaminationDateTimeAppointmentCreated("17/11/2014 10:03");
                itemData.setExaminationState("Chap nhan kham");
                itemData.setExaminationExtraInfo("Can chan doan");
                itemData.setExaminationDoctorNote("Can kiem tra ki hon");
                itemData.setExaminationFirstVisit(true);
                itemDataList.add(itemData);
            }
        }
        return itemDataList;
    }
    @Override
    public void doSearchExaminationAppointments( String appointmentCode, String patientName,String patientId,
                                                 AppConstants.EXAMINATION_STATUS status, String date,
                                                 String startDate, String endDate, int page) {
        try {
            IWebServiceAccess<AppointmentListWSModel,AppointmentWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    AppointmentWSAccess.class,
                    mContext,
                    this,
                    new AppointmentWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken(), page+"",
                            appointmentCode,status.getValue()+"", patientName, date,startDate, endDate, patientId, "10","","","","","",AppConstants.APPOINTMENT_ACTION.NONE));
            WS.sendRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private List<ExaminationAppointmentItemData> fakeDataSearching(String appointmentCode, String patientName, AppConstants.EXAMINATION_STATUS status, String date, String startDate, String endDate, int page){
        List<ExaminationAppointmentItemData> itemDataList = new ArrayList<ExaminationAppointmentItemData>();
        int count = 5;
        if(status == AppConstants.EXAMINATION_STATUS.WAITING){
            count = 10;
        }else if(status == AppConstants.EXAMINATION_STATUS.ACCEPTED){
            count = 15;
        }else if(status == AppConstants.EXAMINATION_STATUS.CANCEL){
            count = 20;
        }
        if(page < 3) {
            for (int index = 0; index < count; index++) {
                ExaminationAppointmentItemData itemData = new ExaminationAppointmentItemData();
                itemData.setPatientAvatar("http://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/thumbs/look_me_in_the_eye.jpg");
                itemData.setPatientName("Bui Van A");
                itemData.setPatientGender("Nam");
                itemData.setPatientPhone("0589484778");
                itemData.setPatientEmail("abc@easycare.vn");
                itemData.setExaminationDateTime("19/12/2014 11:45");
                itemData.setExaminationCode("CODE123456789");
                itemData.setExaminationByPerson("Bui Hieu");
                itemData.setExaminationAddress("Phong kham Hoa Hong");
                itemData.setExaminationReason("Viem co xuong");
                itemData.setExaminationDateTimeAppointmentCreated("17/12/2014 10:03");
                itemData.setExaminationState("Chap nhan kham");
                itemData.setExaminationExtraInfo("Can chan doan");
                itemData.setExaminationDoctorNote("Can kiem tra ki hon");
                itemData.setExaminationFirstVisit(false);
                itemDataList.add(itemData);
            }
        }
        return itemDataList;
    }
    @Override
    public void doAcceptAnExaminationAppointment(String appointmentID) {

        try {
            IWebServiceAccess<AppointmentListWSModel,AppointmentWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    AppointmentWSAccess.class,
                    mContext,
                    this,
                    new AppointmentWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken(), "",
                            "","", "", "","", "", "", "10",appointmentID,"","","","",AppConstants.APPOINTMENT_ACTION.ACCEPT));
            WS.sendRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void doCancelAnExaminationAppointment(String appointmentID) {
        try {
            IWebServiceAccess<AppointmentListWSModel,AppointmentWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    AppointmentWSAccess.class,
                    mContext,
                    this,
                    new AppointmentWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken(), "",
                            "","", "", "","", "", "", "10",appointmentID,"","","","",AppConstants.APPOINTMENT_ACTION.CANCEL));
            WS.sendRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void getAnExaminationAppointmentForDoctor(String appointmentID) {
        try {
            IWebServiceAccess<AppointmentListWSModel,AppointmentWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    AppointmentWSAccess.class,
                    mContext,
                    this,
                    new AppointmentWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken(), "",
                            "","", "", "","", "", "", "10",appointmentID,"","","","",AppConstants.APPOINTMENT_ACTION.VIEWDETAIL));
            WS.sendRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void doChangeAnExaminationAppointment(String appointmentID, String date,String time, int addressChangeID) {
        try {
            IWebServiceAccess<AppointmentListWSModel,AppointmentWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    AppointmentWSAccess.class,
                    mContext,
                    this,
                    new AppointmentWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken(), "",
                            "","", "", "","", "", "", "10",appointmentID,addressChangeID+"",date,time,"",AppConstants.APPOINTMENT_ACTION.CHANGE));
            WS.sendRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void loadAllAddressesForDoctor() {
        try {
            IWebServiceAccess<ClinicAddressListWSModel,ClinicAddressWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    ClinicAddressWSAccess.class,
                    mContext,
                    this,
                    new ClinicAddressWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken()));
            WS.sendRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void setResponseCallback(IResponseUIDataCallback callback) {

    }

    @Override
    public void doUpdateDoctorNotes(String appointmentID, String doctorNotes) {
        try {
            IWebServiceAccess<AppointmentListWSModel,AppointmentWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    AppointmentWSAccess.class,
                    mContext,
                    this,
                    new AppointmentWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken(), "",
                            "","", "", "","", "", "", "10",appointmentID,"","","",doctorNotes,AppConstants.APPOINTMENT_ACTION.UPDATE_DOCTOR_NOTE));
            WS.sendRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onWSResponseOK(IWebServiceModel result) {
        if(result instanceof AppointmentListWSModel){
            AppointmentListWSModel modelList = (AppointmentListWSModel)result;
            List<ExaminationAppointmentItemData> itemDataList = new ArrayList<ExaminationAppointmentItemData>();
            for(AppointmentWSModel appointmentModel : modelList.getListAppointments()){
                ExaminationAppointmentItemData item = new ExaminationAppointmentItemData();
                item.setExaminationId(appointmentModel.getId());
                item.setPatientName(appointmentModel.getPatient_full_name());
                item.setExaminationDateTime(appointmentModel.getTime());
                item.setExaminationReason(appointmentModel.getVisit_reason());
                if(appointmentModel.getStatus() == 0) {
                    item.setExaminationStatus(AppConstants.EXAMINATION_STATUS.WAITING);
                    item.setExaminationState(mContext.getResources().getString(R.string.appointment_status_waiting));
                }else if(appointmentModel.getStatus() == 1 || appointmentModel.getStatus() == 3){
                    item.setExaminationStatus(AppConstants.EXAMINATION_STATUS.ACCEPTED);
                    item.setExaminationState(mContext.getResources().getString(R.string.appointment_status_approved));
                }else if(appointmentModel.getStatus() == -1 || appointmentModel.getStatus() == -2 || appointmentModel.getStatus() == -3){
                    item.setExaminationStatus(AppConstants.EXAMINATION_STATUS.CANCEL);
                    item.setExaminationState(mContext.getResources().getString(R.string.appointment_status_cancel));
                }else if(appointmentModel.getStatus() == -4){
                    item.setExaminationStatus(AppConstants.EXAMINATION_STATUS.MISSING);
                    item.setExaminationState(mContext.getResources().getString(R.string.appointment_status_missing));
                }
                item.setPatientGender(appointmentModel.getPatient_gender() == 1 ? mContext.getResources().getString(R.string.gender_man):mContext.getResources().getString(R.string.gender_women));
                item.setPatientPhone(appointmentModel.getPatient_phone());
                item.setPatientEmail(appointmentModel.getPatient_email());
                item.setExaminationCode(appointmentModel.getCode());
                item.setExaminationAddress(appointmentModel.getAddress());
                item.setExaminationByPerson(appointmentModel.getExamine_for());
                item.setExaminationDateTimeAppointmentCreated(appointmentModel.getCreated_at());
                item.setPatientAvatar(appointmentModel.getPatient_avatar());
                item.setPatientAvatarThumb(appointmentModel.getPatient_avatarThumb());
             /*?????????????
                item.setExaminationExtraInfo(appointmentModel.getLastPage());
                ??????????
                */
                item.setExaminationDoctorNote(appointmentModel.getDoctor_notes());
                item.setExaminationFirstVisit(appointmentModel.getFirst_visit()==0?false:true);
                item.setCurrentPage(modelList.getPage_currentPage());
                item.setLastPage(modelList.getLastPage());
                item.setItemsPerPage(modelList.getItemsPerPage());
                item.setTotalItems(modelList.getItems_total());
                itemDataList.add(item);
            }
            if(mCallback!=null)
                mCallback.onResponseOK(itemDataList,ExaminationAppointmentItemData.class);

        }
        else if(result instanceof AppointmentWSModel){
            AppointmentWSModel appointmentModel = (AppointmentWSModel)result;
            ExaminationAppointmentItemData item = new ExaminationAppointmentItemData();
            item.setAction(appointmentModel.getAction());
            item.setExaminationId(appointmentModel.getId());
            //view detail appointment need more info
            item.setPatientName(appointmentModel.getPatient_full_name());
            item.setExaminationDateTime(appointmentModel.getTime());
            item.setExaminationReason(appointmentModel.getVisit_reason());
            if(appointmentModel.getStatus() == 0) {
                item.setExaminationStatus(AppConstants.EXAMINATION_STATUS.WAITING);
                item.setExaminationState(mContext.getResources().getString(R.string.appointment_status_waiting));
            }else if(appointmentModel.getStatus() == 1 || appointmentModel.getStatus() == 3){
                item.setExaminationStatus(AppConstants.EXAMINATION_STATUS.ACCEPTED);
                item.setExaminationState(mContext.getResources().getString(R.string.appointment_status_approved));
            }else if(appointmentModel.getStatus() == -1 || appointmentModel.getStatus() == -2 || appointmentModel.getStatus() == -3){
                item.setExaminationStatus(AppConstants.EXAMINATION_STATUS.CANCEL);
                item.setExaminationState(mContext.getResources().getString(R.string.appointment_status_cancel));
            }else if(appointmentModel.getStatus() == -4){
                item.setExaminationStatus(AppConstants.EXAMINATION_STATUS.MISSING);
                item.setExaminationState(mContext.getResources().getString(R.string.appointment_status_missing));
            }
            item.setPatientGender(appointmentModel.getPatient_gender() == 1 ? mContext.getResources().getString(R.string.gender_man):mContext.getResources().getString(R.string.gender_women));
            item.setPatientPhone(appointmentModel.getPatient_phone());
            item.setPatientEmail(appointmentModel.getPatient_email());
            item.setExaminationCode(appointmentModel.getCode());
            item.setExaminationAddress(appointmentModel.getAddress());
            item.setExaminationByPerson(appointmentModel.getExamine_for());
            item.setExaminationDateTimeAppointmentCreated(appointmentModel.getCreated_at());
            item.setPatientAvatar(appointmentModel.getPatient_avatar());
            item.setPatientAvatarThumb(appointmentModel.getPatient_avatarThumb());
                /*?????????????
                item.setExaminationExtraInfo(appointmentModel.getLastPage());
                ??????????
                */
            item.setExaminationDoctorNote(appointmentModel.getDoctor_notes());
            item.setExaminationFirstVisit(appointmentModel.getFirst_visit()==0?false:true);

            if(mCallback!=null)
                mCallback.onResponseOK(item,ExaminationAppointmentItemData.class);
        }else if(result instanceof ClinicAddressListWSModel){
            ClinicAddressListWSModel addressListModel = (ClinicAddressListWSModel)result;
            List<DoctorClinicAddressItemData> itemDataList = new ArrayList<DoctorClinicAddressItemData>();
            for(ClinicAddressWSModel model : addressListModel.getClinicAddressesList()){
                DoctorClinicAddressItemData item = new DoctorClinicAddressItemData();
                item.setClinicAddress(model.getClinicAddress());
                item.setClinicAddressId(model.getClinicAddressId());
                itemDataList.add(item);
            }
            if(mCallback!=null)
                mCallback.onResponseOK(itemDataList,DoctorClinicAddressItemData.class);
        }

    }

    @Override
    public void onWSResponseFailed(WSError error) {
        if(error.getStatusCode() == AppConstants.HTTP_STATUS_UNAUTHORIZED)
            mCallback.onUnauthorized();
        else
            mCallback.onResponseFail(error.getErrorMessage(),error.getFunctionTitle());
    }
}
