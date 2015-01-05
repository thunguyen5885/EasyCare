package vn.easycare.layers.services.models;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.layers.services.IWebServiceModel;

/**
 * Created by phan on 12/29/2014.
 */
public class SchedulesListWSModel implements IWebServiceModel {
    List<ScheduleWSModel> listSchedules;

    public SchedulesListWSModel() {
        listSchedules = new ArrayList<ScheduleWSModel>();
    }

    public SchedulesListWSModel(List<ScheduleWSModel> listSchedules) {
        this.listSchedules = listSchedules;
    }

    public List<ScheduleWSModel> getListSchedules() {
        return listSchedules;
    }

    public void setListSchedules(List<ScheduleWSModel> listSchedules) {
        this.listSchedules = listSchedules;
    }
}
