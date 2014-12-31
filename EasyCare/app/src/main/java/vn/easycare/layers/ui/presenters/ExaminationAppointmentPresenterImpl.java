package vn.easycare.layers.ui.presenters;

import android.content.Context;

import java.util.Date;
import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.components.data.base.IBaseItemData;
import vn.easycare.layers.ui.models.ExaminationAppointmentModel;
import vn.easycare.layers.ui.models.base.IBaseModel;
import vn.easycare.layers.ui.models.base.IExaminationAppointmentModel;
import vn.easycare.layers.ui.presenters.base.IExaminationAppointmentPresenter;
import vn.easycare.layers.ui.views.IExaminationAppointmentView;
import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/13/14.
 */
public class ExaminationAppointmentPresenterImpl implements IExaminationAppointmentPresenter,IBaseModel.IResponseUIDataCallback {
    private IExaminationAppointmentView iView;
    private IExaminationAppointmentModel iModel;
    Context mContext;

    public ExaminationAppointmentPresenterImpl(IExaminationAppointmentView view,Context context){
        iView = view;
        iModel = new ExaminationAppointmentModel(context,this);
        mContext = context;
    }

    @Override
    public void init(IExaminationAppointmentView view) {

    }

    @Override
    public void loadExaminationAppointmentsForDoctor(AppConstants.EXAMINATION_STATUS status,String patientId,int page) {
        iModel.getExaminationAppointmentsForDoctor(status,patientId, page);
    }

    @Override
    public void searchExaminationAppointments(String appointmentCode,String patientName,String patientId,AppConstants.EXAMINATION_STATUS status,String date,String startDate, String endDate,int page) {
        iModel.doSearchExaminationAppointments(appointmentCode, patientName,patientId, status, date,startDate,endDate, page);
    }

    @Override
    public void AcceptAnExaminationAppointment( String appointmentID) {
       iModel.doAcceptAnExaminationAppointment(appointmentID);

    }

    @Override
    public void CancelAnExaminationAppointment(String appointmentID) {
        iModel.doCancelAnExaminationAppointment(appointmentID);

    }

    @Override
    public void loadAnExaminationAppointmentForDoctor(String appointmentID) {
        iModel.getAnExaminationAppointmentForDoctor(appointmentID);
    }

    @Override
    public void ChangeAnExaminationAppointment(String appointmentID,String date,String time,int addressChangeID,String doctorNotes) {
       iModel.doChangeAnExaminationAppointment(appointmentID,date,time,addressChangeID);

    }

    @Override
    public void onResponseOK(IBaseItemData itemData) {
        ExaminationAppointmentItemData appointmentItem = (ExaminationAppointmentItemData) itemData;
        if(appointmentItem!=null){
            switch (appointmentItem.getAction()){
                case ACCEPT:
                    iView.DisplayMessageForAcceptAppointment(mContext.getResources().getString(R.string.accept_appointment_ok));
                    break;
                case CANCEL:
                    iView.DisplayMessageForCancelAppointment(mContext.getResources().getString(R.string.cancel_appointment_ok));
                    break;
                case CHANGE:
                    iView.DisplayMessageForChangeAppointment(mContext.getResources().getString(R.string.change_appointment_ok));
                    break;
                case VIEWDETAIL:
                    iView.DisplayDetailForAnAppointment(appointmentItem);
                    break;
            }
        }
    }

    @Override
    public void onResponseOK(List<? extends IBaseItemData> itemDataList) {
        List<ExaminationAppointmentItemData> appointmentItemsList = (List<ExaminationAppointmentItemData>) itemDataList;
        iView.DisplayExaminationAppointmentsForDoctor(appointmentItemsList);
    }

    @Override
    public void onResponseFail(String message) {
        iView.DisplayMessageIncaseError(message);
    }
}
