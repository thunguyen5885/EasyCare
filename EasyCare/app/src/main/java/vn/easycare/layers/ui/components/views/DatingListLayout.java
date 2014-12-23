package vn.easycare.layers.ui.components.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.adapters.DatingListAdapter;
import vn.easycare.layers.ui.components.adapters.DatingListPagerAdapter;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.fragments.DatingDetailFragment;
import vn.easycare.layers.ui.presenters.ExaminationAppointmentPresenterImpl;
import vn.easycare.layers.ui.presenters.base.IExaminationAppointmentPresenter;
import vn.easycare.layers.ui.views.IExaminationAppointmentView;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.DialogUtil;

/**
 * Created by ThuNguyen on 12/17/2014.
 */
public class DatingListLayout extends LinearLayout implements IExaminationAppointmentView{
    private static final int DATE_ITEM_PER_PAGE = 10;

    // For control, layout
    private ListView mLvDatingList;
    private ProgressBar mPbLoading;
    private DatingListAdapter mAdapter;
    private EditText mEdtDatingCode;
    private EditText mEdtPatientName;
    private View mSelectCalendarView;
    private TextView mTvCalendarText;
    private View mSearchLayout;
    private LoadMoreLayout mLoadMoreView;

    // For data, object
    private boolean mIsDataLoading = false;
    private boolean mIsNeedToRefresh = false;

    private int mSelectedYear;
    private int mSelectedMonth;
    private int mSelectedDay;
    private int mTotalItemCount;
    private int mPage;

    private AppointmentTime mAppointmentTime;
    private AppConstants.EXAMINATION_STATUS mDatingType;
    private List<ExaminationAppointmentItemData> mExaminationAppointmentItemDataList;
    private IExaminationAppointmentPresenter mPresenter;
    private DatingListPagerAdapter.IBroadCastToSynData mIBroadCast;

    // Key search
    private String mDatingCode;
    private String mPatientName;
    private String mDatingDate;

    private LayoutInflater mLayoutInflater;
    public DatingListLayout(Context context) {
        super(context);
        init(context);
    }

    public DatingListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DatingListLayout(Context context, AttributeSet attrs, int defStyle) {
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
        mTotalItemCount = 0;
        mDatingType = AppConstants.EXAMINATION_STATUS.WAITING;
        mPage = 1;
        mExaminationAppointmentItemDataList = new ArrayList<ExaminationAppointmentItemData>();
        mAppointmentTime = new AppointmentTime();

        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dating_pager_item_ctrl, null);
        mPbLoading = (ProgressBar) view.findViewById(R.id.pbLoading);
        mLoadMoreView = new LoadMoreLayout(getContext());
        mLoadMoreView.setOnLoadMoreClickListener(mILoadMoreClickListener);
        mLvDatingList = (ListView)view.findViewById(R.id.lvDatingList);
        mLvDatingList.addFooterView(mLoadMoreView);

        mEdtDatingCode = (EditText)view.findViewById(R.id.edtDatingCode);
        mEdtPatientName = (EditText)view. findViewById(R.id.edtPatientName);
        mSelectCalendarView = view.findViewById(R.id.rlSelectCalendarLayout);
        mSelectCalendarView.setOnClickListener(mOnClickListener);
        mTvCalendarText = (TextView)view.findViewById(R.id.tvCalendarText);
        mSearchLayout = view.findViewById(R.id.datingListSearchLayout);
        mSearchLayout.setOnClickListener(mOnClickListener);
        addView(view);

        // Apply font
        AppFnUtils.applyFontForTextViewChild(this, null);

        // Initialize object for API control
        mPresenter = new ExaminationAppointmentPresenterImpl(this, getContext());

    }
    public void setNeedToRefresh(boolean isNeedToRefresh){
        mIsNeedToRefresh = isNeedToRefresh;
    }
    public void setIBroadCast(DatingListPagerAdapter.IBroadCastToSynData broadCast){
        mIBroadCast = broadCast;
    }
    /**
     * Enforce to refresh to sync data from the change of other tabs
     */
    public void enforceToRefreshForDataChanged(){
        if(mIsNeedToRefresh){
            refreshDataWithNonSearch();
        }
    }

    private void refreshDataWithNonSearch(){
        mEdtDatingCode.setText("");
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
    public void loadNewData(){
        mTotalItemCount = 0;
        mPage = 1;
        mExaminationAppointmentItemDataList.clear();
        // Show loading
        mPbLoading.setVisibility(View.VISIBLE);
        // Hide listview
        mLvDatingList.setVisibility(View.GONE);

        // load data
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
                mPresenter.searchExaminationAppointments(mDatingCode, mPatientName, mDatingType, mDatingDate, "", "", mPage);
            } else {
                // Load all
                mPresenter.loadExaminationAppointmentsForDoctor(mDatingType, mPage);
            }
        }
    }

    /**
     * Update GUI from list of data
     */
    private void updateUI(){
        mPbLoading.setVisibility(View.GONE);
        mLvDatingList.setVisibility(View.VISIBLE);

        if(mAdapter == null){
            mAdapter = new DatingListAdapter(getContext());
            mAdapter.setWaitingList(mDatingType == AppConstants.EXAMINATION_STATUS.WAITING);
            mAdapter.setDatingItemClickListener(mDatingItemClickListener);
            mAdapter.setExaminationAppointmentItemDatas(mExaminationAppointmentItemDataList);
            mLvDatingList.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }
    public void setDateType(AppConstants.EXAMINATION_STATUS dateType){
        mDatingType = dateType;
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
                case R.id.datingListSearchLayout:
                    // Update data for search
                    mDatingCode = mEdtDatingCode.getText().toString().trim();
                    mPatientName = mEdtPatientName.getText().toString().trim();
                    mDatingDate = mTvCalendarText.getText().toString().trim();
                    loadNewData();
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
    private DatingListAdapter.IDatingItemClickListener mDatingItemClickListener = new DatingListAdapter.IDatingItemClickListener() {
        @Override
        public void onDatingDetail(String appointmentId) {
            DatingDetailFragment datingDetailFragment = new DatingDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(AppConstants.APPOINMENT_ID_KEY, appointmentId);
            datingDetailFragment.setArguments(bundle);
            ((HomeActivity) getContext()).showFragment(datingDetailFragment);
        }

        @Override
        public void onDatingCalendarChange(ExaminationAppointmentItemData itemData) {
            // TODO
            // Update appointment time from data
            //...................

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
                    // Show progress dialog here
                    mPbLoading.setVisibility(View.VISIBLE);
                    // Change examination based on appointment time
                    //mPresenter.ChangeAnExaminationAppointment();
                }
            });


        }

        @Override
        public void onDatingCalendarCancel(String appointmentId) {
            // Show progress dialog here
            mPbLoading.setVisibility(View.VISIBLE);
            mPresenter.CancelAnExaminationAppointment(appointmentId);
        }

        @Override
        public void onDatingCalendarAccept(String appointmentId) {
            // Show progress dialog here
            mPbLoading.setVisibility(View.VISIBLE);

            mPresenter.AcceptAnExaminationAppointment(appointmentId);
        }
    };


    @Override
    public void DisplayExaminationAppointmentsForDoctor(List<ExaminationAppointmentItemData> examinationAppointmentItemsList) {
        if(examinationAppointmentItemsList != null && examinationAppointmentItemsList.size() > 0){
            if(mPage == 1){ // Load for first time
                if(mExaminationAppointmentItemDataList != null){
                    mExaminationAppointmentItemDataList.clear();
                }
                mExaminationAppointmentItemDataList.addAll(examinationAppointmentItemsList);
            }else{ // Load more here
                mExaminationAppointmentItemDataList.addAll(examinationAppointmentItemsList);
            }
            mLoadMoreView.loadMoreComplete();
        }else{ // Maybe failed or data is end of list
            mLoadMoreView.closeView();
        }
        // Reset
        mIsDataLoading = false;
        mIsNeedToRefresh = false;
        // Update UI anyway
        updateUI();
    }

    @Override
    public void DisplayMessageForAcceptAppointment(String message) {
        // Hide progress dialog here
        mPbLoading.setVisibility(View.GONE);

        // In case of OK
        if(mIBroadCast != null){
            mIBroadCast.broadCast(AppConstants.EXAMINATION_STATUS.ACCEPTED);
        }
        // Reload data here
        enforceToRefreshForDataChanged();
    }

    @Override
    public void DisplayMessageForCancelAppointment(String message) {
        // Hide progress dialog here
        mPbLoading.setVisibility(View.GONE);

        if(mIBroadCast != null){
            mIBroadCast.broadCast(AppConstants.EXAMINATION_STATUS.CANCEL);
        }
        // Reload data here
        enforceToRefreshForDataChanged();
    }

    @Override
    public void DisplayMessageForChangeAppointment(String message) {
        // Hide progress dialog here
        mPbLoading.setVisibility(View.GONE);

        if(mIBroadCast != null){
            mIBroadCast.broadCast(AppConstants.EXAMINATION_STATUS.WAITING);
        }
        // Reload data here
        enforceToRefreshForDataChanged();
    }

    @Override
    public void DisplayPopupForAnAppointment(ExaminationAppointmentItemData item) {

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
