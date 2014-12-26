package vn.easycare.layers.ui.components.data;

import vn.easycare.layers.ui.components.data.base.IBaseItemData;

/**
 * Created by phan on 12/16/2014.
 */
public class InformationStatisticItemData implements IBaseItemData {
    int DetailViewCount;
    int OrderedCount;
    int ExaminationPendingCount;
    int GeneralCommentCount;
    float GeneralAverageRate;
    int WaitingTimeCommentCount;
    float WaitingTimeAverageRate;
    int FacilityCommentCount;
    float FacilityAverageRate;

    public int getDetailViewCount() {
        return DetailViewCount;
    }

    public void setDetailViewCount(int detailViewCount) {
        DetailViewCount = detailViewCount;
    }

    public int getOrderedCount() {
        return OrderedCount;
    }

    public void setOrderedCount(int orderedCount) {
        OrderedCount = orderedCount;
    }

    public int getExaminationPendingCount() {
        return ExaminationPendingCount;
    }

    public void setExaminationPendingCount(int examinationPendingCount) {
        ExaminationPendingCount = examinationPendingCount;
    }

    public int getGeneralCommentCount() {
        return GeneralCommentCount;
    }

    public void setGeneralCommentCount(int generalCommentCount) {
        GeneralCommentCount = generalCommentCount;
    }

    public float getGeneralAverageRate() {
        return GeneralAverageRate;
    }

    public void setGeneralAverageRate(float generalAverageRate) {
        GeneralAverageRate = generalAverageRate;
    }

    public int getWaitingTimeCommentCount() {
        return WaitingTimeCommentCount;
    }

    public void setWaitingTimeCommentCount(int waitingTimeCommentCount) {
        WaitingTimeCommentCount = waitingTimeCommentCount;
    }

    public float getWaitingTimeAverageRate() {
        return WaitingTimeAverageRate;
    }

    public void setWaitingTimeAverageRate(float waitingTimeAverageRate) {
        WaitingTimeAverageRate = waitingTimeAverageRate;
    }

    public int getFacilityCommentCount() {
        return FacilityCommentCount;
    }

    public void setFacilityCommentCount(int facilityCommentCount) {
        FacilityCommentCount = facilityCommentCount;
    }

    public float getFacilityAverageRate() {
        return FacilityAverageRate;
    }

    public void setFacilityAverageRate(float facilityAverageRate) {
        FacilityAverageRate = facilityAverageRate;
    }
}
