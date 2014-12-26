package vn.easycare.layers.ui.models;

import android.content.Context;

import java.util.List;

import vn.easycare.layers.ui.components.data.DoctorClinicAddressItemData;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.components.data.ExaminationScheduleItemData;
import vn.easycare.layers.ui.models.base.IBaseModel;
import vn.easycare.layers.ui.models.base.IExaminationSchedulesModel;

/**
 * Created by phan on 12/24/2014.
 */
public class ExaminationSchedulesModel implements IExaminationSchedulesModel {
    private Context mContext;
    public  ExaminationSchedulesModel(Context context){
        mContext = context;
    }

    @Override
    public List<ExaminationScheduleItemData> getAllExaminationSchedulesForSpecificDate(String date) {
        return null;
    }

    @Override
    public void setResponseCallback(IResponseUIDataCallback callback) {

    }

    @Override
    public ExaminationScheduleItemData getAnExaminationSchedule(String examinationId) {
        return null;
    }

    @Override
    public boolean doCreateNewExaminationSchedule(ExaminationAppointmentItemData itemData) {
        return false;
    }

    @Override
    public boolean doUpdateExaminationSchedule(ExaminationAppointmentItemData itemData) {
        return false;
    }

    @Override
    public boolean doDeleteExaminationSchedule(String examinationId) {
        return false;
    }

    @Override
    public List<DoctorClinicAddressItemData> loadAllAddressesForDoctor() {
        return null;
    }
}
