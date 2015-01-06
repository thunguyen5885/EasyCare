package vn.easycare.layers.ui.presenters;

import android.content.Context;

import java.util.List;

import vn.easycare.layers.ui.components.data.CommentAndAssessmentItemData;
import vn.easycare.layers.ui.components.data.base.IBaseItemData;
import vn.easycare.layers.ui.models.CommentAndAssessmentModel;
import vn.easycare.layers.ui.models.ExaminationAppointmentModel;
import vn.easycare.layers.ui.models.base.IBaseModel;
import vn.easycare.layers.ui.models.base.ICommentAndAssessmentModel;
import vn.easycare.layers.ui.models.base.IExaminationAppointmentModel;
import vn.easycare.layers.ui.presenters.base.ICommentAndAssessmentPresenter;
import vn.easycare.layers.ui.views.ICommentAndAssessmentView;
import vn.easycare.layers.ui.views.IExaminationAppointmentView;

/**
 * Created by phan on 12/16/2014.
 */
public class CommentAndAssessmentPresenterImpl implements ICommentAndAssessmentPresenter,IBaseModel.IResponseUIDataCallback {
    private ICommentAndAssessmentView iView;
    private ICommentAndAssessmentModel iModel;
    Context mContext;

    public CommentAndAssessmentPresenterImpl(ICommentAndAssessmentView view,Context context){
        iView = view;
        iModel = new CommentAndAssessmentModel(context,this);
        mContext = context;
    }
    @Override
    public void init(ICommentAndAssessmentView view) {

    }

    @Override
    public void loadCommentAndAssessmentForDoctor(int page) {
        iModel.getAllCommentsAndAssessmentsForDoctor(page);

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


    @Override
    public <T extends IBaseItemData> void onResponseOK(T itemData, Class<T>... itemDataClass) {

    }

    @Override
    public <T extends IBaseItemData> void onResponseOK(List<T> itemDataList, Class<T>... itemDataClass) {
        List<CommentAndAssessmentItemData> commentAndAssessmentItemsList = (List<CommentAndAssessmentItemData>) itemDataList;
        iView.DisplayAllCommentAndAssessmentForDoctor(commentAndAssessmentItemsList);
    }

    @Override
    public void onResponseFail(String message,String functionTitle) {

        iView.DisplayMessageIncaseError(message,functionTitle);
    }
}
