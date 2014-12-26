package vn.easycare.layers.services.models;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.layers.services.IWebServiceModel;

/**
 * Created by phan on 12/26/2014.
 */
public class CommentAndAssessmentListWSModel implements IWebServiceModel {
    List<CommentAndAssessmentWSModel> listComments;

    public CommentAndAssessmentListWSModel() {
        listComments = new ArrayList<CommentAndAssessmentWSModel>();
    }

    public CommentAndAssessmentListWSModel(List<CommentAndAssessmentWSModel> listComments) {
        this.listComments = listComments;
    }

    public List<CommentAndAssessmentWSModel> getListComments() {
        return listComments;
    }

    public void setListComments(List<CommentAndAssessmentWSModel> listComments) {
        this.listComments = listComments;
    }
}
