package vn.easycare.layers.services.concretes;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import vn.easycare.R;
import vn.easycare.layers.services.AbstractWSAccess;
import vn.easycare.layers.services.IWebServiceParamModel;
import vn.easycare.layers.services.WSError;
import vn.easycare.layers.services.models.CommentAndAssessmentListWSModel;
import vn.easycare.layers.services.models.CommentAndAssessmentWSModel;
import vn.easycare.layers.services.models.CommentAndAssessmentWSParamModel;
import vn.easycare.layers.services.models.builders.CommentAndAssessmentWSBuilder;

/**
 * Created by phan on 12/26/2014.
 */
public class CommentAndAssessmentWSAccess extends AbstractWSAccess<CommentAndAssessmentListWSModel,CommentAndAssessmentWSParamModel> {

    private static final String COMMENT_URI = WEBSERVICE_HOST + "/doctors/comments?token=%s&page=%s";
    private static final String Res_comments = "comments";
    private static final String Res_id = "id";
    private static final String Res_comment = "comment";
    private static final String Res_commonRating = "commonRating";
    private static final String Res_facilityRating = "facilityRating";
    private static final String Res_waitingTimeRating = "waitingTimeRating";
    private static final String Res_isShow = "isShow";
    private static final String Res_createdAt = "createdAt";
    private static final String Res_commentBy = "commentBy";
    private static final String Res_full_name = "full_name";
    private static final String Res_avatar = "avatar";
    private static final String Res_avatar_thumb = "avatar_thumb";
    private static final String Res_paging = "paging";
    private static final String Res_pagetotal = "total";
    private static final String Res_currentPage = "currentPage";
    private static final String Res_lastPage = "lastPage";
    private static final String Res_itemsPerPage = "itemsPerPage";
    private static final String Param_Token = "token";
    private static final String Param_page = "page";
    private CommentAndAssessmentWSParamModel mParam;

    public CommentAndAssessmentWSAccess() {
    }
    @Override
    public String getWSURL() {
        return String.format(COMMENT_URI,mParam.getToken(),mParam.getPage());

    }

    @Override
    public Map<String, String> getWSParams() {
        return null;
    }

    @Override
    public void setWSParams(IWebServiceParamModel params) {
        mParam = (CommentAndAssessmentWSParamModel)params;
    }

    @Override
    public int getMethod() {
        return Request.Method.GET;
    }

    @Override
    public String getRequestTitle() {
        return mContext.getResources().getString(R.string.title_comments_list);
    }
    @Override
    public void onParseJsonResponseOK(String jsonResponse) {
        try {
            CommentAndAssessmentWSBuilder modelBuilder = new  CommentAndAssessmentWSBuilder();
            CommentAndAssessmentListWSModel listModel = new CommentAndAssessmentListWSModel();

            JSONObject jsonBigObj = new JSONObject(jsonResponse);
            JSONArray comments = jsonBigObj.getJSONArray(Res_comments);

            for (int i = 0 ; i < comments.length(); i++) {
                JSONObject jsonObj = comments.getJSONObject(i);
                modelBuilder.Clear();
                modelBuilder.withId(jsonObj.optString(Res_id,""));
                modelBuilder.withComment(jsonObj.optString(Res_comment,""));
                modelBuilder.withCommonRating(Integer.valueOf(jsonObj.optString(Res_commonRating,"0")).intValue());
                modelBuilder.withFacilityRating(Integer.valueOf(jsonObj.optString(Res_facilityRating,"0")).intValue());
                modelBuilder.withWaitingTimeRating(Integer.valueOf(jsonObj.optString(Res_waitingTimeRating,"0")).intValue());
                modelBuilder.withIsShow(Integer.valueOf(jsonObj.optString(Res_isShow,"0")).intValue());
                modelBuilder.withCreatedAt(jsonObj.optString(Res_createdAt,""));

                JSONObject commentByJsonObj = (JSONObject)jsonObj.get(Res_commentBy);
                modelBuilder.withCommentByPatientId(commentByJsonObj.optString(Res_id,""));
                modelBuilder.withCommentByPatientName(commentByJsonObj.optString(Res_full_name,""));
                modelBuilder.withCommentByPatientAvatarUrl(commentByJsonObj.optString(Res_avatar,""));
                modelBuilder.withCommentByPatientAvatarThumbUrl(commentByJsonObj.optString(Res_avatar_thumb,""));

                CommentAndAssessmentWSModel model = modelBuilder.build();
                if(model.getIsShow()==1)
                    listModel.getListComments().add(model);
            }

            JSONObject pageJsonObj = (JSONObject)jsonBigObj.get(Res_paging);
            listModel.setItems_total(Integer.valueOf(pageJsonObj.optString(Res_pagetotal,"0")).intValue());
            listModel.setPage_currentPage(Integer.valueOf(pageJsonObj.optString(Res_currentPage,"0")).intValue());
            listModel.setLastPage(Integer.valueOf(pageJsonObj.optString(Res_lastPage,"0")).intValue());
            listModel.setItemsPerPage(Integer.valueOf(pageJsonObj.optString(Res_itemsPerPage,"0")).intValue());


            if(mCallback!=null)
                mCallback.onWSResponseOK(listModel);
        } catch (JSONException e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage(),getRequestTitle()));
        }
        catch (Exception e) {
            if(mCallback!=null)
                mCallback.onWSResponseFailed(new WSError(e.getMessage(),getRequestTitle()));
        }
    }
}
