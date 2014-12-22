package vn.easycare.layers.ui.presenters.base;

import java.util.Date;

import vn.easycare.layers.ui.models.base.IExaminationAppointmentModel;
import vn.easycare.layers.ui.views.IExaminationAppointmentView;
import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/13/14.
 */
public interface IExaminationAppointmentPresenter extends IPresenter<IExaminationAppointmentView>{
     void loadExaminationAppointmentsForDoctor(AppConstants.EXAMINATION_STATUS status,int page);
     void searchExaminationAppointments( String appointmentCode,String patientName,AppConstants.EXAMINATION_STATUS status,Date date,Date start, Date end,int page);
     void AcceptAnExaminationAppointment(String appointmentID);
     void CancelAnExaminationAppointment(String appointmentID);
     void loadAnExaminationAppointmentForDoctor(String appointmentID);
     void ChangeAnExaminationAppointment(String appointmentID,Date newDateTime,int oldAddressID,int addressChangeID,String doctorNotes);

}
