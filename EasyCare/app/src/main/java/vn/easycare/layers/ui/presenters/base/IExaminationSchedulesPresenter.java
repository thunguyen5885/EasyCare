package vn.easycare.layers.ui.presenters.base;

import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.components.data.ExaminationScheduleItemData;

/**
 * Created by phan on 12/24/2014.
 */
public interface IExaminationSchedulesPresenter {
    void loadAllExaminationSchedulesForSpecificDate(String date);
    void loadAnExaminationSchedule(String scheduleId);
    void createNewExaminationSchedule(ExaminationScheduleItemData itemData);
    void updateExaminationSchedule(ExaminationScheduleItemData itemData);
    void deleteExaminationSchedule(String scheduleId);
    void loadAllAddressesForDoctor();
}
