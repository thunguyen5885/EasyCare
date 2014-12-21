package vn.easycare.layers.services;

/**
 * Created by phannguyen on 12/21/14.
 */
public class WSError {
    String errorMessage;

    public WSError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
