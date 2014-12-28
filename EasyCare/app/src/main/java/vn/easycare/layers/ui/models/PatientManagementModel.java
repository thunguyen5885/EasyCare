package vn.easycare.layers.ui.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.layers.services.IWSResponse;
import vn.easycare.layers.services.IWebServiceAccess;
import vn.easycare.layers.services.IWebServiceModel;
import vn.easycare.layers.services.WSAccessFactory;
import vn.easycare.layers.services.WSDataSingleton;
import vn.easycare.layers.services.WSError;
import vn.easycare.layers.services.concretes.PatientWSAccess;
import vn.easycare.layers.services.models.PatientListWSModel;
import vn.easycare.layers.services.models.PatientWSModel;
import vn.easycare.layers.services.models.PatientWSParamModel;
import vn.easycare.layers.ui.components.data.PatientManagementItemData;
import vn.easycare.layers.ui.models.base.IBaseModel;
import vn.easycare.layers.ui.models.base.IPatientManagementModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phan on 12/16/2014.
 */
public class PatientManagementModel implements IPatientManagementModel,IWSResponse{
    private Context mContext;
    private IResponseUIDataCallback mCallback;

    public  PatientManagementModel(Context context,IResponseUIDataCallback callback){
        mContext = context;
        mCallback = callback;
    }

    @Override
    public void getAllAvailablePatientsForDoctor(int page) {
        try {
            IWebServiceAccess<PatientListWSModel,PatientWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    PatientWSAccess.class,
                    mContext,
                    this,
                    new PatientWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken(),"0",page+"", AppConstants.PATIENT_ACTION.NONE,""));
            WS.sendRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private List<PatientManagementItemData> fakeDateTesting(int page){
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
    public void getAllBlockedPatientForDoctor(int page) {
        try {
            IWebServiceAccess<PatientListWSModel,PatientWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    PatientWSAccess.class,
                    mContext,
                    this,
                    new PatientWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken(),"1",page+"",AppConstants.PATIENT_ACTION.NONE,""));
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
    public void doBlockAPatient(String patientID) {

        try {
            IWebServiceAccess<PatientListWSModel,PatientWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    PatientWSAccess.class,
                    mContext,
                    this,
                    new PatientWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken(),"","",AppConstants.PATIENT_ACTION.BAN,patientID));
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
    public void doUnblockAPatient(String patientID) {

        try {
            IWebServiceAccess<PatientListWSModel,PatientWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    PatientWSAccess.class,
                    mContext,
                    this,
                    new PatientWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken(),"","",AppConstants.PATIENT_ACTION.UNBAN,patientID));
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
    public void onWSResponseOK(IWebServiceModel result) {
        if (result instanceof PatientListWSModel) {
            PatientListWSModel modelList = (PatientListWSModel)result;
            List<PatientManagementItemData> itemDataList = new ArrayList<PatientManagementItemData>();
            for(PatientWSModel patientModel : modelList.getListPatients()){
                PatientManagementItemData item = new PatientManagementItemData();
                item.setPatientId(patientModel.getId());
                item.setPatientAddress(patientModel.getAddress());
                item.setPatientAvatar(patientModel.getAvatar());
                item.setPatientAvatarThumb(patientModel.getAvatar_thumb());
                item.setPatientBirthday(patientModel.getBirthday());
                item.setPatientBlocked(patientModel.getBanned()==1?true:false);
                item.setPatientCancelCount(patientModel.getCancelVisits());
                item.setPatientChangeCount(patientModel.getNumberChangeAppointment());
                item.setPatientCommentCount(patientModel.getTotalComment());
                item.setPatientEmailAddress(patientModel.getEmail());
                item.setPatientOrderCount(patientModel.getVisits());
                item.setPatientName(patientModel.getFull_name());
                item.setPatientPhoneNumber(patientModel.getPhone());
                item.setCurrentPage(modelList.getPage_currentPage());
                item.setLastPage(modelList.getLastPage());
                item.setItemsPerPage(modelList.getItemsPerPage());
                item.setTotalPages(modelList.getPage_total());
                itemDataList.add(item);
            }
            if(mCallback!=null)
                mCallback.onResponseOK(itemDataList);
        }else if(result instanceof PatientWSModel){
            PatientWSModel patientModel = (PatientWSModel)result;
            PatientManagementItemData item = new PatientManagementItemData();
            item.setPatientId(patientModel.getId());
            item.setPatientBlocked(patientModel.getBanned()==1?true:false);
            if(mCallback!=null)
                mCallback.onResponseOK(item);
        }

    }

    @Override
    public void onWSResponseFailed(WSError error) {
        mCallback.onResponseFail(error.getErrorMessage());
    }
}
