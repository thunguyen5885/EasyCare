package vn.easycare.layers.ui.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.layers.services.IWSResponse;
import vn.easycare.layers.ui.components.data.PatientManagementItemData;
import vn.easycare.layers.ui.models.base.IBaseModel;
import vn.easycare.layers.ui.models.base.IPatientManagementModel;

/**
 * Created by phan on 12/16/2014.
 */
public class PatientManagementModel implements IPatientManagementModel{
    private Context mContext;


    public  PatientManagementModel(Context context){
        mContext = context;
    }

    @Override
    public List<PatientManagementItemData> getAllAvailablePatientsForDoctor(int page) {
        List<PatientManagementItemData> itemDataList = new ArrayList<PatientManagementItemData>();
        if(page < 4){
            for(int index = 0; index < 10; index++){
                PatientManagementItemData itemData = new PatientManagementItemData();
                itemData.setPatientAvatar("http://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/thumbs/look_me_in_the_eye.jpg");
                itemData.setPatientName("Bùi Hiệu");
                itemData.setPatientBirthday("10/02/1980");
                itemData.setPatientEmailAddress("buihieu@easycare.vn");
                itemData.setPatientPhoneNumber("0936587458");
                itemData.setPatientAddress("Số 42, Lê Trọng Tấn, Thanh Xuân - Hà Nội");
                itemData.setPatientOrderCount(108);
                itemData.setPatientCancelCount(30);
                itemData.setPatientChangeCount(50);
                itemData.setPatientCommentCount(28);
                itemDataList.add(itemData);
            }
        }
        return itemDataList;
    }

    @Override
    public List<PatientManagementItemData> getAllBlockedPatientForDoctor(int page) {
        List<PatientManagementItemData> itemDataList = new ArrayList<PatientManagementItemData>();
        /*if(page < 4){
            for(int index = 0; index < 10; index++){
                PatientManagementItemData itemData = new PatientManagementItemData();
                itemData.setPatientAvatar("http://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/thumbs/look_me_in_the_eye.jpg");
                itemData.setPatientName("VIP");
                itemData.setPatientBirthday("10/02/1915");
                itemData.setPatientEmailAddress("vip@gmail.com");
                itemData.setPatientPhoneNumber("09365587458");
                itemData.setPatientAddress("Số 85, Kim Mã, Ba Đình-Hà Nội ");
                itemData.setPatientOrderCount(113);
                itemData.setPatientCancelCount(80);
                itemData.setPatientChangeCount(70);
                itemData.setPatientCommentCount(18);
                itemDataList.add(itemData);
            }
        }*/
        return itemDataList;
    }

    @Override
    public boolean doBlockAPatient(String patientID) {
        return true;
    }

    @Override
    public boolean doUnblockAPatient(String patientID) {
        return true;
    }


    @Override
    public void setResponseCallback(IResponseUIDataCallback callback) {
        
    }
}
