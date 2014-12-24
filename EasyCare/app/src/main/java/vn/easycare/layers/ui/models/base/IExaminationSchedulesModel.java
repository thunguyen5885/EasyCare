package vn.easycare.layers.ui.models.base;

import java.util.List;

import vn.easycare.layers.ui.components.data.DoctorClinicAddressItemData;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.components.data.ExaminationScheduleItemData;

/**
 * Created by phan on 12/24/2014.
 */
public interface IExaminationSchedulesModel extends IBaseModel{
    List<ExaminationScheduleItemData> getAllExaminationSchedulesForSpecificDate(String date);
    ExaminationScheduleItemData getAnExaminationSchedule(String examinationId);
    boolean doCreateNewExaminationSchedule(ExaminationAppointmentItemData itemData);
    boolean doUpdateExaminationSchedule(ExaminationAppointmentItemData itemData);
    boolean doDeleteExaminationSchedule(String examinationId);
    List<DoctorClinicAddressItemData> loadAllAddressesForDoctor();
}
