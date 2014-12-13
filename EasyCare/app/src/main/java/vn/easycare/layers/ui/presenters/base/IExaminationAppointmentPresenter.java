package vn.easycare.layers.ui.presenters.base;

import java.util.Date;

import vn.easycare.layers.ui.models.base.IExaminationAppointmentModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/13/14.
 */
public interface IExaminationAppointmentPresenter extends IPresenter<IExaminationAppointmentModel>{
     void loadExaminationAppointmentsForDoctor(String doctorID,AppConstants.EXAMINATION_STATUS status,int page);
     void searchExaminationAppointments(String appointmentCode,String patientName,AppConstants.EXAMINATION_STATUS status,Date date,int page);
     void AcceptAnExaminationAppointment(String doctorID, String appointmentID);
     void CancelAnExaminationAppointment(String doctorID, String appointmentID);
     void loadAnExaminationAppointmentForDoctor(String doctorID, String appointmentID);
     void ChangeAnExaminationAppointment(String doctorID,String appointmentID,Date newDateTime,int oldAddressID,int addressChangeID,String doctorNotes);

}
