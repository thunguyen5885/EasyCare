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
    List<ExaminationAppointmentItemData> getExaminationAppointmentsForDoctor(String doctorID, AppConstants.EXAMINATION_STATUS status, int page);
    List<ExaminationAppointmentItemData> doSearchExaminationAppointments(String appointmentCode,String patientName,AppConstants.EXAMINATION_STATUS status,Date date,int page);
    boolean doAcceptAnExaminationAppointment(String doctorID, String appointmentID);
    boolean doCancelAnExaminationAppointment(String doctorID, String appointmentID);
    ExaminationAppointmentItemData getAnExaminationAppointmentForDoctor(String doctorID, String appointmentID);
    boolean doChangeAnExaminationAppointment(String doctorID,String appointmentID,Date newDateTime,int oldAddressID,int addressChangeID,String doctorNotes);

}
