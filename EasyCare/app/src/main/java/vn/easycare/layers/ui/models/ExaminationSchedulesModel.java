package vn.easycare.layers.ui.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.layers.services.IWSResponse;
import vn.easycare.layers.services.IWebServiceAccess;
import vn.easycare.layers.services.IWebServiceModel;
import vn.easycare.layers.services.WSAccessFactory;
import vn.easycare.layers.services.WSDataSingleton;
import vn.easycare.layers.services.WSError;
import vn.easycare.layers.services.concretes.ScheduleWSAccess;
import vn.easycare.layers.services.models.PatientWSModel;
import vn.easycare.layers.services.models.ScheduleWSModel;
import vn.easycare.layers.services.models.ScheduleWSParamModel;
import vn.easycare.layers.services.models.SchedulesListWSModel;
import vn.easycare.layers.ui.components.data.DoctorClinicAddressItemData;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.components.data.ExaminationScheduleItemData;
import vn.easycare.layers.ui.components.data.PatientManagementItemData;
import vn.easycare.layers.ui.models.base.IBaseModel;
import vn.easycare.layers.ui.models.base.IExaminationSchedulesModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phan on 12/24/2014.
 */
public class ExaminationSchedulesModel implements IExaminationSchedulesModel,IWSResponse {
    private Context mContext;
    private IResponseUIDataCallback mCallback;
    public  ExaminationSchedulesModel(Context context,IResponseUIDataCallback callback){
        mContext = context;
        mCallback = callback;
    }

    @Override
    public void getAllExaminationSchedulesForSpecificDate(String date) {
        try {
            IWebServiceAccess<SchedulesListWSModel,ScheduleWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    ScheduleWSAccess.class,
                    mContext,
                    this,
                    new ScheduleWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken()
                            ,"",date,"","","","","", AppConstants.SCHEDULE_ACTION.NONE));
            WS.sendRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void setResponseCallback(IResponseUIDataCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void getAnExaminationSchedule(String scheduleId) {
        try {
            IWebServiceAccess<SchedulesListWSModel,ScheduleWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    ScheduleWSAccess.class,
                    mContext,
                    this,
                    new ScheduleWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken()
                            ,scheduleId,"","","","","","", AppConstants.SCHEDULE_ACTION.VIEWDETAIL));
            WS.sendRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void doCreateNewExaminationSchedule(ExaminationScheduleItemData itemData) {
        try {
            IWebServiceAccess<SchedulesListWSModel,ScheduleWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    ScheduleWSAccess.class,
                    mContext,
                    this,
                    new ScheduleWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken()
                            ,"",itemData.getScheduleDate(),itemData.getTimeFrom(),
                            itemData.getTimeTo(),itemData.getTimeSlot()+"",
                            itemData.getDoctorAddressId(),"", AppConstants.SCHEDULE_ACTION.CREATE));
            WS.sendRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void doUpdateExaminationSchedule(ExaminationScheduleItemData itemData) {
        try {
            IWebServiceAccess<SchedulesListWSModel,ScheduleWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    ScheduleWSAccess.class,
                    mContext,
                    this,
                    new ScheduleWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken()
                            ,itemData.getScheduleId(),itemData.getScheduleDate(),itemData.getTimeFrom(),
                            itemData.getTimeTo(),itemData.getTimeSlot()+"",
                            itemData.getDoctorAddressId(),"", AppConstants.SCHEDULE_ACTION.UPDATE));
            WS.sendRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void doDeleteExaminationSchedule(String scheduleId) {
        try {
            IWebServiceAccess<SchedulesListWSModel,ScheduleWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    ScheduleWSAccess.class,
                    mContext,
                    this,
                    new ScheduleWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken()
                            ,scheduleId,"","","","","","", AppConstants.SCHEDULE_ACTION.DELETE));
            WS.sendRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void loadAllAddressesForDoctor() {

    }

    @Override
    public void onWSResponseOK(IWebServiceModel result) {
        if (result instanceof SchedulesListWSModel) {
            SchedulesListWSModel modelList = (SchedulesListWSModel)result;
            List<ExaminationScheduleItemData> itemDataList = new ArrayList<ExaminationScheduleItemData>();
            for(ScheduleWSModel scheduleModel : modelList.getListSchedules()){
                ExaminationScheduleItemData item = new ExaminationScheduleItemData();
                item.setScheduleId(scheduleModel.getId());
                item.setScheduleDate(scheduleModel.getDate());
                item.setTimeFrom(scheduleModel.getTime_from());
                item.setTimeTo(scheduleModel.getTime_to());
                item.setTimeSlot(scheduleModel.getTime_slots());
                item.setDoctorAddressId(scheduleModel.getDoctor_address_id());
                item.setNote(scheduleModel.getNote());
                itemDataList.add(item);
            }
            if(mCallback!=null)
                mCallback.onResponseOK(itemDataList);
        }else if(result instanceof ScheduleWSModel){
            ScheduleWSModel scheduleModel = (ScheduleWSModel)result;
            ExaminationScheduleItemData item = new ExaminationScheduleItemData();
            item.setScheduleId(scheduleModel.getId());
            item.setAction(scheduleModel.getAction());
            if(mCallback!=null)
                mCallback.onResponseOK(item);
        }
    }

    @Override
    public void onWSResponseFailed(WSError error) {

    }
}
