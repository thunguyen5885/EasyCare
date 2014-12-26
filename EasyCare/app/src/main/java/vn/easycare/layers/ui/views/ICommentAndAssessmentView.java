package vn.easycare.layers.ui.views;

import java.util.List;

import vn.easycare.layers.ui.components.data.CommentAndAssessmentItemData;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.views.base.IBaseView;

/**
 * Created by phan on 12/16/2014.
 */
public interface ICommentAndAssessmentView extends IBaseView{
    void DisplayAllCommentAndAssessmentForDoctor(List<CommentAndAssessmentItemData> commentAndAssessmentItemsList);
    void DisplayMessageForHideCommentAndAssessment(String message);
    void DisplayMessageIncaseError(String message);
}
