package vn.easycare.layers.ui.presenters.base;

import java.util.Date;

import vn.easycare.layers.ui.models.base.IExaminationAppointmentModel;
import vn.easycare.layers.ui.views.IExaminationAppointmentView;
import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/13/14.
 */
public interface IExaminationAppointmentPresenter extends IPresenter<IExaminationAppointmentView>{
     void loadExaminationAppointmentsForDoctor(AppConstants.EXAMINATION_STATUS status,String patientId,int page);
     void searchExaminationAppointments( String appointmentCode,String patientName,String patientId,AppConstants.EXAMINATION_STATUS status,String date,String startDate, String endDate,int page);
     void AcceptAnExaminationAppointment(String appointmentID);
     void CancelAnExaminationAppointment(String appointmentID);
     void loadAnExaminationAppointmentForDoctor(String appointmentID);
     void ChangeAnExaminationAppointment(String appointmentID,String date,String time,int addressChangeID,String doctorNotes);
     void loadAllAddressesForDoctor();
     void updateDoctorNotes(String appointmentID,String doctorNotes);

}
