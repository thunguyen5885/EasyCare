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
    public List<InformationStatisticItemData> getAllInfoStatisticForDoctor() {
        return null;
    }
}
