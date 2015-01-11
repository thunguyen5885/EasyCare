package vn.easycare.layers.ui.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.base.BaseFragment;
import vn.easycare.layers.ui.components.adapters.CalendarTimeAdapter;
import vn.easycare.layers.ui.components.data.AppointmentTimeData;
import vn.easycare.layers.ui.components.data.DoctorClinicAddressItemData;
import vn.easycare.layers.ui.components.data.ExaminationScheduleItemData;
import vn.easycare.layers.ui.presenters.ExaminationSchedulesPresenterImpl;
import vn.easycare.layers.ui.presenters.base.IExaminationSchedulesPresenter;
import vn.easycare.layers.ui.views.IExaminationSchedulesView;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.DateFnUtils;
import vn.easycare.utils.DialogUtil;

/**
 * Created by ThuNguyen on 12/13/2014.
 */
public class CalendarCreatingFragment extends BaseFragment implements IExaminationSchedulesView, View.OnClickListener{
    // For control, layout
    private View mCalendarLayout;
    private View mNextDayLayout;
    private View mPreviousDayLayout;
    private TextView mTvDay;
    private ListView mCalendarListView;
    private CalendarTimeAdapter mCalendarTimeAdapter;

    // For data, object
    private AppointmentTimeData mMyDate;
    private boolean mIsClicked = false;
    private boolean mIsDateSet = false;
    private List<ExaminationScheduleItemData> mItemDataList;
    private IExaminationSchedulesPresenter mPresenter;
    private ProgressBar mPbLoading;
    private Dialog mLoadingDialog;

    public CalendarCreatingFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mItemDataList = new ArrayList<ExaminationScheduleItemData>();
        mPresenter = new ExaminationSchedulesPresenterImpl(this, getActivity());

        View v = inflater.inflate(R.layout.fragment_calendar_creating, container, false);
        mCalendarLayout = v.findViewById(R.id.calendarLayout);
        mNextDayLayout = v.findViewById(R.id.nextDayLayout);
        mPreviousDayLayout = v.findViewById(R.id.previousDayLayout);
        mTvDay = (TextView) v.findViewById(R.id.tvDateTime);
        mCalendarListView = (ListView) v.findViewById(R.id.calendarListView);
        mPbLoading = (ProgressBar) v.findViewById(R.id.pbLoading);

        // Set on click
        mCalendarLayout.setOnClickListener(this);
        mNextDayLayout.setOnClickListener(this);
        mPreviousDayLayout.setOnClickListener(this);

        // Apply font
        AppFnUtils.applyFontForTextViewChild(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mMyDate == null) { // Keep the last value
            mMyDate = new AppointmentTimeData();
            // Default for today
            Calendar calendar = Calendar.getInstance();
            mMyDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }
        // Update text
        loadDataOnFirst();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            ((HomeActivity) getActivity()).hideFooterSeparator();
            ((HomeActivity) getActivity()).showHeaderBackButton();
        }
    }

    @Override
    public void onClick(View v) {
        if(mIsClicked){
            return;
        }
        mIsClicked = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mIsClicked = false;
            }
        }, 500);
        switch (v.getId()) {
            case R.id.calendarLayout:
                showDatePickerDialog();
                break;
            case R.id.nextDayLayout:
                // Get next day
                DateFnUtils.nextDate(mMyDate);
                // Update
                loadData();
                animationForNext();
                break;
            case R.id.previousDayLayout:
                // Get next day
                DateFnUtils.previousDate(mMyDate);
                // Update
                loadData();
                animationForPrevious();
                break;
        }
    }
    private void loadDataOnFirst(){
        // Update date time text
        updateDateTimeText();

        mPbLoading.setVisibility(View.VISIBLE);
        mCalendarListView.setVisibility(View.GONE);

        // Call API to get the schedules
        mPresenter.loadAllExaminationSchedulesForSpecificDate(mMyDate.generateDateString(AppConstants.DATE_FORMAT_YYYY_MM_DD));
    }
    private void loadData(){
        // Update date time text
        updateDateTimeText();

        mLoadingDialog = DialogUtil.createLoadingDialog(getActivity(), getString(R.string.loading_dialog_in_progress));
        mLoadingDialog.show();
        // Call API to get the schedules
        mPresenter.loadAllExaminationSchedulesForSpecificDate(mMyDate.generateDateString(AppConstants.DATE_FORMAT_YYYY_MM_DD));
    }
    private void updateDateTimeText(){
        if(mMyDate.isValidateDate()){
            mTvDay.setText(DateFnUtils.getDateTimeStringWithDayOfWeekByVietnamese(mMyDate.getYear(), mMyDate.getMonth(), mMyDate.getDay()));
        }
    }
    private void updateUI(){
        mPbLoading.setVisibility(View.GONE);
        mCalendarListView.setVisibility(View.VISIBLE);
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
        }
        mIsDateSet = false;
        // Create data for listview
        mCalendarTimeAdapter = new CalendarTimeAdapter(getActivity());
        mCalendarTimeAdapter.setItemDataList(mItemDataList);
        mCalendarTimeAdapter.setAppointmentTime(mMyDate);
        mCalendarTimeAdapter.setListView(mCalendarListView);
        mCalendarListView.setAdapter(mCalendarTimeAdapter);
//        if(mCalendarTimeAdapter == null) {
//
//        }else {
//            mCalendarTimeAdapter.setItemDataList(mItemDataList);
//            mCalendarTimeAdapter.setAppointmentTime(mMyDate);
//            mCalendarTimeAdapter.notifyDataSetChanged();
//        }
    }
    private void animationForNext(){
        mTvDay.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_slide_in_left));
    }
    private void animationForPrevious(){
        mTvDay.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_slide_in_right));
    }
    private DatePickerDialog.OnDateSetListener mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            if(!mIsDateSet) {
                mIsDateSet = true;
                mMyDate.set(year, monthOfYear, dayOfMonth);
                loadData();
            }
        }
    };
    private void showDatePickerDialog(){
        int yearToSet = mMyDate.getYear();
        int monthToSet = mMyDate.getMonth();
        int dayToSet = mMyDate.getDay();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), mOnDateSetListener, yearToSet, monthToSet, dayToSet);
        datePickerDialog.show();
    }

    @Override
    public void DisplayAllExaminationSchedulesForSeletedDate(List<ExaminationScheduleItemData> scheduleItemsList) {
        if(mItemDataList == null){
            mItemDataList = new ArrayList<ExaminationScheduleItemData>();
        }else{
            mItemDataList.clear();
        }

        mItemDataList.addAll(scheduleItemsList);
        Collections.sort(mItemDataList);
        updateUI();
    }

    @Override
    public void DisplayAnExaminationSchedule(ExaminationScheduleItemData scheduleItem) {

    }

    @Override
    public void DisplayMessageForScheduleActionComplete(String message) {

    }

    @Override
    public void DisplayAllDoctorClinicAddresses(List<DoctorClinicAddressItemData> doctorClinicAddressItemsList) {

    }

    @Override
    public void DisplayMessageIncaseError(String message,String funcTitle) {
        DialogUtil.createInformDialog(this.getActivity(), getString(R.string.message_title), getString(R.string.message_inform_schedule_get_fail),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        updateUI();
                        dialogInterface.dismiss();
                    }
                }).show();
    }
}
