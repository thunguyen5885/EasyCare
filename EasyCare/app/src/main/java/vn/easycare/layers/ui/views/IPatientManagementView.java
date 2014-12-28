package vn.easycare.layers.ui.views;

import java.util.List;

import vn.easycare.layers.ui.components.data.CommentAndAssessmentItemData;
import vn.easycare.layers.ui.components.data.PatientManagementItemData;
import vn.easycare.layers.ui.views.base.IBaseView;

/**
 * Created by phan on 12/16/2014.
 */
public interface IPatientManagementView extends IBaseView {
    void DisplayAllAvailablePatientsForDoctor(List<PatientManagementItemData> patientManagementItemsList);
    void DisplayAllBlockedPatientsForDoctor(List<PatientManagementItemData> patientManagementItemsList);
    void DisplayMessageForBlockPatient(String message);
    void DisplayMessageForUnblockPatient(String message);
    void DisplayAllAppointmentForPatient(String patientID);
    void DisplayMessageIncaseError(String message);
}
