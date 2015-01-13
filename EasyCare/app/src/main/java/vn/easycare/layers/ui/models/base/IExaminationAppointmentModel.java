package vn.easycare.layers.ui.models.base;

import java.util.Date;
import java.util.List;

import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.models.base.IBaseModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/13/14.
 */
public interface IExaminationAppointmentModel extends IBaseModel{
    void getExaminationAppointmentsForDoctor( AppConstants.EXAMINATION_STATUS status,String patientId, int page);
    void doSearchExaminationAppointments(String appointmentCode,String patientName,String patientId,AppConstants.EXAMINATION_STATUS status,String date,String startDate,String endDate,int page);
    void doAcceptAnExaminationAppointment( String appointmentID);
    void doCancelAnExaminationAppointment( String appointmentID);
    void getAnExaminationAppointmentForDoctor(String appointmentID);
    void doChangeAnExaminationAppointment(String appointmentID, String date,String time, String addressChangeID);
    void loadAllAddressesForDoctor();
    void doUpdateDoctorNotes(String appointmentID,String doctorNotes);

}
