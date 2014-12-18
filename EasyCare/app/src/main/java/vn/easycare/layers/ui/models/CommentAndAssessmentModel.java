package vn.easycare.layers.ui.models;

import android.content.Context;

import java.util.List;

import vn.easycare.layers.ui.components.data.CommentAndAssessmentItemData;
import vn.easycare.layers.ui.models.base.ICommentAndAssessmentModel;

/**
 * Created by phan on 12/16/2014.
 */
public class CommentAndAssessmentModel implements ICommentAndAssessmentModel{
    private Context mContext;

    public  CommentAndAssessmentModel(Context context){
        mContext = context;
    }

    @Override
    public List<CommentAndAssessmentItemData> getAllCommentsAndAssessmentsForDoctor(String doctorID, int page) {
        return null;
    }

    @Override
    public boolean doHideACommentAndAssessment(String commentID) {
        return false;
    }


}
