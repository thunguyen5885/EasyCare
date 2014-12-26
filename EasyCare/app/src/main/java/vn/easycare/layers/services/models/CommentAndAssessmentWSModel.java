package vn.easycare.layers.services.models;

import vn.easycare.layers.services.IWebServiceModel;

/**
 * Created by phan on 12/26/2014.
 */
public class CommentAndAssessmentWSModel implements IWebServiceModel {
    String id;
    String comment;
    int commonRating;
    int facilityRating;
    int waitingTimeRating;
    int isShow;
    String createdAt;
    String commentByPatientId;
    String commentByPatientName;
    String commentByPatientAvatarUrl;
    String commentByPatientAvatarThumbUrl;

    public CommentAndAssessmentWSModel(String id, String comment, int commonRating, int facilityRating, int waitingTimeRating, int isShow, String createdAt, String commentByPatientId, String commentByPatientName, String commentByPatientAvatarUrl, String commentByPatientAvatarThumbUrl) {
        this.id = id;
        this.comment = comment;
        this.commonRating = commonRating;
        this.facilityRating = facilityRating;
        this.waitingTimeRating = waitingTimeRating;
        this.isShow = isShow;
        this.createdAt = createdAt;
        this.commentByPatientId = commentByPatientId;
        this.commentByPatientName = commentByPatientName;
        this.commentByPatientAvatarUrl = commentByPatientAvatarUrl;
        this.commentByPatientAvatarThumbUrl = commentByPatientAvatarThumbUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCommonRating() {
        return commonRating;
    }

    public void setCommonRating(int commonRating) {
        this.commonRating = commonRating;
    }

    public int getFacilityRating() {
        return facilityRating;
    }

    public void setFacilityRating(int facilityRating) {
        this.facilityRating = facilityRating;
    }

    public int getWaitingTimeRating() {
        return waitingTimeRating;
    }

    public void setWaitingTimeRating(int waitingTimeRating) {
        this.waitingTimeRating = waitingTimeRating;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCommentByPatientId() {
        return commentByPatientId;
    }

    public void setCommentByPatientId(String commentByPatientId) {
        this.commentByPatientId = commentByPatientId;
    }

    public String getCommentByPatientName() {
        return commentByPatientName;
    }

    public void setCommentByPatientName(String commentByPatientName) {
        this.commentByPatientName = commentByPatientName;
    }

    public String getCommentByPatientAvatarUrl() {
        return commentByPatientAvatarUrl;
    }

    public void setCommentByPatientAvatarUrl(String commentByPatientAvatarUrl) {
        this.commentByPatientAvatarUrl = commentByPatientAvatarUrl;
    }

    public String getCommentByPatientAvatarThumbUrl() {
        return commentByPatientAvatarThumbUrl;
    }

    public void setCommentByPatientAvatarThumbUrl(String commentByPatientAvatarThumbUrl) {
        this.commentByPatientAvatarThumbUrl = commentByPatientAvatarThumbUrl;
    }
}
