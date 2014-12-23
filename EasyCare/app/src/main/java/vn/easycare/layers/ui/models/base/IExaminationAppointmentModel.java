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
    List<ExaminationAppointmentItemData> getExaminationAppointmentsForDoctor( AppConstants.EXAMINATION_STATUS status, int page);
    List<ExaminationAppointmentItemData> doSearchExaminationAppointments(String appointmentCode,String patientName,AppConstants.EXAMINATION_STATUS status,String date,String startDate,String endDate,int page);
    boolean doAcceptAnExaminationAppointment( String appointmentID);
    boolean doCancelAnExaminationAppointment( String appointmentID);
    ExaminationAppointmentItemData getAnExaminationAppointmentForDoctor(String appointmentID);
    boolean doChangeAnExaminationAppointment(String appointmentID,Date newDateTime,int oldAddressID,int addressChangeID,String doctorNotes);

}
