package vn.easycare.layers.ui.views;

import java.util.List;

import vn.easycare.layers.ui.components.data.DoctorClinicAddressItemData;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.views.base.IBaseView;

/**
 * Created by phannguyen on 12/13/14.
 */
public interface IExaminationAppointmentView extends IBaseView{
    void DisplayExaminationAppointmentsForDoctor(List<ExaminationAppointmentItemData> examinationAppointmentItemsList);
    void DisplayMessageForAcceptAppointment(String message);
    void DisplayMessageForCancelAppointment(String message);
    void DisplayMessageForChangeAppointment(String message);
    void DisplayMessageForUpdateDoctorNote(String message);
    void DisplayDetailForAnAppointment(ExaminationAppointmentItemData item);
    void DisplayMessageIncaseError(String message);
    void DisplayAllDoctorClinicAddresses(List<DoctorClinicAddressItemData> doctorClinicAddressItemsList);
}
