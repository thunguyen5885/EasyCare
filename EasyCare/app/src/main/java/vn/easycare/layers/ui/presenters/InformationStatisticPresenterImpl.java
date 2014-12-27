package vn.easycare.layers.ui.presenters;

import android.content.Context;

import java.util.List;

import vn.easycare.layers.ui.components.data.InformationStatisticItemData;
import vn.easycare.layers.ui.components.data.base.IBaseItemData;
import vn.easycare.layers.ui.models.InformationStatisticModel;
import vn.easycare.layers.ui.models.base.IBaseModel;
import vn.easycare.layers.ui.models.base.IInformationStatisticModel;
import vn.easycare.layers.ui.presenters.base.IInformationStatisticPresenter;
import vn.easycare.layers.ui.views.IInformationStatisticView;

/**
 * Created by phan on 12/16/2014.
 */
public class InformationStatisticPresenterImpl implements IInformationStatisticPresenter,IBaseModel.IResponseUIDataCallback {
    private IInformationStatisticView iView;
    private IInformationStatisticModel iModel;
    Context mContext;

    public InformationStatisticPresenterImpl(IInformationStatisticView view, Context context){
        iView = view;
        iModel = new InformationStatisticModel(context,this);
        mContext = context;
    }

    @Override
    public void init(IInformationStatisticView view) {

    }

    @Override
    public void loadAllInfoStatisticForDoctor() {
        iModel.getAllInfoStatisticForDoctor();
    }

    @Override
    public void onResponseOK(IBaseItemData itemData) {
        iView.DisplayAllInfoStatisticForDoctor((InformationStatisticItemData) itemData);
    }

    @Override
    public void onResponseOK(List<? extends  IBaseItemData> itemDataList) {

    }

    @Override
    public void onResponseFail(String message) {
        iView.DisplayMessageIncaseError(message);
    }
}
