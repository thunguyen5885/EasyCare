package vn.easycare.layers.ui.views;

import java.util.List;

import vn.easycare.layers.ui.components.data.DoctorClinicAddressItemData;
import vn.easycare.layers.ui.components.data.ExaminationScheduleItemData;
import vn.easycare.layers.ui.views.base.IBaseView;

/**
 * Created by phan on 12/24/2014.
 */
public interface IExaminationSchedulesView extends IBaseView{
    void DisplayAllExaminationSchedulesForSeletedDate(List<ExaminationScheduleItemData> scheduleItemsList);
    void DisplayAnExaminationSchedule(ExaminationScheduleItemData scheduleItem);
    void DisplayMessageForScheduleActionComplete(String message);
    void DisplayAllDoctorClinicAddresses(List<DoctorClinicAddressItemData> doctorClinicAddressItemsList);

}
