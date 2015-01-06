package vn.easycare.layers.ui.models.base;

import java.util.List;

import vn.easycare.layers.ui.components.data.base.IBaseItemData;

/**
 * Created by phannguyen on 12/7/14.
 */
public interface IBaseModel {
    void setResponseCallback(IResponseUIDataCallback callback);
    interface IResponseUIDataCallback {
        <T extends IBaseItemData> void onResponseOK(T itemData,Class<T>... itemDataClass);
        <T extends IBaseItemData> void onResponseOK(List<T> itemDataList,Class<T>... itemDataClass);
        void onResponseFail(String message,String functionTitle);
    }
}
