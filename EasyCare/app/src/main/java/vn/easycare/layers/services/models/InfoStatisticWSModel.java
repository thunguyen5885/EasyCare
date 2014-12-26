package vn.easycare.layers.services.models;

import vn.easycare.layers.services.IWebServiceModel;

/**
 * Created by phan on 12/26/2014.
 */
public class InfoStatisticWSModel implements IWebServiceModel{
    int totalViewed;
    int totalAppointment;
    int totalWaitingAppointment;
    int totalCommonRating;
    int totalFacilityRating;
    int totalWaitingTimeRating;
    int totalComment;
    float avgCommonRating;
    float avgFacilityRating;
    float avgWaitingTimeRating;

    public InfoStatisticWSModel(int totalViewed, int totalAppointment, int totalWaitingAppointment, int totalCommonRating, int totalFacilityRating, int totalWaitingTimeRating, int totalComment, float avgCommonRating, float avgFacilityRating, float avgWaitingTimeRating) {
        this.totalViewed = totalViewed;
        this.totalAppointment = totalAppointment;
        this.totalWaitingAppointment = totalWaitingAppointment;
        this.totalCommonRating = totalCommonRating;
        this.totalFacilityRating = totalFacilityRating;
        this.totalWaitingTimeRating = totalWaitingTimeRating;
        this.totalComment = totalComment;
        this.avgCommonRating = avgCommonRating;
        this.avgFacilityRating = avgFacilityRating;
        this.avgWaitingTimeRating = avgWaitingTimeRating;
    }

    public int getTotalViewed() {
        return totalViewed;
    }

    public void setTotalViewed(int totalViewed) {
        this.totalViewed = totalViewed;
    }

    public int getTotalAppointment() {
        return totalAppointment;
    }

    public void setTotalAppointment(int totalAppointment) {
        this.totalAppointment = totalAppointment;
    }

    public int getTotalWaitingAppointment() {
        return totalWaitingAppointment;
    }

    public void setTotalWaitingAppointment(int totalWaitingAppointment) {
        this.totalWaitingAppointment = totalWaitingAppointment;
    }

    public int getTotalCommonRating() {
        return totalCommonRating;
    }

    public void setTotalCommonRating(int totalCommonRating) {
        this.totalCommonRating = totalCommonRating;
    }

    public int getTotalFacilityRating() {
        return totalFacilityRating;
    }

    public void setTotalFacilityRating(int totalFacilityRating) {
        this.totalFacilityRating = totalFacilityRating;
    }

    public int getTotalWaitingTimeRating() {
        return totalWaitingTimeRating;
    }

    public void setTotalWaitingTimeRating(int totalWaitingTimeRating) {
        this.totalWaitingTimeRating = totalWaitingTimeRating;
    }

    public int getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(int totalComment) {
        this.totalComment = totalComment;
    }

    public float getAvgCommonRating() {
        return avgCommonRating;
    }

    public void setAvgCommonRating(float avgCommonRating) {
        this.avgCommonRating = avgCommonRating;
    }

    public float getAvgFacilityRating() {
        return avgFacilityRating;
    }

    public void setAvgFacilityRating(float avgFacilityRating) {
        this.avgFacilityRating = avgFacilityRating;
    }

    public float getAvgWaitingTimeRating() {
        return avgWaitingTimeRating;
    }

    public void setAvgWaitingTimeRating(float avgWaitingTimeRating) {
        this.avgWaitingTimeRating = avgWaitingTimeRating;
    }
}
