package vn.easycare.layers.services.concretes;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vn.easycare.layers.services.AbstractWSAccess;
import vn.easycare.layers.services.IWebServiceParamModel;
import vn.easycare.layers.services.WSDataSingleton;
import vn.easycare.layers.services.WSError;
import vn.easycare.layers.services.models.AuthorizationWSParamModel;
import vn.easycare.layers.services.models.InfoStatisticWSModel;
import vn.easycare.layers.services.models.InfoStatisticWSParamModel;
import vn.easycare.layers.services.models.builders.InfoStatisticWSBuilder;

/**
 * Created by phan on 12/26/2014.
 */
public class InfoStatisticWSAccess extends AbstractWSAccess<InfoStatisticWSModel,InfoStatisticWSParamModel> {

    private static final String STATISTIC_URI = WEBSERVICE_HOST + "/doctors/statistics?token=%s";
    private static final String Res_statistics = "statistics";
    private static final String Res_totalViewed = "totalViewed";
    private static final String Res_totalAppointment = "totalAppointment";
    private static final String Res_totalWaitingAppointment = "totalWaitingAppointment";
    private static final String Res_totalCommonRating = "totalCommonRating";
    private static final String Res_totalFacilityRating = "totalFacilityRating";
    private static final String Res_totalWaitingTimeRating = "totalWaitingTimeRating";
    private static final String Res_totalComment = "totalComment";
    private static final String Res_avgCommonRating = "avgCommonRating";
    private static final String Res_avgFacilityRating = "avgFacilityRating";
    private static final String Res_avgWaitingTimeRating = "avgWaitingTimeRating";
    private static final String Param_Token = "token";
    private InfoStatisticWSParamModel mParam;

    public InfoStatisticWSAccess() {
    }

    @Override
    public String getWSURL() {
        return String.format(STATISTIC_URI,mParam.getToken());
    }

    @Override
    public Map<String, String> getWSParams() {

        return null;
    }

    @Override
    public void setWSParams(IWebServiceParamModel params) {
        mParam = (InfoStatisticWSParamModel)params;
    }

    @Override
    public int getMethod() {
        return Request.Method.GET;
    }

    @Override
    public void onParseJsonResponseOK(String jsonResponse) {
        try {
            InfoStatisticWSBuilder modelBuilder = new  InfoStatisticWSBuilder();
            JSONObject jsonBigObj = new JSONObject(jsonResponse);
            JSONObject jsonObj = (JSONObject)jsonBigObj.get(Res_statistics);
            if(jsonObj!=null) {
                modelBuilder.withTotalViewed(Integer.valueOf(jsonObj.get(Res_totalViewed).toString()).intValue());
                modelBuilder.withTotalAppointment(Integer.valueOf(jsonObj.get(Res_totalAppointment).toString()).intValue());
                modelBuilder.withTotalWaitingAppointment(Integer.valueOf(jsonObj.get(Res_totalWaitingAppointment).toString()).intValue());
                modelBuilder.withTotalCommonRating(Integer.valueOf(jsonObj.get(Res_totalCommonRating).toString()).intValue());
                modelBuilder.withTotalFacilityRating(Integer.valueOf(jsonObj.get(Res_totalFacilityRating).toString()).intValue());
                modelBuilder.withTotalWaitingTimeRating(Integer.valueOf(jsonObj.get(Res_totalWaitingTimeRating).toString()).intValue());
                modelBuilder.withTotalComment(Integer.valueOf(jsonObj.get(Res_totalComment).toString()).intValue());
                modelBuilder.withAvgCommonRating(Float.valueOf(jsonObj.get(Res_avgCommonRating).toString()).floatValue());
                modelBuilder.withAvgFacilityRating(Float.valueOf(jsonObj.get(Res_avgFacilityRating).toString()).floatValue());
                modelBuilder.withAvgWaitingTimeRating(Float.valueOf(jsonObj.get(Res_avgWaitingTimeRating).toString()).floatValue());
            }
            if(mCallback!=null)
                mCallback.onWSResponseOK(modelBuilder.build());
        } catch (JSONException e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage()));
        }
        catch (Exception e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage()));
        }
    }

}
