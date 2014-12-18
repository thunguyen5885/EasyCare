package vn.easycare.layers.ui.models.base;

import java.util.List;

import vn.easycare.layers.ui.components.data.PatientManagementItemData;

/**
 * Created by phan on 12/16/2014.
 */
public interface IPatientManagementModel extends IBaseModel {
    List<PatientManagementItemData> getAllAvailablePatientsForDoctor(String doctorID);
    List<PatientManagementItemData> getAllBlockedPatientForDoctor(String doctorID);
    boolean doBlockAPatient(String doctorID, String patientID);
}
