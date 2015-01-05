package vn.easycare.layers.ui.components.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.data.ExaminationScheduleItemData;
import vn.easycare.layers.ui.fragments.TimeRangeSelectionFragment;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by ThuNguyen on 12/21/2014.
 */
public class DateTimeAdapter extends BaseAdapter{
    private static final int START_TIME = 7;
    private static final int END_TIME = 21;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    // For data, object
    private List<MyTime> mTimeList = new ArrayList<MyTime>();
    private boolean mIsClicked = false;
    private List<ExaminationScheduleItemData> mItemDataList;
    public DateTimeAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    public void setItemDataList(List<ExaminationScheduleItemData> itemDataList){
        mItemDataList = itemDataList;
        createDisplayList();
    }

    private void createDisplayList(){
        for(int index = START_TIME; index < END_TIME; index++){

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
                                MyTime myTime = new MyTime();
                                myTime.displayText = (hourIndex > 9 ? (hourIndex + "") : ("0" + hourIndex)) + ":" + ((timeSlotIndex > 9) ? (timeSlotIndex + "") : ("0" + timeSlotIndex));
                                myTime.isSelected = false;
                                myTime.selectedPosInMainList = -1;

                                boolean isInRange = hourIndex > foundItem.getHourFrom() || (hourIndex == foundItem.getHourFrom() && timeSlot <= foundItem.getMinuteFrom());
                                if (isInRange) {
                                    myTime.isSelected = true;
                                    myTime.selectedPosInMainList = selectedPos;
                                }
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
            viewHolder.mTvTime = (TextView) convertView.findViewById(R.id.tvDateTime);
            viewHolder.mHightlight = convertView.findViewById(R.id.vDateHightlight);
            viewHolder.mAdd = convertView.findViewById(R.id.ivDateAdd);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Get hight light width by a half of screen width
//        int screenWidth = AppFnUtils.getScreenWidth((Activity) mContext);
//        viewHolder.mHightlight.getLayoutParams().width = screenWidth / 2;

        // Set onclick
        viewHolder.mAdd.setTag(position);
        viewHolder.mAdd.setOnClickListener(mOnClickListener);

        viewHolder.mHightlight.setTag(position);
        viewHolder.mHightlight.setOnClickListener(mOnClickListener);
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
            viewHolder.mHightlight.setBackgroundColor(mContext.getResources().getColor(R.color.header_background_color));
        }else{
            viewHolder.mHightlight.setBackgroundColor(Color.TRANSPARENT);
        }
        viewHolder.mHightlight.invalidate();

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
                case R.id.ivDateAdd:
                case R.id.vDateHightlight:
                    // Go to time range screen
                    TimeRangeSelectionFragment timeRangeSelectionFragment = new TimeRangeSelectionFragment();
                    ((HomeActivity)mContext).showFragment(timeRangeSelectionFragment);
                    break;
            }
        }
    };
    private class MyTime{
        private boolean isSelected;
        private int selectedPosInMainList;
        private String displayText;
    }
    private static class ViewHolder{
        private TextView mTvTime;
        private View mHightlight;
        private View mAdd;
    }

}
