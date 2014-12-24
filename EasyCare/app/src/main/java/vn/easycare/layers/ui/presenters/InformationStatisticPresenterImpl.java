package vn.easycare.layers.ui.presenters;

import android.content.Context;

import vn.easycare.layers.ui.models.InformationStatisticModel;
import vn.easycare.layers.ui.models.base.IInformationStatisticModel;
import vn.easycare.layers.ui.presenters.base.IInformationStatisticPresenter;
import vn.easycare.layers.ui.views.IInformationStatisticView;

/**
 * Created by phan on 12/16/2014.
 */
public class InformationStatisticPresenterImpl implements IInformationStatisticPresenter {
    private IInformationStatisticView iView;
    private IInformationStatisticModel iModel;
    Context mContext;

    public InformationStatisticPresenterImpl(IInformationStatisticView view, Context context){
        iView = view;
        iModel = new InformationStatisticModel(context);
        mContext = context;
    }

    @Override
    public void init(IInformationStatisticView view) {

    }

    @Override
    public void loadAllInfoStatisticForDoctor() {
        iView.DisplayAllInfoStatisticForDoctor(iModel.getAllInfoStatisticForDoctor());
    }
}
