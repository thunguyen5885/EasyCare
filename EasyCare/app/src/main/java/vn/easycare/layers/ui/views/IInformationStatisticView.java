package vn.easycare.layers.ui.views;

import java.util.List;

import vn.easycare.layers.ui.components.data.InformationStatisticItemData;
import vn.easycare.layers.ui.components.data.PatientManagementItemData;
import vn.easycare.layers.ui.views.base.IBaseView;

/**
 * Created by phan on 12/16/2014.
 */
public interface IInformationStatisticView extends IBaseView{
    void DisplayAllInfoStatisticForDoctor(InformationStatisticItemData InfoStatisticItem);
    void DisplayMessageIncaseError(String message);
}
