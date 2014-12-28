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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.adapters.DateTimeAdapter;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.DateFnUtils;

/**
 * Created by ThuNguyen on 12/13/2014.
 */
public class DateCreatingFragment extends Fragment implements View.OnClickListener{
    // For control, layout
    private View mCalendarLayout;
    private View mNextDayLayout;
    private View mPreviousDayLayout;
    private TextView mTvDay;
    private ListView mDatingTimeListView;
    private DateTimeAdapter mDateTimeAdapter;
    // For data, object
    private MyDate mMyDate;
    private boolean mIsClicked = false;
    public DateCreatingFragment(){
        mMyDate = new MyDate();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_date_creating, container, false);
        mCalendarLayout = v.findViewById(R.id.calendarLayout);
        mNextDayLayout = v.findViewById(R.id.nextDayLayout);
        mPreviousDayLayout = v.findViewById(R.id.previousDayLayout);
        mTvDay = (TextView) v.findViewById(R.id.tvDateTime);
        mDatingTimeListView = (ListView) v.findViewById(R.id.datingTimeListView);

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
        update();

        // Create data for listview
        mDateTimeAdapter = new DateTimeAdapter(getActivity());
        mDatingTimeListView.setAdapter(mDateTimeAdapter);
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
                update();
                animationForNext();
                break;
            case R.id.previousDayLayout:
                // Get next day
                DateFnUtils.previousDate(mMyDate);
                // Update
                update();
                animationForPrevious();
                break;
        }
    }
    private void update(){
        updateDateTimeText();

    }
    private void updateDateTimeText(){
        if(mMyDate.isValidate()){
            mTvDay.setText(DateFnUtils.getDateTimeStringWithDayOfWeekByVietnamese(mMyDate.mSelectedYear, mMyDate.mSelectedMonth, mMyDate.mSelectedDay));
        }
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
            update();
        }
    };
    private void showDatePickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int yearToSet = mMyDate.mSelectedYear;
        int monthToSet = mMyDate.mSelectedMonth;
        int dayToSet = mMyDate.mSelectedDay;

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), mOnDateSetListener, yearToSet, monthToSet, dayToSet);
        datePickerDialog.show();
    }

    public class MyDate{
        private int mSelectedYear;
        private int mSelectedMonth;
        private int mSelectedDay;

        public MyDate(){
            mSelectedDay = -1;
            mSelectedMonth = -1;
            mSelectedYear = -1;
        }

        public int getSelectedYear() {
            return mSelectedYear;
        }

        public void setSelectedYear(int mSelectedYear) {
            this.mSelectedYear = mSelectedYear;
        }

        public int getSelectedMonth() {
            return mSelectedMonth;
        }

        public void setSelectedMonth(int mSelectedMonth) {
            this.mSelectedMonth = mSelectedMonth;
        }

        public int getSelectedDay() {
            return mSelectedDay;
        }

        public void setSelectedDay(int mSelectedDay) {
            this.mSelectedDay = mSelectedDay;
        }

        public void set(int year, int month, int day){
            mSelectedYear = year;
            mSelectedMonth = month;
            mSelectedDay = day;
        }
        public boolean isValidate(){
            return (mSelectedYear != -1 && mSelectedMonth != -1 && mSelectedDay != -1);
        }

    }
}
