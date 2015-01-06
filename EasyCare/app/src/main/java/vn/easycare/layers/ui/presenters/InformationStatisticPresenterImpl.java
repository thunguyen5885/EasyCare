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
    public <T extends IBaseItemData> void onResponseOK(T itemData, Class<T>... itemDataClass) {
        iView.DisplayAllInfoStatisticForDoctor((InformationStatisticItemData) itemData);
    }

    @Override
    public <T extends IBaseItemData> void onResponseOK(List<T> itemDataList, Class<T>... itemDataClass) {

    }

    @Override
    public void onResponseFail(String message,String functionTitle) {
        iView.DisplayMessageIncaseError(message,functionTitle);
    }
    @Override
    public void onUnauthorized() {
        iView.UnauthorizedProcessing();
    }
}
