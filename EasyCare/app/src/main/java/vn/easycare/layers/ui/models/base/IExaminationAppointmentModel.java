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
    List<ExaminationAppointmentItemData> getExaminationAppointmentsForDoctor(String token, AppConstants.EXAMINATION_STATUS status, int page);
    List<ExaminationAppointmentItemData> doSearchExaminationAppointments(String token,String appointmentCode,String patientName,AppConstants.EXAMINATION_STATUS status,Date date,Date start,Date end,int page);
    boolean doAcceptAnExaminationAppointment(String token, String appointmentID);
    boolean doCancelAnExaminationAppointment(String token, String appointmentID);
    ExaminationAppointmentItemData getAnExaminationAppointmentForDoctor(String token, String appointmentID);
    boolean doChangeAnExaminationAppointment(String token,String appointmentID,Date newDateTime,int oldAddressID,int addressChangeID,String doctorNotes);

}
