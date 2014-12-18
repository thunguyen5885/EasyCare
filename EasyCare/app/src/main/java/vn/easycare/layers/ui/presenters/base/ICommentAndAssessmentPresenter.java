package vn.easycare.layers.ui.presenters.base;

import vn.easycare.layers.ui.models.base.ICommentAndAssessmentModel;
import vn.easycare.layers.ui.models.base.IExaminationAppointmentModel;
import vn.easycare.layers.ui.views.ICommentAndAssessmentView;
import vn.easycare.utils.AppConstants;

/**
 * Created by phan on 12/16/2014.
 */
public interface ICommentAndAssessmentPresenter extends IPresenter<ICommentAndAssessmentView> {
    void loadCommentAndAssessmentForDoctor(String doctorID,int page);
    void HideACommentAndAssessment(String commentID);
}
