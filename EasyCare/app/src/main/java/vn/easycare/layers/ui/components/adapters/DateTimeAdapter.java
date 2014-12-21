package vn.easycare.layers.ui.components.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
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
    private List<String> mTimeList = new ArrayList<String>();
    private boolean mIsClicked = false;
    public DateTimeAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);

        // Create data
        for(int index = START_TIME; index < END_TIME; index++){
            String time = (index < 10) ? ("0" + index) : (index + "");
            mTimeList.add(time + ":00");
            mTimeList.add(time + ":15");
            mTimeList.add(time + ":30");
            mTimeList.add(time + ":45");
        }
        mTimeList.add("21:00");

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
            convertView = mLayoutInflater.inflate(R.layout.date_time_ctrl, null);
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

        // Set data
        String timeAtPos = mTimeList.get(position);
        if(timeAtPos.contains("00")){
            viewHolder.mTvTime.setTextColor(mContext.getResources().getColor(R.color.textview_color_default));
        }else {
            viewHolder.mTvTime.setTextColor(mContext.getResources().getColor(R.color.textview_color_grey));
        }
        viewHolder.mTvTime.setText(timeAtPos);
        // Random to set background
        Random random = new Random();
        int rand = random.nextInt(10);
        if(rand % 2 != 0){
            viewHolder.mHightlight.setBackgroundColor(mContext.getResources().getColor(R.color.header_background_color));
        }else{
            viewHolder.mHightlight.setBackgroundColor(Color.TRANSPARENT);
        }
        viewHolder.mHightlight.invalidate();
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
                    // Go to time range screen
                    TimeRangeSelectionFragment timeRangeSelectionFragment = new TimeRangeSelectionFragment();
                    ((HomeActivity)mContext).showFragment(timeRangeSelectionFragment);
                    break;
            }
        }
    };
    private static class ViewHolder{
        private TextView mTvTime;
        private View mHightlight;
        private View mAdd;
    }

}
