package vn.easycare.layers.ui.models;

import android.content.Context;

import java.util.List;

import vn.easycare.layers.services.IWSResponse;
import vn.easycare.layers.ui.components.data.InformationStatisticItemData;
import vn.easycare.layers.ui.models.base.IInformationStatisticModel;

/**
 * Created by phan on 12/16/2014.
 */
public class InformationStatisticModel implements IInformationStatisticModel {
    private Context mContext;


    public InformationStatisticModel(Context context){
        mContext = context;
    }


    @Override
    public InformationStatisticItemData getAllInfoStatisticForDoctor() {
        InformationStatisticItemData itemData = new InformationStatisticItemData();
        itemData.setDetailViewCount(500);
        itemData.setOrderedCount(200);
        itemData.setExaminationPendingCount(2);
        itemData.setGeneralCommentCount(276);
        itemData.setGeneralAverageRate(7);
        itemData.setWaitingTimeCommentCount(300);
        itemData.setWaitingTimeAverageRate(5);
        itemData.setFacilityCommentCount(250);
        itemData.setFacilityAverageRate(7);
        return itemData;
    }
}
