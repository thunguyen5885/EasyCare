package vn.easycare.layers.ui.presenters.base;

import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;

/**
 * Created by phan on 12/24/2014.
 */
public interface IExaminationSchedulesPresenter {
    void loadAllExaminationSchedulesForSpecificDate(String date);
    void loadAnExaminationSchedule(String examinationId);
    void createNewExaminationSchedule(ExaminationAppointmentItemData itemData);
    void updateExaminationSchedule(ExaminationAppointmentItemData itemData);
    void deleteExaminationSchedule(String examinationId);
    void loadAllAddressesForDoctor();
}
