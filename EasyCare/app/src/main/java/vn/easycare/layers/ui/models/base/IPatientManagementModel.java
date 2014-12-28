package vn.easycare.layers.ui.models.base;

import java.util.List;

import vn.easycare.layers.ui.components.data.PatientManagementItemData;

/**
 * Created by phan on 12/16/2014.
 */
public interface IPatientManagementModel extends IBaseModel {
    void getAllAvailablePatientsForDoctor(int page);
    void getAllBlockedPatientForDoctor(int page);
    void doBlockAPatient(String patientID);
    void doUnblockAPatient(String patientID);
}
