package vn.easycare.layers.ui.components.data;

import java.io.Serializable;

import vn.easycare.layers.ui.components.data.base.IBaseItemData;

/**
 * Created by phannguyen on 3/22/15.
 */
public class MessageManagementItemData implements Serializable,IBaseItemData {
    String messageId ="";
    String senderAvatar;
    String senderAvatarThumb;
    String senderName;
    String sentTime;
    String messageContent;
    String placeExaminationToDoctorName;
    String departmentName;
    Boolean isRead;

    int totalPages;
    int currentPage;
    int lastPage;
    int itemsPerPage;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getPlaceExaminationToDoctorName() {
        return placeExaminationToDoctorName;
    }

    public void setPlaceExaminationToDoctorName(String placeExaminationToDoctorName) {
        this.placeExaminationToDoctorName = placeExaminationToDoctorName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public int getTotalItems() {
        return totalPages;
    }

    public void setTotalItems(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public String getSenderAvatar() {
        return senderAvatar;
    }

    public void setSenderAvatar(String senderAvatar) {
        this.senderAvatar = senderAvatar;
    }

    public String getSenderAvatarThumb() {
        return senderAvatarThumb;
    }

    public void setSenderAvatarThumb(String senderAvatarThumb) {
        this.senderAvatarThumb = senderAvatarThumb;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
