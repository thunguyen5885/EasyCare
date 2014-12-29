package vn.easycare.layers.ui.components.data;

import vn.easycare.layers.ui.components.data.base.IBaseItemData;

/**
 * Created by phan on 12/16/2014.
 */
public class CommentAndAssessmentItemData implements IBaseItemData{
    String CommentId;
    String PatientAvatar;
    String PatientAvatarThumb;
    String PatientName;
    String PatientId;
    String CommentDateTime;
    String CommentContent;
    int GeneralPoint;//from 1 to 5
    int FacilityPoint;//from 1 to 5
    int WaitingTimePoint;//from 1 to 5
    boolean IsDisplayed;
    int totalPages;
    int currentPage;
    int lastPage;
    int itemsPerPage;

    public String getCommentDateTime() {
        return CommentDateTime;
    }

    public void setCommentDateTime(String commentDateTime) {
        CommentDateTime = commentDateTime;
    }

    public String getCommentContent() {
        return CommentContent;
    }

    public void setCommentContent(String commentContent) {
        CommentContent = commentContent;
    }

    public int getGeneralPoint() {
        return GeneralPoint;
    }

    public void setGeneralPoint(int generalPoint) {
        GeneralPoint = generalPoint;
    }

    public int getWaitingTimePoint() {
        return WaitingTimePoint;
    }

    public void setWaitingTimePoint(int waitingTimePoint) {
        WaitingTimePoint = waitingTimePoint;
    }

    public int getFacilityPoint() {
        return FacilityPoint;
    }

    public void setFacilityPoint(int facilityPoint) {
        FacilityPoint = facilityPoint;
    }

    public boolean isDisplayed() {
        return IsDisplayed;
    }

    public void setDisplayed(boolean isDisplayed) {
        IsDisplayed = isDisplayed;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getPatientAvatarThumb() {
        return PatientAvatarThumb;
    }

    public void setPatientAvatarThumb(String patientAvatarThumb) {
        PatientAvatarThumb = patientAvatarThumb;
    }

    public String getPatientAvatar() {
        return PatientAvatar;
    }

    public void setPatientAvatar(String patientAvatar) {
        PatientAvatar = patientAvatar;
    }

    public String getCommentId() {
        return CommentId;
    }

    public void setCommentId(String commentId) {
        CommentId = commentId;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String patientId) {
        PatientId = patientId;
    }

    public int getTotalItems() {
        return totalPages;
    }

    public void setTotalItems(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
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
