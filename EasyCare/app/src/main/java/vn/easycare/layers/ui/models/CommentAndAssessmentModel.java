package vn.easycare.layers.ui.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.easycare.layers.services.IWSResponse;
import vn.easycare.layers.services.IWebServiceAccess;
import vn.easycare.layers.services.IWebServiceModel;
import vn.easycare.layers.services.WSAccessFactory;
import vn.easycare.layers.services.WSDataSingleton;
import vn.easycare.layers.services.WSError;
import vn.easycare.layers.services.concretes.CommentAndAssessmentWSAccess;
import vn.easycare.layers.services.models.CommentAndAssessmentListWSModel;
import vn.easycare.layers.services.models.CommentAndAssessmentWSModel;
import vn.easycare.layers.services.models.CommentAndAssessmentWSParamModel;
import vn.easycare.layers.ui.components.data.CommentAndAssessmentItemData;
import vn.easycare.layers.ui.models.base.ICommentAndAssessmentModel;

/**
 * Created by phan on 12/16/2014.
 */
public class CommentAndAssessmentModel implements ICommentAndAssessmentModel,IWSResponse {
    private Context mContext;
    private IResponseUIDataCallback mCallback;
    public  CommentAndAssessmentModel(Context context,IResponseUIDataCallback callback){
        mContext = context;
        mCallback = callback;
    }

    @Override
    public void getAllCommentsAndAssessmentsForDoctor(int page) {
        try {
            IWebServiceAccess<CommentAndAssessmentListWSModel,CommentAndAssessmentWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    CommentAndAssessmentWSAccess.class,
                    mContext,
                    this,
                    new CommentAndAssessmentWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken(),page+""));
            WS.sendRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private List<CommentAndAssessmentItemData> fakeDataTesting(int page){
        List<CommentAndAssessmentItemData> itemDataList = new ArrayList<CommentAndAssessmentItemData>();
        if(page <= 4) {
            Calendar calendar = Calendar.getInstance();
            for (int index = 0; index < 10; index++) {
                CommentAndAssessmentItemData itemData = new CommentAndAssessmentItemData();
                itemData.setPatientAvatarThumb("http://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/thumbs/look_me_in_the_eye.jpg");
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


    @Override
    public void setResponseCallback(IResponseUIDataCallback callback) {
        mCallback = callback;
    }

    @Override
    public void onWSResponseOK(IWebServiceModel result) {
        CommentAndAssessmentListWSModel listModel = (CommentAndAssessmentListWSModel)result;
        List<CommentAndAssessmentItemData> itemDataList = new ArrayList<CommentAndAssessmentItemData>();
        for(CommentAndAssessmentWSModel commentModel : listModel.getListComments()){
            CommentAndAssessmentItemData item = new CommentAndAssessmentItemData();
            item.setCommentId(commentModel.getId());
            item.setCommentContent(commentModel.getComment());
            item.setCommentDateTime(commentModel.getCreatedAt());
            item.setFacilityPoint(commentModel.getFacilityRating());
            item.setGeneralPoint(commentModel.getCommonRating());
            item.setWaitingTimePoint(commentModel.getWaitingTimeRating());
            item.setPatientName(commentModel.getCommentByPatientName());
            item.setPatientId(commentModel.getCommentByPatientId());
            item.setPatientAvatarThumb(commentModel.getCommentByPatientAvatarThumbUrl());
            item.setPatientAvatar(commentModel.getCommentByPatientAvatarUrl());
            item.setDisplayed(true);
            item.setTotalItems(listModel.getItems_total());
            item.setCurrentPage(listModel.getPage_currentPage());
            item.setLastPage(listModel.getLastPage());
            item.setItemsPerPage(listModel.getItemsPerPage());
            itemDataList.add(item);
        }
        if(mCallback!=null)
            mCallback.onResponseOK(itemDataList);
    }

    @Override
    public void onWSResponseFailed(WSError error) {
        if(mCallback!=null) {
            mCallback.onResponseFail(error.getErrorMessage(), error.getFunctionTitle());
        }
    }
}
