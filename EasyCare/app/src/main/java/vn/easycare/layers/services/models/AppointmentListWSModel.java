package vn.easycare.layers.services.models;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.layers.services.IWebServiceModel;

/**
 * Created by phan on 12/26/2014.
 */
public class AppointmentListWSModel implements IWebServiceModel{
    List<AppointmentWSModel> listAppointments;
    int page_total;
    int page_currentPage;
    int lastPage;
    int itemsPerPage;

    public AppointmentListWSModel() {
        listAppointments = new ArrayList<AppointmentWSModel>();
    }

    public List<AppointmentWSModel> getListAppointments() {
        return listAppointments;
    }

    public void setListAppointments(List<AppointmentWSModel> listAppointments) {
        this.listAppointments = listAppointments;
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
