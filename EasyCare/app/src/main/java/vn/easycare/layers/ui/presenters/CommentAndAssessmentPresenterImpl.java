package vn.easycare.layers.ui.presenters;

import android.content.Context;

import vn.easycare.layers.ui.models.CommentAndAssessmentModel;
import vn.easycare.layers.ui.models.ExaminationAppointmentModel;
import vn.easycare.layers.ui.models.base.ICommentAndAssessmentModel;
import vn.easycare.layers.ui.models.base.IExaminationAppointmentModel;
import vn.easycare.layers.ui.presenters.base.ICommentAndAssessmentPresenter;
import vn.easycare.layers.ui.views.ICommentAndAssessmentView;
import vn.easycare.layers.ui.views.IExaminationAppointmentView;

/**
 * Created by phan on 12/16/2014.
 */
public class CommentAndAssessmentPresenterImpl implements ICommentAndAssessmentPresenter {
    private ICommentAndAssessmentView iView;
    private ICommentAndAssessmentModel iModel;
    Context mContext;

    public CommentAndAssessmentPresenterImpl(ICommentAndAssessmentView view,Context context){
        iView = view;
        iModel = new CommentAndAssessmentModel(context);
        mContext = context;
    }
    @Override
    public void init(ICommentAndAssessmentView view) {

    }

    @Override
    public void loadCommentAndAssessmentForDoctor(String doctorID, int page) {
        iView.DisplayAllCommentAndAssessmentForDoctor(iModel.getAllCommentsAndAssessmentsForDoctor(doctorID,page));
    }

    @Override
    public void HideACommentAndAssessment(String commentID) {
        boolean isHide = iModel.doHideACommentAndAssessment(commentID);
        if(isHide){
            iView.DisplayMessageForHideCommentAndAssessment("");
        }else{
            iView.DisplayMessageForHideCommentAndAssessment("");
        }
    }
}
