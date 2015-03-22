package vn.easycare.layers.ui.components.data;

/**
 * Created by phannguyen on 3/22/15.
 */
public class DoctorManagementItemData {
    private String doctorFullName="";
    private String doctorAvatar="";
    private String doctorId="";
    private String doctorEmail="";
    private String doctorPhone="";
    private String doctorAvatarThumb="";

    int totalPages;
    int currentPage;
    int lastPage;
    int itemsPerPage;

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    public String getDoctorAvatar() {
        return doctorAvatar;
    }

    public void setDoctorAvatar(String doctorAvatar) {
        this.doctorAvatar = doctorAvatar;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getDoctorAvatarThumb() {
        return doctorAvatarThumb;
    }

    public void setDoctorAvatarThumb(String doctorAvatarThumb) {
        this.doctorAvatarThumb = doctorAvatarThumb;
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
}
