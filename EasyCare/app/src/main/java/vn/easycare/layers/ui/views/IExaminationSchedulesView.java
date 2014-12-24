package vn.easycare.layers.ui.views;

import java.util.List;

import vn.easycare.layers.ui.components.data.DoctorClinicAddressItemData;
import vn.easycare.layers.ui.components.data.ExaminationScheduleItemData;

/**
 * Created by phan on 12/24/2014.
 */
public interface IExaminationSchedulesView {
    void DisplayAllExaminationSchedulesForSeletedDate(List<ExaminationScheduleItemData> scheduleItemsList);
    void DisplayAnExaminationSchedule(ExaminationScheduleItemData scheduleItem);
    void DisplayMessageForScheduleAction(String message);
    void DisplayAllDoctorClinicAddresses(List<DoctorClinicAddressItemData> doctorClinicAddressItemsList);
}
