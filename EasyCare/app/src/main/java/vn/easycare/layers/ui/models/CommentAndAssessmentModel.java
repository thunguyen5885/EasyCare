package vn.easycare.layers.ui.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vn.easycare.layers.services.IWSResponse;
import vn.easycare.layers.ui.components.data.CommentAndAssessmentItemData;
import vn.easycare.layers.ui.models.base.ICommentAndAssessmentModel;

/**
 * Created by phan on 12/16/2014.
 */
public class CommentAndAssessmentModel implements ICommentAndAssessmentModel{
    private Context mContext;

    public  CommentAndAssessmentModel(Context context){
        mContext = context;
    }

    @Override
    public List<CommentAndAssessmentItemData> getAllCommentsAndAssessmentsForDoctor(String doctorID, int page) {
        List<CommentAndAssessmentItemData> itemDataList = new ArrayList<CommentAndAssessmentItemData>();
        if(page <= 4) {
            Calendar calendar = Calendar.getInstance();
            for (int index = 0; index < 10; index++) {
                CommentAndAssessmentItemData itemData = new CommentAndAssessmentItemData();
                itemData.setPatientAvatar("http://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/thumbs/look_me_in_the_eye.jpg");
                itemData.setPatientName("Nguyen Van A");
                itemData.setCommentDateTime("11/08/2014   12:30");
                itemData.setCommentContent("Bác sĩ Tuấn thật tuyệt vời, phòng khám tuy hơi nhỏ nhưng thái độ của nhân viên rất lịch sự. Tôi cảm thấy hài lòng khi chọn EC.");
                itemData.setGeneralPoint(5);
                itemData.setFacilityPoint(4);
                itemData.setWaitingTimePoint(3);
                itemDataList.add(itemData);
            }
        }
        return itemDataList;
    }

    @Override
    public boolean doHideACommentAndAssessment(String commentID) {
        return false;
    }


}
