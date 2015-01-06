package vn.easycare.layers.services;

import vn.easycare.utils.AppConstants;

/**
 * Created by phannguyen on 12/21/14.
 */
public class WSError {
    String errorMessage;
    String functionTitle;
    int StatusCode;

    public WSError(String errorMessage, String functionTitle, int statusCode) {
        this.errorMessage = errorMessage;
        this.functionTitle = functionTitle;
        StatusCode = statusCode;
    }

    public WSError(String errorMessage, String functionTitle) {
        this.errorMessage = errorMessage;
        this.functionTitle = functionTitle;
        this.StatusCode = AppConstants.APP_EXCEPTION_STATUS_CODE;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getFunctionTitle() {
        return functionTitle;
    }

    public void setFunctionTitle(String functionTitle) {
        this.functionTitle = functionTitle;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }
}
