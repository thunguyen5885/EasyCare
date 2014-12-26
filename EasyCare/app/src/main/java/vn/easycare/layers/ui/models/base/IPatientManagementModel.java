package vn.easycare.layers.ui.models.base;

import java.util.List;

import vn.easycare.layers.ui.components.data.PatientManagementItemData;

/**
 * Created by phan on 12/16/2014.
 */
public interface IPatientManagementModel extends IBaseModel {
    List<PatientManagementItemData> getAllAvailablePatientsForDoctor(int page);
    List<PatientManagementItemData> getAllBlockedPatientForDoctor(int page);
    boolean doBlockAPatient(String patientID);
    boolean doUnblockAPatient(String patientID);
}
