package vn.easycare.layers.services.models.builders;

import org.json.JSONException;
import org.json.JSONObject;

import vn.easycare.layers.services.models.InfoStatisticWSModel;

/**
 * Created by phan on 12/26/2014.
 */
public class InfoStatisticWSBuilder {
    int totalViewed = 0;
    int totalAppointment = 0;
    int totalWaitingAppointment = 0;
    int totalCommonRating = 0;
    int totalFacilityRating = 0;
    int totalWaitingTimeRating = 0;
    int totalComment = 0;
    float avgCommonRating = 0;
    float avgFacilityRating = 0;
    float avgWaitingTimeRating = 0;

    public InfoStatisticWSBuilder() {
    }

    public InfoStatisticWSBuilder(JSONObject resJson) throws JSONException {
        //parse json and set value for properties
    }

    public InfoStatisticWSBuilder withTotalViewed(int totalViewed) {
        this.totalViewed = totalViewed;
        return this;
    }

    public InfoStatisticWSBuilder withTotalAppointment(int totalAppointment) {
        this.totalAppointment = totalAppointment;
        return this;
    }

    public InfoStatisticWSBuilder withTotalWaitingAppointment(int totalWaitingAppointment) {
        this.totalWaitingAppointment = totalWaitingAppointment;
        return this;
    }

    public InfoStatisticWSBuilder withTotalCommonRating(int totalCommonRating) {
        this.totalCommonRating = totalCommonRating;
        return this;
    }

    public InfoStatisticWSBuilder withTotalFacilityRating(int totalFacilityRating) {
        this.totalFacilityRating = totalFacilityRating;
        return this;
    }

    public InfoStatisticWSBuilder withTotalWaitingTimeRating(int totalWaitingTimeRating) {
        this.totalWaitingTimeRating = totalWaitingTimeRating;
        return this;
    }

    public InfoStatisticWSBuilder withTotalComment(int totalComment) {
        this.totalComment = totalComment;
        return this;
    }

    public InfoStatisticWSBuilder withAvgCommonRating(float avgCommonRating) {
        this.avgCommonRating = avgCommonRating;
        return this;
    }

    public InfoStatisticWSBuilder withAvgFacilityRating(float avgFacilityRating) {
        this.avgFacilityRating = avgFacilityRating;
        return this;
    }

    public InfoStatisticWSBuilder withAvgWaitingTimeRating(float avgWaitingTimeRating) {
        this.avgWaitingTimeRating = avgWaitingTimeRating;
        return this;
    }

    public InfoStatisticWSModel build(){
        return new InfoStatisticWSModel( totalViewed,  totalAppointment,  totalWaitingAppointment,
                totalCommonRating,  totalFacilityRating,  totalWaitingTimeRating,
                totalComment,  avgCommonRating,  avgFacilityRating,
                avgWaitingTimeRating);
    }
}
