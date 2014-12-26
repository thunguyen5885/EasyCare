package vn.easycare.layers.ui.presenters;

import android.content.Context;

import vn.easycare.layers.ui.models.CommentAndAssessmentModel;
import vn.easycare.layers.ui.models.PatientManagementModel;
import vn.easycare.layers.ui.models.base.ICommentAndAssessmentModel;
import vn.easycare.layers.ui.models.base.IPatientManagementModel;
import vn.easycare.layers.ui.presenters.base.IPatientManagementPresenter;
import vn.easycare.layers.ui.presenters.base.IPresenter;
import vn.easycare.layers.ui.views.ICommentAndAssessmentView;
import vn.easycare.layers.ui.views.IPatientManagementView;

/**
 * Created by phan on 12/16/2014.
 */
public class PatientManagementPresenterImpl implements IPatientManagementPresenter {
    private IPatientManagementView iView;
    private IPatientManagementModel iModel;
    Context mContext;

    public PatientManagementPresenterImpl(IPatientManagementView view,Context context){
        iView = view;
        iModel = new PatientManagementModel(context);
        mContext = context;
    }
    @Override
    public void init(IPatientManagementView view) {

    }

    @Override
    public void loadAllAvailablePatientsForDoctor(int page) {
        iView.DisplayAllAvailablePatientsForDoctor(iModel.getAllAvailablePatientsForDoctor(page));
    }

    @Override
    public void loadAllBlockedPatientsForDoctor(int page) {
        iView.DisplayAllBlockedPatientsForDoctor(iModel.getAllBlockedPatientForDoctor(page));
    }

    @Override
    public void blockAPatient(String patientID) {
        boolean isBlocked = iModel.doBlockAPatient(patientID);
        if(isBlocked){
            iView.DisplayMessageForBlockPatient("");
        }else{
            iView.DisplayMessageForBlockPatient("");
        }
    }

    @Override
    public void unblockAPatient(String patientID) {
        boolean isBlocked = iModel.doUnblockAPatient(patientID);
        if(isBlocked){
            iView.DisplayMessageForUnblockPatient("");
        }else{
            iView.DisplayMessageForUnblockPatient("");
        }
    }

    @Override
    public void showAllAppointmentsForAPatient(String patientID) {
        iView.DisplayAllAppointmentForPatient(patientID);
    }
}
