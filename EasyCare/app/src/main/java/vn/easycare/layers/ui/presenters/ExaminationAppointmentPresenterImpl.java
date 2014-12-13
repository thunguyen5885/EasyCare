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
    public void init(IExaminationAppointmentModel view) {

    }

    @Override
    public void loadExaminationAppointmentsForDoctor(String doctorID, AppConstants.EXAMINATION_STATUS status, int page) {
        iView.DisplayExaminationAppointmentsForDoctor(iModel.getExaminationAppointmentsForDoctor(doctorID, status, page));
    }

    @Override
    public void searchExaminationAppointments(String appointmentCode, String patientName, AppConstants.EXAMINATION_STATUS status, Date date, int page) {
        iView.DisplayExaminationAppointmentsForDoctor(iModel.doSearchExaminationAppointments(appointmentCode, patientName, status, date, page));
    }

    @Override
    public void AcceptAnExaminationAppointment(String doctorID, String appointmentID) {
        boolean isAccept = iModel.doAcceptAnExaminationAppointment(doctorID,appointmentID);
        if(isAccept){
            iView.DisplayMessageForAcceptAppointment(mContext.getResources().getString(R.string.accept_appointment_ok));
        }else{
            iView.DisplayMessageForAcceptAppointment(mContext.getResources().getString(R.string.accept_appointment_fail));
        }
    }

    @Override
    public void CancelAnExaminationAppointment(String doctorID, String appointmentID) {
        boolean isCancel = iModel.doCancelAnExaminationAppointment(doctorID,appointmentID);
        if(isCancel){
            iView.DisplayMessageForCancelAppointment(mContext.getResources().getString(R.string.cancel_appointment_ok));
        }else{
            iView.DisplayMessageForCancelAppointment(mContext.getResources().getString(R.string.cancel_appointment_fail));
        }
    }

    @Override
    public void loadAnExaminationAppointmentForDoctor(String doctorID, String appointmentID) {
        iView.DisplayPopupForAnAppointment(iModel.getAnExaminationAppointmentForDoctor(doctorID, appointmentID));
    }

    @Override
    public void ChangeAnExaminationAppointment(String doctorID, String appointmentID, Date newDateTime, int oldAddressID, int addressChangeID, String doctorNotes) {
       boolean isChange = iModel.doChangeAnExaminationAppointment(doctorID,appointmentID,newDateTime,oldAddressID,addressChangeID,doctorNotes);
        if(isChange){
            iView.DisplayMessageForChangeAppointment(mContext.getResources().getString(R.string.change_appointment_ok));
        }else{
            iView.DisplayMessageForChangeAppointment(mContext.getResources().getString(R.string.change_appointment_fail));
        }
    }
}
