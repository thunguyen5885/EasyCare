package vn.easycare.layers.ui.presenters;

import android.content.Context;

import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.models.ExaminationSchedulesModel;
import vn.easycare.layers.ui.models.base.IExaminationSchedulesModel;
import vn.easycare.layers.ui.presenters.base.IExaminationSchedulesPresenter;
import vn.easycare.layers.ui.views.IExaminationSchedulesView;

/**
 * Created by phan on 12/24/2014.
 */
public class ExaminationSchedulesPresenterImpl implements IExaminationSchedulesPresenter{
    private IExaminationSchedulesView iView;
    private IExaminationSchedulesModel iModel;
    Context mContext;

    public ExaminationSchedulesPresenterImpl(IExaminationSchedulesView view,Context context){
        iView = view;
        iModel = new ExaminationSchedulesModel(context);
        mContext = context;
    }

    @Override
    public void loadAllExaminationSchedulesForSpecificDate(String date) {
        iView.DisplayAllExaminationSchedulesForSeletedDate(iModel.getAllExaminationSchedulesForSpecificDate(date));
    }

    @Override
    public void loadAnExaminationSchedule(String examinationId) {
        iView.DisplayAnExaminationSchedule(iModel.getAnExaminationSchedule(examinationId));
    }

    @Override
    public void createNewExaminationSchedule(ExaminationAppointmentItemData itemData) {
        boolean isCreated = iModel.doCreateNewExaminationSchedule(itemData);
        if(isCreated){
            iView.DisplayMessageForScheduleAction("");
        }else{
            iView.DisplayMessageForScheduleAction("");
        }
    }

    @Override
    public void updateExaminationSchedule(ExaminationAppointmentItemData itemData) {
        boolean isUpdated = iModel.doUpdateExaminationSchedule(itemData);
        if(isUpdated){
            iView.DisplayMessageForScheduleAction("");
        }else{
            iView.DisplayMessageForScheduleAction("");
        }
    }

    @Override
    public void deleteExaminationSchedule(String examinationId) {
        boolean isDeleted = iModel.doDeleteExaminationSchedule(examinationId);
        if(isDeleted){
            iView.DisplayMessageForScheduleAction("");
        }else{
            iView.DisplayMessageForScheduleAction("");
        }
    }

    @Override
    public void loadAllAddressesForDoctor() {
        iView.DisplayAllDoctorClinicAddresses(iModel.loadAllAddressesForDoctor());
    }
}
