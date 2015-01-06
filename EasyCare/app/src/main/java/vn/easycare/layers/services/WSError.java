package vn.easycare.layers.services;

/**
 * Created by phannguyen on 12/21/14.
 */
public class WSError {
    String errorMessage;
    String functionTitle;

    public WSError(String errorMessage, String functionTitle) {
        this.errorMessage = errorMessage;
        this.functionTitle = functionTitle;
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
}
