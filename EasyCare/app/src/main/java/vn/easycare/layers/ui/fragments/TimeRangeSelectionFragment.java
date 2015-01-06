package vn.easycare.layers.ui.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.adapters.SimpleTextAdapter;
import vn.easycare.layers.ui.components.data.DoctorClinicAddressItemData;
import vn.easycare.layers.ui.components.data.ExaminationScheduleItemData;
import vn.easycare.layers.ui.presenters.ExaminationSchedulesPresenterImpl;
import vn.easycare.layers.ui.presenters.base.IExaminationSchedulesPresenter;
import vn.easycare.layers.ui.views.IExaminationSchedulesView;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.DialogUtil;

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

    private Dialog mLoadingDialog;
    // For data, object
    private boolean mIsClicked = false;
    private IExaminationSchedulesPresenter mPresenter;

    private ExaminationScheduleItemData mItemData;
    private List<DoctorClinicAddressItemData> mDoctorAddressList;
    private List<CalendarTime> mTimeFromList;
    private List<CalendarTime> mTimeToList;
    private List<Integer> mTimeSlotList;

    private int mTimeFromIndex;
    private int mTimeToIndex;
    private int mTimeSlotIndex;
    private int mAddressIndex;

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
        //mTimeSlotList = Arrays.asList(TIME_SLOTS);
        mTimeSlotList = new ArrayList<Integer>();
        for(int index = 1; index <= 30; index++){
           mTimeSlotList.add(index);
        }
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

        // For time info
        mTimeFromIndex = 0;
        mTimeToIndex = 0;
        mTimeSlotIndex = 0;
        mAddressIndex = 0;
        updateTime();
    }
    private void updateUIWithUpdatedItem(){
        mDeleteLayout.setVisibility(View.VISIBLE);
        mTvDate.setText(AppFnUtils.convertDateFormat(AppConstants.DATE_FORMAT_YYYY_MM_DD, AppConstants.DATE_FORMAT_DD_MM_YYYY, mItemData.getScheduleDate()));
        mTimeFromList = createCalendarTimeListFromTimeSlot(mItemData.getTimeSlot());
        mTimeToList = createCalendarTimeListFromTimeSlot(mItemData.getTimeSlot());
        mEdtComment.setText(mItemData.getNote());
        mTimeFromIndex = findItemInCalendarTimeList(mTimeFromList, mItemData.getHourFrom(), mItemData.getMinuteFrom());
        mTimeToIndex = findItemInCalendarTimeList(mTimeToList, mItemData.getHourTo(), mItemData.getMinuteTo());
        mTimeSlotIndex = findItemInSlotList(mTimeSlotList, mItemData.getTimeSlot());

        updateTime();
    }

    private void updateTime(){
        if(mTimeFromIndex >= 0 && mTimeToIndex >= 0 && mTimeSlotIndex >= 0) {
            CalendarTime timeFrom = mTimeFromList.get(mTimeFromIndex);
            CalendarTime timeTo = mTimeToList.get(mTimeToIndex);
            mTvFromTime.setText(timeFrom.getDisplayText());
            mTvToTime.setText(timeTo.getDisplayText());

            mTvAverageTime.setText(String.valueOf(mTimeSlotList.get(mTimeSlotIndex)));
        }

    }
    private void updateAddress(){
        if(mAddressIndex != -1){
            DoctorClinicAddressItemData addressData = mDoctorAddressList.get(mAddressIndex);
            mTvOfficeAddress.setText(addressData.getClinicAddress());
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
            case R.id.rlFromTime:
                DialogUtil.showListViewDialog(getActivity(), mTimeFromList, new SimpleTextAdapter.IOnItemClickListener() {
                    @Override
                    public void onItemClickListener(int selectedPos) {
                        mTimeFromIndex = selectedPos;
                        updateTime();
                    }
                });
                break;
            case R.id.rlToTime:
                DialogUtil.showListViewDialog(getActivity(), mTimeToList, new SimpleTextAdapter.IOnItemClickListener() {
                    @Override
                    public void onItemClickListener(int selectedPos) {
                        mTimeToIndex = selectedPos;
                        updateTime();
                    }
                });
                break;
            case R.id.rlAverageTime:
                DialogUtil.showListViewDialog(getActivity(), mTimeSlotList, new SimpleTextAdapter.IOnItemClickListener() {
                    @Override
                    public void onItemClickListener(int selectedPos) {
                        mTimeSlotIndex = selectedPos;
                        updateTime();
                    }
                });
                break;
            case R.id.rlOfficeAddressLayout:
                DialogUtil.showListViewDialog(getActivity(), mDoctorAddressList, new SimpleTextAdapter.IOnItemClickListener() {
                    @Override
                    public void onItemClickListener(int selectedPos) {
                        mAddressIndex = selectedPos;
                        updateAddress();
                    }
                });
                break;
            case R.id.cancelLayout:
                ((HomeActivity) getActivity()).onBackPressed();
                break;
            case R.id.saveLayout:
                // Update data
                mItemData.setDoctorAddressId(mDoctorAddressList.get(mAddressIndex).getClinicAddressId());
                mItemData.setTimeFrom(mTimeFromList.get(mTimeFromIndex).getDisplayText());
                mItemData.setTimeTo(mTimeToList.get(mTimeToIndex).getDisplayText());
                mItemData.setTimeSlot(mTimeSlotList.get(mTimeSlotIndex));
                mItemData.setNote(mEdtComment.getText().toString());

                // Show dialog and start to update
                mLoadingDialog = DialogUtil.createLoadingDialog(getActivity(), getString(R.string.loading_dialog_in_progress));
                mLoadingDialog.show();
                mPresenter.updateExaminationSchedule(mItemData);

                break;
            case R.id.deleteLayout:
                // Show dialog and start to update
                mLoadingDialog = DialogUtil.createLoadingDialog(getActivity(), getString(R.string.loading_dialog_in_progress));
                mLoadingDialog.show();
                mPresenter.deleteExaminationSchedule(mItemData.getScheduleId());
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
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
        }
        // Show dialog confirm done
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((HomeActivity) getActivity()).onBackPressed();
            }
        }, 500);

    }

    @Override
    public void DisplayAllDoctorClinicAddresses(List<DoctorClinicAddressItemData> doctorClinicAddressItemsList) {
        mDoctorAddressList = doctorClinicAddressItemsList;
        if(mDoctorAddressList != null && mDoctorAddressList.size() > 0){
            mAddressIndex = findItemInAddressList(mDoctorAddressList, mItemData.getDoctorAddressId());
            updateAddress();
        }
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

    @Override
    public void DisplayMessageIncaseError(String message,String funcTitle) {

        DialogUtil.createInformDialog(this.getActivity(), funcTitle, message,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                }).show();
    }

    private class CalendarTime{
        private int hour;
        private int minute;

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

        @Override
        public String toString() {
            return getDisplayText();
        }
    }
    private int findItemInCalendarTimeList(List<CalendarTime> calendarList, int hour, int minute){
        int foundIndex = 0;
        for(int index = 0; index < calendarList.size(); index++){
            CalendarTime calendarTime = calendarList.get(index);
            if(calendarTime.hour == hour && calendarTime.minute == minute){
                foundIndex = index;
                break;
            }
        }
        return foundIndex;
    }
    private int findItemInSlotList(List<Integer> slotList, int slot){
        int foundIndex = 0;
        for(int index = 0; index < slotList.size(); index++){
            if(slotList.get(index) == slot){
                foundIndex = index;
                break;
            }
        }
        return foundIndex;
    }
    private int findItemInAddressList(List<DoctorClinicAddressItemData> addressList, String addressId){
        int foundIndex = 0;
        for(int index = 0; index < addressList.size(); index++){
            DoctorClinicAddressItemData itemData = addressList.get(index);
            if(itemData.getClinicAddressId().equalsIgnoreCase(addressId)){
                foundIndex = index;
                break;
            }
        }
        return foundIndex;
    }
}
