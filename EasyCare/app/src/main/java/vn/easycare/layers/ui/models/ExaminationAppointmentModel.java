package vn.easycare.layers.ui.models;

import android.content.Context;

import java.util.Date;
import java.util.List;

import vn.easycare.layers.services.IWSResponse;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.models.base.IExaminationAppointmentModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/13/14.
 */
public class ExaminationAppointmentModel implements IExaminationAppointmentModel {
    private Context mContext;


    public  ExaminationAppointmentModel(Context context){
        mContext = context;
    }
    @Override
    public List<ExaminationAppointmentItemData> getExaminationAppointmentsForDoctor(String doctorID, AppConstants.EXAMINATION_STATUS status, int page) {
        return null;
    }

    @Override
    public List<ExaminationAppointmentItemData> doSearchExaminationAppointments(String appointmentCode, String patientName, AppConstants.EXAMINATION_STATUS status, Date date, int page) {
        return null;
    }

    @Override
    public boolean doAcceptAnExaminationAppointment(String doctorID, String appointmentID) {
        return true;
    }

    @Override
    public boolean doCancelAnExaminationAppointment(String doctorID, String appointmentID) {
        return true;
    }

    @Override
    public ExaminationAppointmentItemData getAnExaminationAppointmentForDoctor(String doctorID, String appointmentID) {
        return null;
    }

    @Override
    public boolean doChangeAnExaminationAppointment(String doctorID, String appointmentID, Date newDateTime, int oldAddressID, int addressChangeID, String doctorNotes) {
        return true;
    }

}
