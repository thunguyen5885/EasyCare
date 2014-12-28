package vn.easycare.layers.ui.presenters;

import android.content.Context;

import java.util.List;

import vn.easycare.layers.ui.components.data.PatientManagementItemData;
import vn.easycare.layers.ui.components.data.base.IBaseItemData;
import vn.easycare.layers.ui.models.CommentAndAssessmentModel;
import vn.easycare.layers.ui.models.PatientManagementModel;
import vn.easycare.layers.ui.models.base.IBaseModel;
import vn.easycare.layers.ui.models.base.ICommentAndAssessmentModel;
import vn.easycare.layers.ui.models.base.IPatientManagementModel;
import vn.easycare.layers.ui.presenters.base.IPatientManagementPresenter;
import vn.easycare.layers.ui.presenters.base.IPresenter;
import vn.easycare.layers.ui.views.ICommentAndAssessmentView;
import vn.easycare.layers.ui.views.IPatientManagementView;

/**
 * Created by phan on 12/16/2014.
 */
public class PatientManagementPresenterImpl implements IPatientManagementPresenter,IBaseModel.IResponseUIDataCallback {
    private IPatientManagementView iView;
    private IPatientManagementModel iModel;
    Context mContext;

    public PatientManagementPresenterImpl(IPatientManagementView view,Context context){
        iView = view;
        iModel = new PatientManagementModel(context,this);
        mContext = context;
    }
    @Override
    public void init(IPatientManagementView view) {

    }

    @Override
    public void loadAllAvailablePatientsForDoctor(int page) {
        iModel.getAllAvailablePatientsForDoctor(page);

    }

    @Override
    public void loadAllBlockedPatientsForDoctor(int page) {
        iModel.getAllBlockedPatientForDoctor(page);

    }

    @Override
    public void blockAPatient(String patientID) {
        iModel.doBlockAPatient(patientID);

    }

    @Override
    public void unblockAPatient(String patientID) {
        iModel.doUnblockAPatient(patientID);

    }

    @Override
    public void showAllAppointmentsForAPatient(String patientID) {
        iView.DisplayAllAppointmentForPatient(patientID);
    }

    @Override
    public void onResponseOK(IBaseItemData itemData) {
        PatientManagementItemData patientItem = (PatientManagementItemData)itemData;
        if(patientItem!=null){
            if(patientItem.isPatientBlocked()){
                iView.DisplayMessageForBlockPatient("");
            }else{
                iView.DisplayMessageForUnblockPatient("");
            }
        }
    }

    @Override
    public void onResponseOK(List<? extends IBaseItemData> itemDataList) {
        List<PatientManagementItemData> itemPatienList = (List<PatientManagementItemData>)itemDataList;
        if(itemPatienList!=null && itemPatienList.size()>0){
            PatientManagementItemData aItem = itemPatienList.get(0);
            if(aItem.isPatientBlocked()){
                iView.DisplayAllBlockedPatientsForDoctor(itemPatienList);
            }else{
                iView.DisplayAllAvailablePatientsForDoctor(itemPatienList);
            }
        }
    }

    @Override
    public void onResponseFail(String message) {
        iView.DisplayMessageIncaseError(message);
    }
}
