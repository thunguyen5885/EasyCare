package vn.easycare.layers.ui.presenters;

import android.content.Context;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import vn.easycare.layers.ui.components.data.DoctorClinicAddressItemData;
import vn.easycare.layers.ui.components.data.ExaminationScheduleItemData;
import vn.easycare.layers.ui.components.data.base.IBaseItemData;
import vn.easycare.layers.ui.models.ExaminationSchedulesModel;
import vn.easycare.layers.ui.models.base.IBaseModel;
import vn.easycare.layers.ui.models.base.IExaminationSchedulesModel;
import vn.easycare.layers.ui.presenters.base.IExaminationSchedulesPresenter;
import vn.easycare.layers.ui.views.IExaminationSchedulesView;
import vn.easycare.utils.AppConstants;

/**
 * Created by phan on 12/24/2014.
 */
public class ExaminationSchedulesPresenterImpl implements IExaminationSchedulesPresenter,IBaseModel.IResponseUIDataCallback {
    private IExaminationSchedulesView iView;
    private IExaminationSchedulesModel iModel;
    Context mContext;

    public ExaminationSchedulesPresenterImpl(IExaminationSchedulesView view,Context context){
        iView = view;
        iModel = new ExaminationSchedulesModel(context,this);
        mContext = context;
    }

    @Override
    public void loadAllExaminationSchedulesForSpecificDate(String date) {
        iModel.getAllExaminationSchedulesForSpecificDate(date);

    }

    @Override
    public void loadAnExaminationSchedule(String scheduleId) {
        iModel.getAnExaminationSchedule(scheduleId);

    }

    @Override
    public void createNewExaminationSchedule(ExaminationScheduleItemData itemData) {
        iModel.doCreateNewExaminationSchedule(itemData);

    }

    @Override
    public void updateExaminationSchedule(ExaminationScheduleItemData itemData) {
        iModel.doUpdateExaminationSchedule(itemData);

    }

    @Override
    public void deleteExaminationSchedule(String scheduleId) {
        iModel.doDeleteExaminationSchedule(scheduleId);
    }

    @Override
    public void loadAllAddressesForDoctor() {
        iModel.loadAllAddressesForDoctor();
      //  iView.DisplayAllDoctorClinicAddresses();
    }


    @Override
    public <T extends IBaseItemData> void onResponseOK(T itemData, Class<T>... itemDataClass) {
        ExaminationScheduleItemData scheduleItemData = (ExaminationScheduleItemData)itemData;
        if(scheduleItemData.getAction()== AppConstants.SCHEDULE_ACTION.VIEWDETAIL)
            iView.DisplayAnExaminationSchedule(scheduleItemData);
        else if(scheduleItemData.getAction()== AppConstants.SCHEDULE_ACTION.CREATE ||
                scheduleItemData.getAction()== AppConstants.SCHEDULE_ACTION.UPDATE ||
                scheduleItemData.getAction()== AppConstants.SCHEDULE_ACTION.DELETE)
            iView.DisplayMessageForScheduleActionComplete("");
    }

    @Override
    public <T extends IBaseItemData> void onResponseOK(List<T> itemDataList, Class<T>... itemDataClass) {
        if(itemDataClass[0].equals(ExaminationScheduleItemData.class)){
            List<ExaminationScheduleItemData> itemScheduleList = (List<ExaminationScheduleItemData>)itemDataList;
            iView.DisplayAllExaminationSchedulesForSeletedDate(itemScheduleList);
        }else if(itemDataClass[0].equals(DoctorClinicAddressItemData.class)){
            List<DoctorClinicAddressItemData> itemAddressesList = (List<DoctorClinicAddressItemData>)itemDataList;
            iView.DisplayAllDoctorClinicAddresses(itemAddressesList);
        }
    }

    @Override
    public void onResponseFail(String message) {
        iView.DisplayMessageIncaseError(message);
    }
}
