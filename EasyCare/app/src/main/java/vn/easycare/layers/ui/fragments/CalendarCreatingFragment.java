package vn.easycare.layers.ui.fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.adapters.DateTimeAdapter;
import vn.easycare.layers.ui.components.data.AppointmentTimeData;
import vn.easycare.layers.ui.components.data.DoctorClinicAddressItemData;
import vn.easycare.layers.ui.components.data.ExaminationScheduleItemData;
import vn.easycare.layers.ui.presenters.ExaminationSchedulesPresenterImpl;
import vn.easycare.layers.ui.presenters.base.IExaminationSchedulesPresenter;
import vn.easycare.layers.ui.views.IExaminationSchedulesView;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.DateFnUtils;

/**
 * Created by ThuNguyen on 12/13/2014.
 */
public class CalendarCreatingFragment extends Fragment implements IExaminationSchedulesView, View.OnClickListener{
    // For control, layout
    private View mCalendarLayout;
    private View mNextDayLayout;
    private View mPreviousDayLayout;
    private TextView mTvDay;
    private ListView mCalendarListView;
    private DateTimeAdapter mDateTimeAdapter;

    // For data, object
    private AppointmentTimeData mMyDate;
    private boolean mIsClicked = false;
    private List<ExaminationScheduleItemData> mItemDataList;
    private IExaminationSchedulesPresenter mPresenter;
    

    public CalendarCreatingFragment(){
        mMyDate = new AppointmentTimeData();
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

        // Default for today
        Calendar calendar = Calendar.getInstance();
        mMyDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        // Update text
        loadData();

        // Create data for listview
        mDateTimeAdapter = new DateTimeAdapter(getActivity());
        mCalendarListView.setAdapter(mDateTimeAdapter);
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
    private void loadData(){
        // Update date time text
        updateDateTimeText();

        // Call API to get the schedules
        mPresenter.loadAllExaminationSchedulesForSpecificDate(mMyDate.generateDateString(AppConstants.DATE_FORMAT_YYYY_MM_DD));
    }
    private void updateDateTimeText(){
        if(mMyDate.isValidateDate()){
            mTvDay.setText(DateFnUtils.getDateTimeStringWithDayOfWeekByVietnamese(mMyDate.getYear(), mMyDate.getMonth(), mMyDate.getDay()));
        }
    }
    private void updateUI(){

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
            mMyDate.set(year, monthOfYear, dayOfMonth);
            loadData();
        }
    };
    private void showDatePickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int yearToSet = mMyDate.getYear();
        int monthToSet = mMyDate.getMonth();
        int dayToSet = mMyDate.getDay();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), mOnDateSetListener, yearToSet, monthToSet, dayToSet);
        datePickerDialog.show();
    }

    @Override
    public void DisplayAllExaminationSchedulesForSeletedDate(List<ExaminationScheduleItemData> scheduleItemsList) {

    }

    @Override
    public void DisplayAnExaminationSchedule(ExaminationScheduleItemData scheduleItem) {

    }

    @Override
    public void DisplayMessageForScheduleAction(String message) {

    }

    @Override
    public void DisplayAllDoctorClinicAddresses(List<DoctorClinicAddressItemData> doctorClinicAddressItemsList) {

    }
}
