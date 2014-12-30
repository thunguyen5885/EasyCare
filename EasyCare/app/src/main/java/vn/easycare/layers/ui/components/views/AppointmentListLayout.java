package vn.easycare.layers.ui.components.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.adapters.AppointmentListAdapter;
import vn.easycare.layers.ui.components.adapters.AppointmentListPagerAdapter;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.fragments.AppointmentDetailFragment;
import vn.easycare.layers.ui.presenters.ExaminationAppointmentPresenterImpl;
import vn.easycare.layers.ui.presenters.base.IExaminationAppointmentPresenter;
import vn.easycare.layers.ui.views.IExaminationAppointmentView;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.DialogUtil;

/**
 * Created by ThuNguyen on 12/17/2014.
 */
public class AppointmentListLayout extends LinearLayout implements IExaminationAppointmentView{
    private static final int DATE_ITEM_PER_PAGE = 10;

    // For control, layout
    private ListView mLvDatingList;
    private ProgressBar mPbLoading;
    private TextView mTvNoData;
    private EditText mEdtAppointmentCode;
    private EditText mEdtPatientName;
    private View mSelectCalendarView;
    private TextView mTvCalendarText;
    private View mSearchLayout;
    private LoadMoreLayout mLoadMoreView;
    private Dialog mLoadingDialog;
    // For data, object
    private String mPatientId;
    private boolean mIsDataLoading = false;
    private boolean mIsNeedToRefresh = false;

    private int mSelectedYear;
    private int mSelectedMonth;
    private int mSelectedDay;
    private int mPage;
    private int mItemCount;

    private AppointmentListAdapter mAdapter;
    private AppointmentTime mAppointmentTime;
    private AppConstants.EXAMINATION_STATUS mAppointmentType;
    private List<ExaminationAppointmentItemData> mExaminationAppointmentItemDataList;
    private IExaminationAppointmentPresenter mPresenter;
    private AppointmentListPagerAdapter.IBroadCastToSynData mIBroadCast;

    // Key search
    private String mDatingCode;
    private String mPatientName;
    private String mDatingDate;

    private LayoutInflater mLayoutInflater;
    public AppointmentListLayout(Context context) {
        super(context);
        init(context);
    }

    public AppointmentListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AppointmentListLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    private void init(Context context) {
        mSelectedYear = -1;
        mSelectedMonth = -1;
        mSelectedDay = -1;

        // For key and result search
        mDatingCode = "";
        mPatientName = "";
        mDatingDate = "";
        mAppointmentType = AppConstants.EXAMINATION_STATUS.WAITING;
        mPage = 1;
        mExaminationAppointmentItemDataList = new ArrayList<ExaminationAppointmentItemData>();
        mAppointmentTime = new AppointmentTime();

        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.appointment_pager_item_ctrl, null);
        mPbLoading = (ProgressBar) view.findViewById(R.id.pbLoading);
        mTvNoData = (TextView) view.findViewById(R.id.tvNoData);

        mLoadMoreView = new LoadMoreLayout(getContext());
        mLoadMoreView.setOnLoadMoreClickListener(mILoadMoreClickListener);
        mLvDatingList = (ListView)view.findViewById(R.id.lvAppointmentList);
        mLvDatingList.addFooterView(mLoadMoreView);

        mEdtAppointmentCode = (EditText)view.findViewById(R.id.edtAppointmentCode);
        mEdtPatientName = (EditText)view. findViewById(R.id.edtPatientName);
        mSelectCalendarView = view.findViewById(R.id.rlSelectCalendarLayout);
        mSelectCalendarView.setOnClickListener(mOnClickListener);
        mTvCalendarText = (TextView)view.findViewById(R.id.tvCalendarText);
        mSearchLayout = view.findViewById(R.id.appointmentListSearchLayout);
        mSearchLayout.setOnClickListener(mOnClickListener);
        addView(view);

        // Apply font
        AppFnUtils.applyFontForTextViewChild(this);

        // Initialize object for API control
        mPresenter = new ExaminationAppointmentPresenterImpl(this, getContext());

    }
    public void setNeedToRefresh(boolean isNeedToRefresh){
        mIsNeedToRefresh = isNeedToRefresh;
    }
    public void setIBroadCast(AppointmentListPagerAdapter.IBroadCastToSynData broadCast){
        mIBroadCast = broadCast;
    }
    public void setPatientId(String patientId){
        mPatientId = patientId;
    }
    /**
     * Enforce to refresh to sync data from the change of other tabs
     */
    public void enforceToRefreshForDataChanged(){
        if(mIsNeedToRefresh){
            refreshDataWithNonSearch();
        }
    }

    public void refreshDataWithNonSearch(){
        mEdtAppointmentCode.setText("");
        mEdtPatientName.setText("");
        mTvCalendarText.setText("");
        mDatingCode = "";
        mPatientName = "";
        mDatingDate = "";

        mSelectedYear = -1;
        mSelectedMonth = -1;
        mSelectedDay = -1;

        // Load new data
        loadNewData();
    }
    public void refreshDataAndShowLoading(){
        mEdtAppointmentCode.setText("");
        mEdtPatientName.setText("");
        mTvCalendarText.setText("");
        mDatingCode = "";
        mPatientName = "";
        mDatingDate = "";

        mSelectedYear = -1;
        mSelectedMonth = -1;
        mSelectedDay = -1;

        mLoadingDialog = DialogUtil.createLoadingDialog(getContext(), getResources().getString(R.string.loading_dialog_in_progress));
        mLoadingDialog.show();
        // Load new data
        loadData();
    }
    /**
     * When user did the "Change" or "Cancel", need to refresh data
     */
    public void refreshDataWhenDataChanged(){
        mEdtAppointmentCode.setText("");
        mEdtPatientName.setText("");
        mTvCalendarText.setText("");
        mDatingCode = "";
        mPatientName = "";
        mDatingDate = "";

        mSelectedYear = -1;
        mSelectedMonth = -1;
        mSelectedDay = -1;

        loadData();
    }
    public void loadNewData(){
        mPage = 1;
        mExaminationAppointmentItemDataList.clear();
        // Show loading
        mPbLoading.setVisibility(View.VISIBLE);
        // Hide listview
        mLvDatingList.setVisibility(View.GONE);

        // load data
        loadData();

    }
    public void beginSearch(){
        mPage = 1;
        mExaminationAppointmentItemDataList.clear();

        mLoadingDialog = DialogUtil.createLoadingDialog(getContext(), getResources().getString(R.string.loading_dialog_in_progress));
        mLoadingDialog.show();

        // Load data
        loadData();
    }
    /**
     * Load more when click on loadmore button
     */
    private void loadMoreData(){
        mPage++;
        mLoadMoreView.beginLoading();

        loadData();
    }

    /**
     * Begin call API here
     */
    private void loadData(){
        if(!mIsDataLoading) {
            mIsDataLoading = true;
            if (mDatingCode.length() > 0 ||
                    mPatientName.length() > 0 ||
                    mDatingDate.length() > 0) { // Search
                mPresenter.searchExaminationAppointments(mDatingCode, mPatientName, mAppointmentType, mDatingDate, "", "", mPage);
            } else {
                // Load all
                mPresenter.loadExaminationAppointmentsForDoctor(mAppointmentType, mPage);
            }
        }
    }

    /**
     * Update GUI from list of data
     */
    private void updateUI(final boolean isEndOfList){
        mPbLoading.setVisibility(View.GONE);
        mLvDatingList.setVisibility(View.VISIBLE);
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
        }
        if(mExaminationAppointmentItemDataList.size() == 0){
            mTvNoData.setVisibility(View.VISIBLE);
        }else{
            mTvNoData.setVisibility(View.GONE);
        }

        if(mAdapter == null){
            mAdapter = new AppointmentListAdapter(getContext());
            mAdapter.setWaitingList(mAppointmentType == AppConstants.EXAMINATION_STATUS.WAITING);
            mAdapter.setEndOfList(isEndOfList);
            mAdapter.setDatingItemClickListener(mDatingItemClickListener);
            mAdapter.setExaminationAppointmentItemDatas(mExaminationAppointmentItemDataList);
            mLvDatingList.setAdapter(mAdapter);
        }else{
            mAdapter.setEndOfList(isEndOfList);
            mAdapter.notifyDataSetChanged();
        }

    }
    public void setDateType(AppConstants.EXAMINATION_STATUS appointmentType){
        mAppointmentType = appointmentType;
    }
    private void showDatePickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int yearToSet = 0;
        int monthToSet = 0;
        int dayToSet = 0;
        if(mSelectedYear != -1){
            yearToSet = mSelectedYear;
        }else{
            yearToSet = calendar.get(Calendar.YEAR);
        }
        if(mSelectedMonth != -1){
            monthToSet = mSelectedMonth;
        }else{
            monthToSet = calendar.get(Calendar.MONTH);
        }
        if(mSelectedDay != -1){
            dayToSet = mSelectedDay;
        }else{
            dayToSet = calendar.get(Calendar.DAY_OF_MONTH);
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), mOnDateSetListener, yearToSet, monthToSet, dayToSet);
        datePickerDialog.show();
    }
    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rlSelectCalendarLayout:
                    showDatePickerDialog();
                    break;
                case R.id.appointmentListSearchLayout:
                    // Update data for search
                    mDatingCode = mEdtAppointmentCode.getText().toString().trim();
                    mPatientName = mEdtPatientName.getText().toString().trim();
                    mDatingDate = mTvCalendarText.getText().toString().trim();
                    if(mDatingCode.length() > 0 || mPatientName.length() > 0 || mDatingDate.length() > 0) {
                        beginSearch();
                    }else{
                        // Show dialog to confirm to need to fill at least one field.
                    }
                    break;
            }
        }
    };
    private LoadMoreLayout.ILoadMoreClickListener mILoadMoreClickListener = new LoadMoreLayout.ILoadMoreClickListener() {
        @Override
        public void onLoadMoreClicked() {
            loadMoreData();
        }
    };
    private DatePickerDialog.OnDateSetListener mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mSelectedYear = year;
            mSelectedMonth = monthOfYear;
            mSelectedDay = dayOfMonth;

            // Update on UI
            mTvCalendarText.setText(mSelectedDay + "/" + (mSelectedMonth + 1) + "/" + mSelectedYear);
        }
    };
    private AppointmentListAdapter.IDatingItemClickListener mDatingItemClickListener = new AppointmentListAdapter.IDatingItemClickListener() {
        @Override
        public void onDatingDetail(ExaminationAppointmentItemData itemData) {
            AppointmentDetailFragment appointmentDetailFragment = new AppointmentDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppConstants.APPOINTMENT_ID_KEY, itemData);
            bundle.putBoolean(AppConstants.EXAMINATION_TYPE_KEY, mAppointmentType == AppConstants.EXAMINATION_STATUS.WAITING);
            appointmentDetailFragment.setArguments(bundle);
            ((HomeActivity) getContext()).showFragment(appointmentDetailFragment);
        }

        @Override
        public void onDatingCalendarChange(final ExaminationAppointmentItemData itemData) {
            // TODO
            // Update appointment time from data
            //...................
            //itemData.
            // Show datetime dialog here
            DialogUtil.showDateTimeDialog(getContext(), mAppointmentTime, new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mAppointmentTime.set(year, monthOfYear, dayOfMonth);
                }
            }, new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    mAppointmentTime.set(hourOfDay, minute);
                }
            }, new OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*mLoadingDialog = DialogUtil.createLoadingDialog(getContext(), getResources().getString(R.string.loading_dialog_in_progress));
                    mLoadingDialog.show();
                    // Change examination based on appointment time
                    mPresenter.ChangeAnExaminationAppointment(itemData);*/
                }
            });


        }

        @Override
        public void onDatingCalendarCancel(final String appointmentId) {
            mLoadingDialog = DialogUtil.createLoadingDialog(getContext(), getResources().getString(R.string.loading_dialog_in_progress));
            mLoadingDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPresenter.CancelAnExaminationAppointment(appointmentId);
                }
            }, 2000);

        }

        @Override
        public void onDatingCalendarAccept(final String appointmentId) {
            mLoadingDialog = DialogUtil.createLoadingDialog(getContext(), getResources().getString(R.string.loading_dialog_in_progress));
            mLoadingDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPresenter.AcceptAnExaminationAppointment(appointmentId);
                }
            }, 2000);
        }
    };


    @Override
    public void DisplayExaminationAppointmentsForDoctor(List<ExaminationAppointmentItemData> examinationAppointmentItemsList) {
        boolean isEndOfList = false;
        if(examinationAppointmentItemsList != null && examinationAppointmentItemsList.size() > 0){
            if(mPage == 1){ // Load for first time
                if(mExaminationAppointmentItemDataList != null){
                    mExaminationAppointmentItemDataList.clear();
                }
                mItemCount = examinationAppointmentItemsList.get(0).getTotalItems();
                mExaminationAppointmentItemDataList.addAll(examinationAppointmentItemsList);
            }else{ // Load more here
                mExaminationAppointmentItemDataList.addAll(examinationAppointmentItemsList);
            }
            if(mItemCount == mExaminationAppointmentItemDataList.size()){
                isEndOfList = true;
                mLoadMoreView.closeView();
                mLvDatingList.removeFooterView(mLoadMoreView);
            }else{
                isEndOfList = false;
                mLvDatingList.removeFooterView(mLoadMoreView);
                mLoadMoreView.loadMoreComplete();
                mLvDatingList.addFooterView(mLoadMoreView);
            }
        }else{ // Maybe failed or data is end of list
            isEndOfList = true;
            mLoadMoreView.closeView();
            mLvDatingList.removeFooterView(mLoadMoreView);
        }
        // Reset
        mIsDataLoading = false;
        mIsNeedToRefresh = false;
        // Update UI anyway
        updateUI(isEndOfList);
    }

    @Override
    public void DisplayMessageForAcceptAppointment(String message) {
        processWhenUpdateDone(message, AppConstants.EXAMINATION_STATUS.ACCEPTED);
    }

    @Override
    public void DisplayMessageForCancelAppointment(String message) {
        processWhenUpdateDone(message, AppConstants.EXAMINATION_STATUS.CANCEL);
    }

    @Override
    public void DisplayMessageForChangeAppointment(String message) {
        processWhenUpdateDone(message, AppConstants.EXAMINATION_STATUS.WAITING);
    }

    @Override
    public void DisplayDetailForAnAppointment(ExaminationAppointmentItemData item) {

    }

    @Override
    public void DisplayMessageIncaseError(String message) {
        // Reset
        mIsDataLoading = false;
        mIsNeedToRefresh = false;

        mLvDatingList.removeFooterView(mLoadMoreView);
        mLoadMoreView.loadMoreComplete();
        mLvDatingList.addFooterView(mLoadMoreView);

        // Update UI anyway
        updateUI(true);
    }

    private void processWhenUpdateDone(String message, AppConstants.EXAMINATION_STATUS status){
        boolean isUpdatedDone = true;
        if(isUpdatedDone) {
            // In case of OK
            if (mIBroadCast != null) {
                mIBroadCast.broadCast(status);
            }
            // Reload data here
            refreshDataWhenDataChanged();
        }else{
            if(mLoadingDialog != null){
                mLoadingDialog.dismiss();
            }
        }
    }
    public class AppointmentTime{
        private int year;
        private int month;
        private int day;
        private int hour;
        private int minute;

        public AppointmentTime(){
            year = -1;
            month = -1;
            day = -1;
            hour = -1;
            minute = -1;
        }
        public void set(int year, int month, int day){
            this.year = year;
            this.month = month;
            this.day = day;
        }
        public void set(int hour, int minute){
            this.hour = hour;
            this.minute = minute;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }
    }
}