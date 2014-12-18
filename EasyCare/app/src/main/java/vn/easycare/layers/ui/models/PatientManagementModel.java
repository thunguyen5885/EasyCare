package vn.easycare.layers.ui.models;

import android.content.Context;

import java.util.List;

import vn.easycare.layers.ui.components.data.PatientManagementItemData;
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
    public List<PatientManagementItemData> getAllAvailablePatientsForDoctor(String doctorID) {
        return null;
    }

    @Override
    public List<PatientManagementItemData> getAllBlockedPatientForDoctor(String doctorID) {
        return null;
    }

    @Override
    public boolean doBlockAPatient(String doctorID, String patientID) {
        return true;
    }
}
