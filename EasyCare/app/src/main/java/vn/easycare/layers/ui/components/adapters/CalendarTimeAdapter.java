package vn.easycare.layers.ui.components.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.data.AppointmentTimeData;
import vn.easycare.layers.ui.components.data.ExaminationScheduleItemData;
import vn.easycare.layers.ui.fragments.TimeRangeSelectionFragment;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by ThuNguyen on 12/21/2014.
 */
public class CalendarTimeAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ListView mListView;

    // For data, object
    private boolean mIsClicked = false;
    private boolean mIsListViewScrolling = false;
    private List<MyTime> mTimeList = new ArrayList<MyTime>();
    private List<ExaminationScheduleItemData> mItemDataList;
    private AppointmentTimeData mAppointmentTime;
    private HashMap<Integer, ViewHolder> mViewHolderMap;

    public CalendarTimeAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mViewHolderMap = new HashMap<Integer, ViewHolder>();
    }
    public void setItemDataList(List<ExaminationScheduleItemData> itemDataList){
        mItemDataList = itemDataList;
        createDisplayList();
    }
    public void setAppointmentTime(AppointmentTimeData appointmentTime){
        mAppointmentTime = appointmentTime;
    }
    public void setListView(ListView listView){
        mListView = listView;
        mListView.setOnScrollListener(mOnScrollListener);
    }
    private void createDisplayList(){
        mTimeList.clear();
        for(int index = AppConstants.START_TIME; index <= AppConstants.END_TIME; index++){
            HashMap<Integer, ExaminationScheduleItemData> foundItemMap = new HashMap<Integer, ExaminationScheduleItemData>();
            for(int pos = 0; pos < mItemDataList.size(); pos++){
                ExaminationScheduleItemData itemData = mItemDataList.get(pos);
                if(index <= itemData.getHourTo() && index >= itemData.getHourFrom()){
                    foundItemMap.put(pos, itemData);
                }
            }
            if(foundItemMap.size() > 0){
                Iterator<Integer> iterator = foundItemMap.keySet().iterator();
                List<String> checkDuplicateList = new ArrayList<String>();
                while(iterator.hasNext()) {
                    Integer selectedPos = iterator.next();
                    ExaminationScheduleItemData foundItem = foundItemMap.get(selectedPos);
                    if (foundItem != null) {
                        int timeSlot = foundItem.getTimeSlot();
                        for (int hourIndex = foundItem.getHourFrom(); hourIndex <= foundItem.getHourTo(); hourIndex++) {
                            // Calculate the time slot threshold
                            int timeSlotThreshold = 59;
                            if (hourIndex == foundItem.getHourTo()) {
                                timeSlotThreshold = foundItem.getMinuteTo();
                            }
                            // Calculate the time slot for beginning
                            int timeSlotStart = 0;
                            if (hourIndex == foundItem.getHourFrom()) {
                                timeSlotStart = foundItem.getMinuteFrom();
                            }
                            // Add the atom time first if the minute value is over 0
                            if(timeSlotStart != 0 && hourIndex == foundItem.getHourFrom()){
                                if(!checkDuplicateList.contains(String.valueOf(hourIndex))){
                                    MyTime myTime = new MyTime();
                                    myTime.displayText = (hourIndex > 9 ? (hourIndex + "") : ("0" + hourIndex)) + ":00";
                                    myTime.isSelected = false;
                                    myTime.selectedPosInMainList = -1;
                                    mTimeList.add(myTime);
                                    checkDuplicateList.add(String.valueOf(hourIndex));
                                }
                            }
                            for (int timeSlotIndex = timeSlotStart; timeSlotIndex <= timeSlotThreshold; timeSlotIndex += timeSlot) {
                                checkDuplicateList.add(String.valueOf(hourIndex));
                                MyTime myTime = new MyTime();
                                myTime.displayText = (hourIndex > 9 ? (hourIndex + "") : ("0" + hourIndex)) + ":" + ((timeSlotIndex > 9) ? (timeSlotIndex + "") : ("0" + timeSlotIndex));
                                myTime.isSelected = true;
                                myTime.selectedPosInMainList = selectedPos;

                                mTimeList.add(myTime);
                            }
                        }
                        index = foundItem.getHourTo();
                    }
                }
            }else{
                MyTime myTime = new MyTime();
                myTime.displayText = (index > 9 ? (index + ""): ("0"+index)) + ":00";
                myTime.isSelected = false;
                myTime.selectedPosInMainList = -1;
                mTimeList.add(myTime);
            }
        }
    }
    private ExaminationScheduleItemData findTimeStartInList(int hour){
        ExaminationScheduleItemData foundItem = null;
        for(ExaminationScheduleItemData itemData: mItemDataList){
            if(hour <= itemData.getHourTo() && hour >= itemData.getHourFrom()){
                foundItem = itemData;
                break;
            }
        }
        return  foundItem;
    }
    @Override
    public int getCount() {
        return mTimeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.calendar_time_ctrl, null);
            viewHolder = new ViewHolder();
            viewHolder.mHighlight = convertView.findViewById(R.id.vDateHighlight);
            viewHolder.mTvTime = (TextView) convertView.findViewById(R.id.tvDateTime);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Add to view holder map
        mViewHolderMap.put(position, viewHolder);

        // Set onclick
        viewHolder.mHighlight.setTag(position);
        viewHolder.mHighlight.setOnClickListener(mOnClickListener);
        viewHolder.mHighlight.setOnTouchListener(mOnTouchListener);
        // Set data text
        String timeAtPos = mTimeList.get(position).displayText;
        if(timeAtPos.contains("00")){
            viewHolder.mTvTime.setTextColor(mContext.getResources().getColor(R.color.textview_color_default));
        }else {
            viewHolder.mTvTime.setTextColor(mContext.getResources().getColor(R.color.textview_color_grey));
        }
        viewHolder.mTvTime.setText(timeAtPos);

        // Set background
        boolean isSelected = mTimeList.get(position).isSelected;
        if(isSelected){
            viewHolder.mHighlight.setBackgroundColor(mContext.getResources().getColor(R.color.header_background_color));
            viewHolder.mPosAtMainList = mTimeList.get(position).selectedPosInMainList;
        }else{
            viewHolder.mHighlight.setBackgroundColor(Color.TRANSPARENT);
            viewHolder.mPosAtMainList = -1;
        }
        viewHolder.mHighlight.invalidate();

        // Apply font
        AppFnUtils.applyFontForTextViewChild(convertView);
        return convertView;
    }
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
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
                case R.id.vDateHighlight:
                    // Go to time range screen
                    TimeRangeSelectionFragment timeRangeSelectionFragment = new TimeRangeSelectionFragment();

                    // Decide to get the item data
                    ExaminationScheduleItemData itemData = null;
                    Integer selectedTimeIndex = (Integer)v.getTag();
                    MyTime selectedTimeItem = mTimeList.get(selectedTimeIndex);
                    if(selectedTimeItem != null){
                        if(selectedTimeItem.isSelected){ // Click on the highlight item
                            int selectedPosInMainList = selectedTimeItem.selectedPosInMainList;
                            itemData = mItemDataList.get(selectedPosInMainList);
                        }else{
                            // Create empty data
                            itemData = new ExaminationScheduleItemData("", mAppointmentTime.generateDateString(AppConstants.DATE_FORMAT_YYYY_MM_DD), selectedTimeItem.displayText, selectedTimeItem.displayText, 0, "", "");
                        }
                    }else{
                        // Create empty data
                        itemData = new ExaminationScheduleItemData("", mAppointmentTime.generateDateString(AppConstants.DATE_FORMAT_YYYY_MM_DD),selectedTimeItem.displayText, selectedTimeItem.displayText, 0, "", "");
                    }
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(AppConstants.CALENDAR_ID_KEY, itemData);
                    timeRangeSelectionFragment.setArguments(bundle);
                    ((HomeActivity) mContext).showFragment(timeRangeSelectionFragment);
                    break;
            }
        }
    };
    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(final View v, final MotionEvent event) {

            ExaminationScheduleItemData itemData = null;
            Integer selectedTimeIndex = (Integer)v.getTag();
            final MyTime selectedTimeItem = mTimeList.get(selectedTimeIndex);

            if(selectedTimeItem != null) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(!mIsListViewScrolling){
                                    if (selectedTimeItem.isSelected) {
                                        findAndSetSelectionForAllSameItems(selectedTimeItem.selectedPosInMainList, true);
                                    } else {
                                        v.setBackgroundColor(mContext.getResources().getColor(R.color.textview_color_grey));
                                        v.invalidate();
                                    }
                                }
                            }
                        },100);

                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        if(selectedTimeItem.isSelected){
                            findAndSetSelectionForAllSameItems(selectedTimeItem.selectedPosInMainList, false);
                        }else{
                            v.setBackgroundColor(Color.TRANSPARENT);
                            v.invalidate();
                        }
                        break;
                }
            }

            return false;
        }
    };
    private void findAndSetSelectionForAllSameItems(int posInMainList, boolean isSelected){
        if(mViewHolderMap.size() > 0){
            Iterator<ViewHolder> iterator = mViewHolderMap.values().iterator();
            while(iterator.hasNext()){
                ViewHolder viewHolder = iterator.next();
                if(viewHolder.mPosAtMainList == posInMainList){
                    if(isSelected){
                        viewHolder.mHighlight.setBackgroundColor(mContext.getResources().getColor(R.color.calendar_time_highlight_color));
                        viewHolder.mHighlight.invalidate();
                    }else{
                        viewHolder.mHighlight.setBackgroundColor(mContext.getResources().getColor(R.color.header_background_color));
                        viewHolder.mHighlight.invalidate();
                    }
                }
            }
        }
    }
    private AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            mIsListViewScrolling = (scrollState != SCROLL_STATE_IDLE);
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    };
    private class MyTime{
        private boolean isSelected;
        private int selectedPosInMainList;
        private String displayText;
    }
    private static class ViewHolder{
        private TextView mTvTime;
        private View mHighlight;
        private int mPosAtMainList = -1;
    }

}
