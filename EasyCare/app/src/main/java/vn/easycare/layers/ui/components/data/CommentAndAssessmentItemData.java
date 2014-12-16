package vn.easycare.layers.ui.components.data;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by phan on 12/16/2014.
 */
public class CommentAndAssessmentItemData {
    Bitmap PatientAvatar;
    String PatientName;
    Date CommentDateTime;
    String CommentContent;
    int GeneralPoint;//from 1 to 5
    int FacilityPoint;//from 1 to 5
    int WaitingTimePoint;//from 1 to 5
    boolean IsDisplayed;

    public Date getCommentDateTime() {
        return CommentDateTime;
    }

    public void setCommentDateTime(Date commentDateTime) {
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

    public Bitmap getPatientAvatar() {
        return PatientAvatar;
    }

    public void setPatientAvatar(Bitmap patientAvatar) {
        PatientAvatar = patientAvatar;
    }
}
