package vn.easycare.layers.ui.models.base;

import java.util.List;

import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.components.data.InformationStatisticItemData;
import vn.easycare.layers.ui.views.base.IBaseView;
import vn.easycare.utils.AppConstants;

/**
 * Created by phan on 12/16/2014.
 */
public interface IInformationStatisticModel extends IBaseModel{
    InformationStatisticItemData getAllInfoStatisticForDoctor();
}
