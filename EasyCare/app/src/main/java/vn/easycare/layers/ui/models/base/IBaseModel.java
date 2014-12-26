package vn.easycare.layers.ui.models.base;

import java.util.List;

import vn.easycare.layers.ui.components.data.base.IBaseItemData;

/**
 * Created by phannguyen on 12/7/14.
 */
public interface IBaseModel {
    void setResponseCallback(IResponseUIDataCallback callback);
    interface IResponseUIDataCallback {
        void onResponseOK(IBaseItemData itemData);
        void onResponseOK(List<? extends IBaseItemData> itemDataList);
        void onResponseFail(String message);
    }
}
