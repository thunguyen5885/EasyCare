package vn.easycare.layers.ui.models.base;

import java.util.List;

import vn.easycare.layers.ui.components.data.DoctorClinicAddressItemData;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.components.data.ExaminationScheduleItemData;

/**
 * Created by phan on 12/24/2014.
 */
public interface IExaminationSchedulesModel extends IBaseModel{
    void getAllExaminationSchedulesForSpecificDate(String date);
    void getAnExaminationSchedule(String scheduleId);
    void doCreateNewExaminationSchedule(ExaminationScheduleItemData itemData);
    void doUpdateExaminationSchedule(ExaminationScheduleItemData itemData);
    void doDeleteExaminationSchedule(String scheduleId);
    void loadAllAddressesForDoctor();
}
