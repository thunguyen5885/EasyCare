package vn.easycare.layers.ui.components.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.adapters.AppointmentListForAPatientAdapter;
import vn.easycare.layers.ui.components.adapters.AppointmentListPagerAdapter;
import vn.easycare.layers.ui.components.adapters.SimpleTextAdapter;
import vn.easycare.layers.ui.components.data.AppointmentTimeData;
import vn.easycare.layers.ui.components.data.DoctorClinicAddressItemData;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.components.views.base.BaseLinearLayout;
import vn.easycare.layers.ui.fragments.AppointmentDetailFragment;
import vn.easycare.layers.ui.presenters.ExaminationAppointmentPresenterImpl;
import vn.easycare.layers.ui.presenters.base.IExaminationAppointmentPresenter;
import vn.easycare.layers.ui.views.IExaminationAppointmentView;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.DateFnUtils;
import vn.easycare.utils.DialogUtil;

/**
 * Created by ThuNguyen on 12/17/2014.
 */
public class AppointmentListForAPatientLayout extends BaseLinearLayout implements IExaminationAppointmentView{
    private static final int DATE_ITEM_PER_PAGE = 10;

    // For control, layout
    private ListView mLvAppointmentList;
    private ProgressBar mPbLoading;
    private TextView mTvNoData;
    private EditText mEdtAppointmentCode;
    private View mSelectCalendarView;
    private TextView mTvCalendarText;
    private View mSearchLayout;
    private View mChooseTimeSearch;
    private TextView mTvChooseTimeResult;

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
    private int mCriteriaDateIndex;

    private AppointmentListForAPatientAdapter mAdapter;
    private AppointmentTimeData mAppointmentTimeDataForChangeCalendar;
    private AppointmentTimeData mAppointmentTimeDataForSelectCalendar;
    private AppConstants.EXAMINATION_STATUS mAppointmentType;
    private List<ExaminationAppointmentItemData> mExaminationAppointmentItemDataList;
    private IExaminationAppointmentPresenter mPresenter;
    private AppointmentListPagerAdapter.IBroadCastToSynData mIBroadCast;
    private List<String> mDateSearchList;

    // Key search
    private String mAppointmentCode;
    private String mAppointmentDate;

    private LayoutInflater mLayoutInflater;
    public AppointmentListForAPatientLayout(Context context) {
        super(context);
        init(context);
    }

    public AppointmentListForAPatientLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AppointmentListForAPatientLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    private void init(Context context) {
        mSelectedYear = -1;
        mSelectedMonth = -1;
        mSelectedDay = -1;

        // For key and result search
        mAppointmentCode = "";
        mAppointmentDate = "";
        mAppointmentType = AppConstants.EXAMINATION_STATUS.WAITING;
        mPage = 1;

        mExaminationAppointmentItemDataList = new ArrayList<ExaminationAppointmentItemData>();
        mAppointmentTimeDataForChangeCalendar = new AppointmentTimeData();
        mAppointmentTimeDataForSelectCalendar = new AppointmentTimeData();

        // Get string list for criteria date for search
        String[] criteriaArr = getResources().getStringArray(R.array.search_criteria_for_date);
        mDateSearchList = Arrays.asList(criteriaArr);

        // Inflate to get view
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.appointment_for_a_patient_pager_item_ctrl, null);
        mPbLoading = (ProgressBar) view.findViewById(R.id.pbLoading);
        mTvNoData = (TextView) view.findViewById(R.id.tvNoData);

        mLoadMoreView = new LoadMoreLayout(getContext());
        mLoadMoreView.setOnLoadMoreClickListener(mILoadMoreClickListener);
        mLvAppointmentList = (ListView)view.findViewById(R.id.lvAppointmentList);
        mLvAppointmentList.addFooterView(mLoadMoreView);

        mEdtAppointmentCode = (EditText)view.findViewById(R.id.edtAppointmentCode);
        mSelectCalendarView = view.findViewById(R.id.rlSelectCalendarLayout);
        mSelectCalendarView.setOnClickListener(mOnClickListener);
        mTvCalendarText = (TextView)view.findViewById(R.id.tvCalendarText);
        mSearchLayout = view.findViewById(R.id.appointmentListSearchLayout);
        mSearchLayout.setOnClickListener(mOnClickListener);
        mChooseTimeSearch = view.findViewById(R.id.rlChooseTimeSearch);
        mChooseTimeSearch.setOnClickListener(mOnClickListener);
        mTvChooseTimeResult = (TextView) view.findViewById(R.id.tvChooseTimeResult);

        // Update date search first
        updateDateSearch(0);
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
    private void resetField(){
        mEdtAppointmentCode.setText("");
        mTvCalendarText.setText("");
        mAppointmentCode = "";
        mAppointmentDate = "";

        mSelectedYear = -1;
        mSelectedMonth = -1;
        mSelectedDay = -1;

        updateDateSearch(0);
    }
    private void updateDateSearch(int index){
        mCriteriaDateIndex = index;
        mTvChooseTimeResult.setText(mDateSearchList.get(mCriteriaDateIndex));
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
        resetField();

        // Load new data
        loadNewData();
    }
    public void refreshDataAndShowLoading(){
       resetField();

        mLoadingDialog = DialogUtil.createLoadingDialog(getContext(), getResources().getString(R.string.loading_dialog_in_progress));
        mLoadingDialog.show();
        // Load new data
        mPage = 1;
        mExaminationAppointmentItemDataList.clear();
        loadData();
    }
    /**
     * When user did the "Change" or "Cancel", need to refresh data
     */
    public void refreshDataWhenDataChanged(){
        resetField();

        mPage = 1;
        mExaminationAppointmentItemDataList.clear();
        loadData();
    }
    public void loadNewData(){
        mPage = 1;
        mExaminationAppointmentItemDataList.clear();
        // Show loading
        mPbLoading.setVisibility(View.VISIBLE);
        // Hide listview
        mLvAppointmentList.setVisibility(View.GONE);

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
            if (mAppointmentCode.length() > 0 ||
                    mAppointmentDate.length() > 0 ||
                    mCriteriaDateIndex > 0) { // Search
                AppointmentTimeData fromDate = new AppointmentTimeData();
                AppointmentTimeData toDate = new AppointmentTimeData();
                if(mCriteriaDateIndex == 1){ // Today
                    DateFnUtils.getCurrentDate(fromDate);
                    DateFnUtils.getCurrentDate(toDate);
                }else if(mCriteriaDateIndex == 2){ // This week
                    DateFnUtils.getDateRangeOfWeek(fromDate, toDate);
                }else if(mCriteriaDateIndex == 3){ // This month
                    DateFnUtils.getDateRangeOfMonth(fromDate, toDate);
                }
                String fromDateStr = fromDate.generateDateString(AppConstants.DATE_FORMAT_YYYY_MM_DD);
                String toDateStr = toDate.generateDateString(AppConstants.DATE_FORMAT_YYYY_MM_DD);

                mPresenter.searchExaminationAppointments(mAppointmentCode, "", (mPatientId != null)?mPatientId:"", mAppointmentType, mAppointmentDate, fromDateStr, toDateStr, mPage);
            } else {
                // Load all
                mPresenter.loadExaminationAppointmentsForDoctor(mAppointmentType, (mPatientId != null)?mPatientId:"", mPage);
            }
        }
    }

    /**
     * Update GUI from list of data
     */
    private void updateUI(final boolean isEndOfList){
        mPbLoading.setVisibility(View.GONE);
        mLvAppointmentList.setVisibility(View.VISIBLE);
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
        }
        if(mExaminationAppointmentItemDataList.size() == 0){
            mTvNoData.setVisibility(View.VISIBLE);
        }else{
            mTvNoData.setVisibility(View.GONE);
        }

        if(mAdapter == null || mPage == 1){
            mAdapter = new AppointmentListForAPatientAdapter(getContext());
            mAdapter.setWaitingList(mAppointmentType == AppConstants.EXAMINATION_STATUS.WAITING);
            mAdapter.setEndOfList(isEndOfList);
            mAdapter.setAppointmentItemClickListener(mAppointmentItemClickListener);
            mAdapter.setExaminationAppointmentItemDatas(mExaminationAppointmentItemDataList);
            mLvAppointmentList.setAdapter(mAdapter);
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
                    mAppointmentCode = mEdtAppointmentCode.getText().toString().trim();
                    mAppointmentDate = mAppointmentTimeDataForSelectCalendar.generateDateString(AppConstants.DATE_FORMAT_YYYY_MM_DD);
                    beginSearch();

                    break;
                case R.id.rlChooseTimeSearch:
                    // Show dialog to choose time
                    DialogUtil.showListViewDialog(getContext(), mDateSearchList, false, new SimpleTextAdapter.IOnItemClickListener() {
                        @Override
                        public void onItemClickListener(int selectedPos) {
                            // Update time
                            updateDateSearch(selectedPos);
                        }
                    });
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
            mAppointmentTimeDataForSelectCalendar.set(year, monthOfYear, dayOfMonth);

            // Update on UI
            mTvCalendarText.setText(mAppointmentTimeDataForSelectCalendar.generateDateString(AppConstants.DATE_FORMAT_DD_MM_YYYY));
        }
    };
    private AppointmentListForAPatientAdapter.IAppointmentItemClickListener mAppointmentItemClickListener = new AppointmentListForAPatientAdapter.IAppointmentItemClickListener() {
        @Override
        public void onAppointmentDetail(ExaminationAppointmentItemData itemData) {
            AppointmentDetailFragment appointmentDetailFragment = new AppointmentDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(AppConstants.APPOINTMENT_ID_KEY, itemData.getExaminationId());
            bundle.putBoolean(AppConstants.EXAMINATION_TYPE_KEY, mAppointmentType == AppConstants.EXAMINATION_STATUS.WAITING);
            appointmentDetailFragment.setArguments(bundle);
            ((HomeActivity) getContext()).showFragment(appointmentDetailFragment);
        }

        @Override
        public void onAppointmentCalendarChange(final ExaminationAppointmentItemData itemData) {
            // TODO
            // Update appointment time from data
            //...................
            //itemData.
            // Show datetime dialog here
            DialogUtil.showDateTimeDialog(getContext(), mAppointmentTimeDataForChangeCalendar, new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mAppointmentTimeDataForChangeCalendar.set(year, monthOfYear, dayOfMonth);
                }
            }, new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    mAppointmentTimeDataForChangeCalendar.set(hourOfDay, minute);
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
        public void onAppointmentCalendarCancel(final String appointmentId) {
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
        public void onAppointmentCalendarAccept(final String appointmentId) {
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
                mLvAppointmentList.removeFooterView(mLoadMoreView);
            }else{
                isEndOfList = false;
                mLvAppointmentList.removeFooterView(mLoadMoreView);
                mLoadMoreView.loadMoreComplete();
                mLvAppointmentList.addFooterView(mLoadMoreView);
            }
        }else{ // Maybe failed or data is end of list
            isEndOfList = true;
            mLoadMoreView.closeView();
            mLvAppointmentList.removeFooterView(mLoadMoreView);
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
    public void DisplayMessageForUpdateDoctorNote(String message) {

    }

    @Override
    public void DisplayDetailForAnAppointment(ExaminationAppointmentItemData item) {

    }

    @Override
    public void DisplayMessageIncaseError(String message, String funcTitle) {
        DialogUtil.createInformDialog(this.getContext(), funcTitle, message,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Reset
                        mIsDataLoading = false;
                        mIsNeedToRefresh = false;

                        mLvAppointmentList.removeFooterView(mLoadMoreView);
                        mLoadMoreView.loadMoreComplete();

                        // Update UI anyway
                        updateUI(true);
                        dialogInterface.dismiss();
                    }
                }).show();
    }



    @Override
    public void DisplayAllDoctorClinicAddresses(List<DoctorClinicAddressItemData> doctorClinicAddressItemsList) {

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

}
