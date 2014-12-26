package vn.easycare.layers.ui.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.easycare.layers.services.IWSResponse;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.models.base.IBaseModel;
import vn.easycare.layers.ui.models.base.IExaminationAppointmentModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/13/14.
 */
public class ExaminationAppointmentModel implements IExaminationAppointmentModel {
    private Context mContext;


    public  ExaminationAppointmentModel(Context context){
        mContext = context;
    }
    @Override
    public List<ExaminationAppointmentItemData> getExaminationAppointmentsForDoctor(AppConstants.EXAMINATION_STATUS status, int page) {
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
    public List<ExaminationAppointmentItemData> doSearchExaminationAppointments( String appointmentCode, String patientName, AppConstants.EXAMINATION_STATUS status, String date, String startDate, String endDate, int page) {
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
    public boolean doAcceptAnExaminationAppointment(String appointmentID) {
        return true;
    }

    @Override
    public boolean doCancelAnExaminationAppointment(String appointmentID) {
        return true;
    }

    @Override
    public ExaminationAppointmentItemData getAnExaminationAppointmentForDoctor(String appointmentID) {
        return null;
    }

    @Override
    public boolean doChangeAnExaminationAppointment(String appointmentID, Date newDateTime, int oldAddressID, int addressChangeID, String doctorNotes) {
        return true;
    }

    @Override
    public void setResponseCallback(IResponseUIDataCallback callback) {

    }
}
