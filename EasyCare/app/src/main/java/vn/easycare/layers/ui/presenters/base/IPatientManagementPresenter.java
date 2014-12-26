package vn.easycare.layers.ui.presenters.base;

import vn.easycare.layers.ui.models.base.IPatientManagementModel;
import vn.easycare.layers.ui.views.IPatientManagementView;

/**
 * Created by phan on 12/16/2014.
 */
public interface IPatientManagementPresenter extends IPresenter<IPatientManagementView> {
    void loadAllAvailablePatientsForDoctor(int page);
    void loadAllBlockedPatientsForDoctor(int page);
    void blockAPatient(String patientID);
    void unblockAPatient(String patientID);
    void showAllAppointmentsForAPatient(String patientID);
}
