package vn.easycare.layers.ui.views;

import java.util.List;

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
    void DisplayPopupForAnAppointment(ExaminationAppointmentItemData item);
}
