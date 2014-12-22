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
    public void loadExaminationAppointmentsForDoctor(String token,AppConstants.EXAMINATION_STATUS status,int page) {
        iView.DisplayExaminationAppointmentsForDoctor(iModel.getExaminationAppointmentsForDoctor(token, status, page));
    }

    @Override
    public void searchExaminationAppointments(String token, String appointmentCode,String patientName,AppConstants.EXAMINATION_STATUS status,Date date,Date start, Date end,int page) {
        iView.DisplayExaminationAppointmentsForDoctor(iModel.doSearchExaminationAppointments(token,appointmentCode, patientName, status, date,start,end, page));
    }

    @Override
    public void AcceptAnExaminationAppointment(String token, String appointmentID) {
        boolean isAccept = iModel.doAcceptAnExaminationAppointment(token,appointmentID);
        if(isAccept){
            iView.DisplayMessageForAcceptAppointment(mContext.getResources().getString(R.string.accept_appointment_ok));
        }else{
            iView.DisplayMessageForAcceptAppointment(mContext.getResources().getString(R.string.accept_appointment_fail));
        }
    }

    @Override
    public void CancelAnExaminationAppointment(String token, String appointmentID) {
        boolean isCancel = iModel.doCancelAnExaminationAppointment(token,appointmentID);
        if(isCancel){
            iView.DisplayMessageForCancelAppointment(mContext.getResources().getString(R.string.cancel_appointment_ok));
        }else{
            iView.DisplayMessageForCancelAppointment(mContext.getResources().getString(R.string.cancel_appointment_fail));
        }
    }

    @Override
    public void loadAnExaminationAppointmentForDoctor(String token, String appointmentID) {
        iView.DisplayPopupForAnAppointment(iModel.getAnExaminationAppointmentForDoctor(token, appointmentID));
    }

    @Override
    public void ChangeAnExaminationAppointment(String token, String appointmentID, Date newDateTime, int oldAddressID, int addressChangeID, String doctorNotes) {
       boolean isChange = iModel.doChangeAnExaminationAppointment(token,appointmentID,newDateTime,oldAddressID,addressChangeID,doctorNotes);
        if(isChange){
            iView.DisplayMessageForChangeAppointment(mContext.getResources().getString(R.string.change_appointment_ok));
        }else{
            iView.DisplayMessageForChangeAppointment(mContext.getResources().getString(R.string.change_appointment_fail));
        }
    }
}
