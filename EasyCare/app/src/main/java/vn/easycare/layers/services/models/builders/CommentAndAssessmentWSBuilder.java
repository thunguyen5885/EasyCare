package vn.easycare.layers.services.models.builders;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import vn.easycare.layers.services.models.CommentAndAssessmentWSModel;

/**
 * Created by phan on 12/26/2014.
 */
public class CommentAndAssessmentWSBuilder {
    String id = "";
    String comment = "";
    int commonRating = 0;
    int facilityRating = 0;
    int waitingTimeRating = 0;
    int isShow = 1;
    String createdAt = "";
    String commentByPatientId = "";
    String commentByPatientName = "";
    String commentByPatientAvatarUrl = "";
    String commentByPatientAvatarThumbUrl = "";
    Context mContext;
    public CommentAndAssessmentWSBuilder() {
    }

    public CommentAndAssessmentWSBuilder(Context context) {
        mContext = context;
    }
    public CommentAndAssessmentWSBuilder(JSONObject resJson) throws JSONException {
        //parse json and set value for properties
    }

    public CommentAndAssessmentWSBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public CommentAndAssessmentWSBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public CommentAndAssessmentWSBuilder withCommonRating(int commonRating) {
        this.commonRating = commonRating;
        return this;
    }

    public CommentAndAssessmentWSBuilder withFacilityRating(int facilityRating) {
        this.facilityRating = facilityRating;
        return this;
    }

    public CommentAndAssessmentWSBuilder withWaitingTimeRating(int waitingTimeRating) {
        this.waitingTimeRating = waitingTimeRating;
        return this;
    }

    public CommentAndAssessmentWSBuilder withIsShow(int isShow) {
        this.isShow = isShow;
        return this;
    }

    public CommentAndAssessmentWSBuilder withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CommentAndAssessmentWSBuilder withCommentByPatientId(String commentByPatientId) {
        this.commentByPatientId = commentByPatientId;
        return this;
    }

    public CommentAndAssessmentWSBuilder withCommentByPatientName(String commentByPatientName) {
        this.commentByPatientName = commentByPatientName;
        return this;
    }

    public CommentAndAssessmentWSBuilder withCommentByPatientAvatarUrl(String commentByPatientAvatarUrl) {
        this.commentByPatientAvatarUrl = commentByPatientAvatarUrl;
        return this;
    }

    public CommentAndAssessmentWSBuilder withCommentByPatientAvatarThumbUrl(String commentByPatientAvatarThumbUrl) {
        this.commentByPatientAvatarThumbUrl = commentByPatientAvatarThumbUrl;
        return this;
    }

    public CommentAndAssessmentWSModel build() {
        return new CommentAndAssessmentWSModel( id,  comment,  commonRating,  facilityRating,  waitingTimeRating,
                isShow, createdAt, commentByPatientId, commentByPatientName,
                commentByPatientAvatarUrl,  commentByPatientAvatarThumbUrl);
    }

    public void Clear(){
        id = "";
        comment = "";
        commonRating = 0;
        facilityRating = 0;
        waitingTimeRating = 0;
        isShow = 1;
        createdAt = "";
        commentByPatientId = "";
        commentByPatientName = "";
        commentByPatientAvatarUrl = "";
        commentByPatientAvatarThumbUrl = "";
    }
}
