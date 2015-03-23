package vn.easycare.layers.ui.components.views;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import vn.easycare.R;
import vn.easycare.layers.ui.components.adapters.CalendarGridAdapter;
import vn.easycare.layers.ui.components.data.AppointmentTimeData;
import vn.easycare.layers.ui.components.views.base.BaseLinearLayout;
import vn.easycare.utils.DateFnUtils;

/**
 * Created by ThuNguyen on 12/17/2014.
 */
public class AppointmentListGridLayout extends BaseLinearLayout {
    // For layout, view, control
    private View mFlPrevious;
    private View mFlNext;
    private TextView mTvDate;
    private GridView mGvCalendar;
    private CalendarGridAdapter mCalendarGridAdapter;
    private View mEmptyHintColor;
    private View mWaitingForConfirmedHintColor;
    private View mConfirmedHintColor;
    private View mTreatedHintColor;

    // For data, object
    private CalendarGridAdapter mGridAdapter;
    private AppointmentTimeData mMyDate;
    private boolean mIsClicked = false;
    public AppointmentListGridLayout(Context context) {
        super(context);
        init(context);
    }

    public AppointmentListGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AppointmentListGridLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.appointment_list_grid_ctrl, this);
        mFlPrevious = view.findViewById(R.id.flPrevious);
        mFlPrevious.setOnClickListener(mOnClickListener);
        mFlNext = view.findViewById(R.id.flNext);
        mFlNext.setOnClickListener(mOnClickListener);
        mTvDate = (TextView)view.findViewById(R.id.tvDate);
        mGvCalendar = (GridView)view.findViewById(R.id.gvCalendar);
        mEmptyHintColor = view.findViewById(R.id.emptyHintColor);
        mWaitingForConfirmedHintColor = view.findViewById(R.id.waitingConfirmedHintColor);
        mConfirmedHintColor = view.findViewById(R.id.confirmedHintColor);
        mTreatedHintColor = view.findViewById(R.id.treatedHintColor);

        // Begin to set background
        setBackgroundForHintControl(mEmptyHintColor, R.color.grid_item_empty_color);
        setBackgroundForHintControl(mWaitingForConfirmedHintColor, R.color.grid_item_waiting_confirmed_color);
        setBackgroundForHintControl(mConfirmedHintColor, R.color.grid_item_confirmed_color);
        setBackgroundForHintControl(mTreatedHintColor, R.color.grid_item_treated_color);
        // Also set the title
        setTitleForHintControl(mEmptyHintColor, R.string.appointment_list_empty);
        setTitleForHintControl(mWaitingForConfirmedHintColor, R.string.appointment_list_waiting_for_confirmed);
        setTitleForHintControl(mConfirmedHintColor, R.string.appointment_list_confirmed);
        setTitleForHintControl(mTreatedHintColor, R.string.appointment_list_treated);

        // Init the date
        initDate();

    }
    private void setBackgroundForHintControl(View root, int resColorId){
        View backgroundHintColor = root.findViewById(R.id.vHintColorBackground);
        backgroundHintColor.setBackgroundResource(resColorId);
    }
    private void setTitleForHintControl(View root, int resTitleId){
        TextView tvHintTitle = (TextView) root.findViewById(R.id.tvHintColorText);
        tvHintTitle.setText(resTitleId);
    }
    private void setHintNumberVal(View root, int numVal){
        TextView tvHintNumberVal = (TextView)root.findViewById(R.id.tvHintColorNumVal);
        tvHintNumberVal.setText(String.valueOf(numVal));
    }
    private void animationForNext(){
        mTvDate.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_slide_in_left));
    }
    private void animationForPrevious(){
        mTvDate.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_slide_in_right));
    }
    private void initDate(){
        if(mMyDate == null) { // Keep the last value
            mMyDate = new AppointmentTimeData();
            // Default for today
            Calendar calendar = Calendar.getInstance();
            mMyDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }
        updateDateTimeText();
    }
    private void createDumpDataAndRender(){
        List<Object> dataList = new ArrayList<Object>();
        int size = 10 + new Random().nextInt(25);
        for(int index = 0; index < size; index++){
            dataList.add("abc");
        }
        showCalendarTime(dataList);
    }
    private void updateDateTimeText(){
        if(mMyDate.isValidateDate()){
            mTvDate.setText(DateFnUtils.getDateTimeStringWithDayOfWeekByVietnamese(mMyDate.getYear(), mMyDate.getMonth(), mMyDate.getDay()));
        }
    }
    public void loadNewData(){
        createDumpDataAndRender();
    }
    public void showCalendarTime(List<Object> dataList){
        if(mCalendarGridAdapter == null){
            mCalendarGridAdapter = new CalendarGridAdapter(getContext());
            mCalendarGridAdapter.setDataList(dataList);
            mGvCalendar.setAdapter(mCalendarGridAdapter);
        }else{
            mCalendarGridAdapter.setDataList(dataList);
            mCalendarGridAdapter.notifyDataSetChanged();
        }
    }
    public void setHintNumberVal(int numForEmptyHint, int numForWaitingConfirmedHint, int numForConfirmedHint, int numForTreatedHint){
        setHintNumberVal(mEmptyHintColor, numForEmptyHint);
        setHintNumberVal(mWaitingForConfirmedHintColor, numForWaitingConfirmedHint);
        setHintNumberVal(mConfirmedHintColor, numForConfirmedHint);
        setHintNumberVal(mTreatedHintColor, numForTreatedHint);
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
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
            switch (v.getId()){
                case R.id.flPrevious:
                    // Get next day
                    DateFnUtils.previousDate(mMyDate);
                    animationForPrevious();
                    updateDateTimeText();
                    createDumpDataAndRender();
                    break;
                case R.id.flNext:
                    // Get next day
                    DateFnUtils.nextDate(mMyDate);
                    animationForNext();
                    updateDateTimeText();
                    createDumpDataAndRender();
                    break;
            }
        }
    };
}
