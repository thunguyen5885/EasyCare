package vn.easycare.layers.ui.models;

import android.content.Context;

import vn.easycare.layers.services.IWSResponse;
import vn.easycare.layers.services.IWebServiceAccess;
import vn.easycare.layers.services.IWebServiceModel;
import vn.easycare.layers.services.WSAccessFactory;
import vn.easycare.layers.services.WSDataSingleton;
import vn.easycare.layers.services.WSError;
import vn.easycare.layers.services.concretes.InfoStatisticWSAccess;
import vn.easycare.layers.services.models.InfoStatisticWSModel;
import vn.easycare.layers.services.models.InfoStatisticWSParamModel;
import vn.easycare.layers.ui.components.data.InformationStatisticItemData;
import vn.easycare.layers.ui.models.base.IInformationStatisticModel;
import vn.easycare.utils.AppConstants;

/**
 * Created by phan on 12/16/2014.
 */
public class InformationStatisticModel implements IInformationStatisticModel,IWSResponse {
    private Context mContext;
    private IResponseUIDataCallback mCallback;

    public InformationStatisticModel(Context context,IResponseUIDataCallback callback){
        mContext = context;
        this.mCallback = callback;
    }


    @Override
    public void getAllInfoStatisticForDoctor() {
        try {
            IWebServiceAccess<InfoStatisticWSModel,InfoStatisticWSParamModel> WS = WSAccessFactory.getInstance().getWebServiceAccess(
                    InfoStatisticWSAccess.class,
                    mContext,
                    this,
                    new InfoStatisticWSParamModel(WSDataSingleton.getInstance(mContext).getSessionToken()));
            WS.sendRequest();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private InformationStatisticItemData fakeDataTesting(){
        InformationStatisticItemData itemData = new InformationStatisticItemData();
        itemData.setDetailViewCount(500);
        itemData.setOrderedCount(200);
        itemData.setExaminationPendingCount(2);
        itemData.setGeneralCommentCount(276);
        itemData.setGeneralAverageRate(7);
        itemData.setWaitingTimeCommentCount(300);
        itemData.setWaitingTimeAverageRate(5);
        itemData.setFacilityCommentCount(250);
        itemData.setFacilityAverageRate(7);
        return itemData;
    }

    @Override
    public void setResponseCallback(IResponseUIDataCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void onWSResponseOK(IWebServiceModel result) {
        InfoStatisticWSModel model = (InfoStatisticWSModel)result;
        InformationStatisticItemData itemData = new InformationStatisticItemData();
        itemData.setDetailViewCount(model.getTotalViewed());
        itemData.setOrderedCount(model.getTotalAppointment());
        itemData.setExaminationPendingCount(model.getTotalWaitingAppointment());
        itemData.setGeneralCommentCount(model.getTotalComment());
        itemData.setGeneralAverageRate(model.getAvgCommonRating());
        itemData.setWaitingTimeCommentCount(model.getTotalWaitingTimeRating());
        itemData.setWaitingTimeAverageRate(model.getAvgWaitingTimeRating());
        itemData.setFacilityCommentCount(model.getTotalFacilityRating());
        itemData.setFacilityAverageRate(model.getAvgFacilityRating());
        if(mCallback != null) {
            mCallback.onResponseOK(itemData);
        }
    }

    @Override
    public void onWSResponseFailed(WSError error) {
        if(error.getStatusCode() == AppConstants.HTTP_STATUS_UNAUTHORIZED)
            mCallback.onUnauthorized();
        else
            mCallback.onResponseFail(error.getErrorMessage(),error.getFunctionTitle());
    }
}
