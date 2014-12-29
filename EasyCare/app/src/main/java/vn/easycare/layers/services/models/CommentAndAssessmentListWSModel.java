package vn.easycare.layers.services.models;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.layers.services.IWebServiceModel;

/**
 * Created by phan on 12/26/2014.
 */
public class CommentAndAssessmentListWSModel implements IWebServiceModel {
    List<CommentAndAssessmentWSModel> listComments;
    int page_total;
    int page_currentPage;
    int lastPage;
    int itemsPerPage;

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

    public int getItems_total() {
        return page_total;
    }

    public void setItems_total(int page_total) {
        this.page_total = page_total;
    }

    public int getPage_currentPage() {
        return page_currentPage;
    }

    public void setPage_currentPage(int page_currentPage) {
        this.page_currentPage = page_currentPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
}
