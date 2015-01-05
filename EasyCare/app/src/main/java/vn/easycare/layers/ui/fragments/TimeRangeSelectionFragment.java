package vn.easycare.layers.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.data.DoctorClinicAddressItemData;
import vn.easycare.layers.ui.components.data.ExaminationScheduleItemData;
import vn.easycare.layers.ui.presenters.ExaminationSchedulesPresenterImpl;
import vn.easycare.layers.ui.presenters.base.IExaminationSchedulesPresenter;
import vn.easycare.layers.ui.views.IExaminationSchedulesView;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by ThuNguyen on 12/13/2014.
 */
public class TimeRangeSelectionFragment extends Fragment implements IExaminationSchedulesView, View.OnClickListener{
    private static final int START_TIME = 7;
    private static final int END_TIME = 21;
    private static final Integer[] TIME_SLOTS = {0, 5, 10, 15, 20, 25, 30};
    // For control, layout
    private TextView mTvDate;
    private TextView mTvFromTime;
    private TextView mTvToTime;
    private TextView mTvAverageTime;
    private TextView mTvOfficeAddress;
    private EditText mEdtComment;

    private View mFromTimeLayout;
    private View mToTimeLayout;
    private View mAverageTimeLayout;
    private View mOfficeAddressLayout;
    private View mCancelLayout;
    private View mSaveLayout;
    private View mDeleteLayout;

    // For data, object
    private boolean mIsClicked = false;
    private IExaminationSchedulesPresenter mPresenter;

    private ExaminationScheduleItemData mItemData;
    private List<DoctorClinicAddressItemData> mDoctorAddressList;
    private List<CalendarTime> mTimeFromList;
    private List<CalendarTime> mTimeToList;
    private List<Integer> mTimeSlotList;
    public TimeRangeSelectionFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_time_range, container, false);
        mTvDate = (TextView) v.findViewById(R.id.tvTimeRangeDayContent);
        mTvFromTime = (TextView) v.findViewById(R.id.tvFromTime);
        mTvToTime = (TextView) v.findViewById(R.id.tvToTime);
        mTvAverageTime = (TextView) v.findViewById(R.id.tvAverageTime);
        mTvOfficeAddress = (TextView) v.findViewById(R.id.tvOfficeAddress);
        mEdtComment = (EditText) v.findViewById(R.id.edtComment);

        mFromTimeLayout = v.findViewById(R.id.rlFromTime);
        mToTimeLayout = v.findViewById(R.id.rlToTime);
        mAverageTimeLayout = v.findViewById(R.id.rlAverageTime);
        mOfficeAddressLayout = v.findViewById(R.id.rlOfficeAddressLayout);
        mCancelLayout = v.findViewById(R.id.cancelLayout);
        mSaveLayout = v.findViewById(R.id.saveLayout);
        mDeleteLayout = v.findViewById(R.id.deleteLayout);

        // Set onclick
        mFromTimeLayout.setOnClickListener(this);
        mToTimeLayout.setOnClickListener(this);
        mAverageTimeLayout.setOnClickListener(this);
        mAverageTimeLayout.setOnClickListener(this);
        mOfficeAddressLayout.setOnClickListener(this);
        mCancelLayout.setOnClickListener(this);
        mSaveLayout.setOnClickListener(this);
        mDeleteLayout.setOnClickListener(this);
        // Apply font
        AppFnUtils.applyFontForTextViewChild(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new ExaminationSchedulesPresenterImpl(this, getActivity());
        mPresenter.loadAllAddressesForDoctor();

        // Create time slot list
        mTimeSlotList = Arrays.asList(TIME_SLOTS);

        // Get arguments
        Bundle bundle = getArguments();
        if(bundle != null){
            mItemData = (ExaminationScheduleItemData) bundle.getSerializable(AppConstants.CALENDAR_ID_KEY);
        }

        if(mItemData != null){
            if(mItemData.getScheduleId() != null && mItemData.getScheduleId().length() > 0){ // Schedule has been created before
                updateUIWithUpdatedItem();
            }else{
                updateUIWithNewItem();
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            ((HomeActivity) getActivity()).showFooterSeparator();
            ((HomeActivity) getActivity()).showHeaderBackButton();
        }
    }
    private void updateUIWithNewItem(){
        mDeleteLayout.setVisibility(View.GONE);
        mTvDate.setText(mItemData.getScheduleDate());
        mTimeFromList = createCalendarTimeListFromTimeSlot(0);
        mTimeToList = createCalendarTimeListFromTimeSlot(0);
    }
    private void updateUIWithUpdatedItem(){
        mDeleteLayout.setVisibility(View.VISIBLE);
        mTvDate.setText(AppFnUtils.convertDateFormat(AppConstants.DATE_FORMAT_YYYY_MM_DD, AppConstants.DATE_FORMAT_DD_MM_YYYY, mItemData.getScheduleDate()));
        mTimeFromList = createCalendarTimeListFromTimeSlot(mItemData.getTimeSlot());
        mTimeToList = createCalendarTimeListFromTimeSlot(mItemData.getTimeSlot());
        mTvAverageTime.setText(String.valueOf(mItemData.getTimeSlot()));
        mTvFromTime.setText(mItemData.getDisplayForHourMinuteFrom());
        mTvToTime.setText(mItemData.getDisplayForHourMinuteTo());
        mEdtComment.setText(mItemData.getNote());
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
            case R.id.rlFromTime:
                Toast.makeText(getActivity(), "FromTime clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rlToTime:
                Toast.makeText(getActivity(), "ToTime clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rlAverageTime:
                Toast.makeText(getActivity(), "AverageTime clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rlOfficeAddressLayout:
                Toast.makeText(getActivity(), "OfficeAddress clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancelLayout:
                ((HomeActivity) getActivity()).onBackPressed();
                break;
            case R.id.saveLayout:
                ((HomeActivity) getActivity()).onBackPressed();
                break;
            case R.id.deleteLayout:
                break;
        }
    }

    @Override
    public void DisplayAllExaminationSchedulesForSeletedDate(List<ExaminationScheduleItemData> scheduleItemsList) {

    }

    @Override
    public void DisplayAnExaminationSchedule(ExaminationScheduleItemData scheduleItem) {

    }

    @Override
    public void DisplayMessageForScheduleActionComplete(String message) {

    }

    @Override
    public void DisplayAllDoctorClinicAddresses(List<DoctorClinicAddressItemData> doctorClinicAddressItemsList) {
        mDoctorAddressList = doctorClinicAddressItemsList;

    }

    @Override
    public void DisplayMessageIncaseError(String message) {

    }
    private List<CalendarTime> createCalendarTimeListFromTimeSlot(int timeSlot){
        List<CalendarTime> resultList = new ArrayList<CalendarTime>();
        for(int index = START_TIME; index < END_TIME; index++){
            if(timeSlot > 0) {
                for (int slotIndex = 0; slotIndex < 60; slotIndex += timeSlot) {
                    CalendarTime calendarTime = new CalendarTime();
                    calendarTime.hour = index;
                    calendarTime.minute = slotIndex;
                    resultList.add(calendarTime);
                }
            }else{
                CalendarTime calendarTime = new CalendarTime();
                calendarTime.hour = index;
                calendarTime.minute = 0;
                resultList.add(calendarTime);
            }
        }
        return resultList;
    }
    private class CalendarTime{
        private int hour;
        private int minute;
        private String displayText;

        public String getDisplayText(){
            String text = "";
            if(hour > 9){
                text += hour + ":";
            }else{
                text += "0" + hour + ":";
            }
            if(minute > 9){
                text += minute + "";
            }else{
                text += "0" + minute + "";
            }
            return text;

        }
    }
}
