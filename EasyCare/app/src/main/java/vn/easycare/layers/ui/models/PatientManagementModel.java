package vn.easycare.layers.ui.models;

import android.content.Context;

import vn.easycare.layers.ui.models.base.IPatientManagementModel;

/**
 * Created by phan on 12/16/2014.
 */
public class PatientManagementModel implements IPatientManagementModel{
    private Context mContext;
    public  PatientManagementModel(Context context){
        mContext = context;
    }
}
