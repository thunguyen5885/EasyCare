package vn.easycare.layers.services.models;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.layers.services.IWebServiceModel;

/**
 * Created by phannguyen on 12/28/14.
 */
public class PatientListWSModel implements IWebServiceModel {
    List<PatientWSModel> listPatients;
    int page_total;
    int page_currentPage;
    int lastPage;
    int itemsPerPage;

    public PatientListWSModel() {
        listPatients = new ArrayList<PatientWSModel>();
    }

    public PatientListWSModel(List<PatientWSModel> listPatients) {
        this.listPatients = listPatients;
    }

    public List<PatientWSModel> getListPatients() {
        return listPatients;
    }

    public void setListPatients(List<PatientWSModel> listPatients) {
        this.listPatients = listPatients;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
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
