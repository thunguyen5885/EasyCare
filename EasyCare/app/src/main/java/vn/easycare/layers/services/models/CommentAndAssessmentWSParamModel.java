package vn.easycare.layers.services.models;

import vn.easycare.layers.services.IWebServiceParamModel;

/**
 * Created by phan on 12/26/2014.
 */
public class CommentAndAssessmentWSParamModel implements IWebServiceParamModel {
    String token;
    String page;
    public CommentAndAssessmentWSParamModel(String token, String page) {
        this.token = token;
        this.page = page;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
