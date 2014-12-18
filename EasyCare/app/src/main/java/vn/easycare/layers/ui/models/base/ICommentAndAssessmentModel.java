package vn.easycare.layers.ui.models.base;

import java.util.List;

import vn.easycare.layers.ui.components.data.CommentAndAssessmentItemData;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.utils.AppConstants;

/**
 * Created by phan on 12/16/2014.
 */
public interface ICommentAndAssessmentModel extends IBaseModel{
    List<CommentAndAssessmentItemData> getAllCommentsAndAssessmentsForDoctor(String doctorID,int page);
    boolean doHideACommentAndAssessment(String commentID);
}
