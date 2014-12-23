package vn.easycare.layers.ui.presenters;

import android.content.Context;

import java.util.Date;

import vn.easycare.R;
import vn.easycare.layers.ui.models.ExaminationAppointmentModel;
import vn.easycare.layers.ui.models.base.IExaminationAppointmentModel;
import vn.easycare.layers.ui.presenters.base.IExaminationAppointmentPresenter;
import vn.easycare.layers.ui.views.IExaminationAppointmentView;
import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/13/14.
 */
public class ExaminationAppointmentPresenterImpl implements IExaminationAppointmentPresenter {
    private IExaminationAppointmentView iView;
    private IExaminationAppointmentModel iModel;
    Context mContext;

    public ExaminationAppointmentPresenterImpl(IExaminationAppointmentView view,Context context){
        iView = view;
        iModel = new ExaminationAppointmentModel(context);
        mContext = context;
    }

    @Override
    public void init(IExaminationAppointmentView view) {

    }

    @Override
    public void loadExaminationAppointmentsForDoctor(AppConstants.EXAMINATION_STATUS status,int page) {
        iView.DisplayExaminationAppointmentsForDoctor(iModel.getExaminationAppointmentsForDoctor(status, page));
    }

    @Override
    public void searchExaminationAppointments(String appointmentCode,String patientName,AppConstants.EXAMINATION_STATUS status,String date,String startDate, String endDate,int page) {
        iView.DisplayExaminationAppointmentsForDoctor(iModel.doSearchExaminationAppointments(appointmentCode, patientName, status, date,startDate,endDate, page));
    }

    @Override
    public void AcceptAnExaminationAppointment( String appointmentID) {
        boolean isAccept = iModel.doAcceptAnExaminationAppointment(appointmentID);
        if(isAccept){
            iView.DisplayMessageForAcceptAppointment(mContext.getResources().getString(R.string.accept_appointment_ok));
        }else{
            iView.DisplayMessageForAcceptAppointment(mContext.getResources().getString(R.string.accept_appointment_fail));
        }
    }

    @Override
    public void CancelAnExaminationAppointment(String appointmentID) {
        boolean isCancel = iModel.doCancelAnExaminationAppointment(appointmentID);
        if(isCancel){
            iView.DisplayMessageForCancelAppointment(mContext.getResources().getString(R.string.cancel_appointment_ok));
        }else{
            iView.DisplayMessageForCancelAppointment(mContext.getResources().getString(R.string.cancel_appointment_fail));
        }
    }

    @Override
    public void loadAnExaminationAppointmentForDoctor(String appointmentID) {
        iView.DisplayPopupForAnAppointment(iModel.getAnExaminationAppointmentForDoctor(appointmentID));
    }

    @Override
    public void ChangeAnExaminationAppointment(String appointmentID, Date newDateTime, int oldAddressID, int addressChangeID, String doctorNotes) {
       boolean isChange = iModel.doChangeAnExaminationAppointment(appointmentID,newDateTime,oldAddressID,addressChangeID,doctorNotes);
        if(isChange){
            iView.DisplayMessageForChangeAppointment(mContext.getResources().getString(R.string.change_appointment_ok));
        }else{
            iView.DisplayMessageForChangeAppointment(mContext.getResources().getString(R.string.change_appointment_fail));
        }
    }
}
