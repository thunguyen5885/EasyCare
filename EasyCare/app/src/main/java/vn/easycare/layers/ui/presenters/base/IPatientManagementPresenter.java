package vn.easycare.layers.ui.presenters.base;

import vn.easycare.layers.ui.models.base.IPatientManagementModel;
import vn.easycare.layers.ui.views.IPatientManagementView;

/**
 * Created by phan on 12/16/2014.
 */
public interface IPatientManagementPresenter extends IPresenter<IPatientManagementView> {
    void loadAllAvailablePatientsForDoctor(String doctorID);
    void loadAllBlockedPatientsForDoctor(String doctorID);
    void blockAPatient(String doctorID, String patientID);
    void showAllAppointmentsForAPatient(String doctorID,String patientID);
}
