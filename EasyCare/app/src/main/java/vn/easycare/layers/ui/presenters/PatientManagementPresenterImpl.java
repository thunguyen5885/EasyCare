package vn.easycare.layers.ui.presenters;

import android.content.Context;

import vn.easycare.layers.ui.models.CommentAndAssessmentModel;
import vn.easycare.layers.ui.models.PatientManagementModel;
import vn.easycare.layers.ui.models.base.ICommentAndAssessmentModel;
import vn.easycare.layers.ui.models.base.IPatientManagementModel;
import vn.easycare.layers.ui.presenters.base.IPatientManagementPresenter;
import vn.easycare.layers.ui.presenters.base.IPresenter;
import vn.easycare.layers.ui.views.ICommentAndAssessmentView;
import vn.easycare.layers.ui.views.IPatientManagementView;

/**
 * Created by phan on 12/16/2014.
 */
public class PatientManagementPresenterImpl implements IPatientManagementPresenter {
    private IPatientManagementView iView;
    private IPatientManagementModel iModel;
    Context mContext;

    public PatientManagementPresenterImpl(IPatientManagementView view,Context context){
        iView = view;
        iModel = new PatientManagementModel(context);
        mContext = context;
    }
    @Override
    public void init(IPatientManagementView view) {

    }
}
