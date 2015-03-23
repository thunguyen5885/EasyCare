package vn.easycare.layers.ui.components.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

import vn.easycare.R;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by Thu Nguyen on 3/22/2015.
 */
public class CalendarGridAdapter extends BaseAdapter{
    private static final int ITEMS_PER_ROW = 5;
    private List<Object> mDataList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int mItemSize;
    private boolean mIsClicked = false;
    public CalendarGridAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);

        // Calculate the item size to inflate
        int screenWidth = AppFnUtils.getScreenWidth((Activity)mContext);
        int padding = (int)(2*mContext.getResources().getDimension(R.dimen.common_padding));
        mItemSize = (screenWidth - padding) / ITEMS_PER_ROW - (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, mContext.getResources().getDisplayMetrics());
    }
    @Override
    public int getCount() {
        return mDataList.size();
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
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.calendar_grid_item_ctrl, null);
            viewHolder.mCalendarGridItemLayout = convertView.findViewById(R.id.calendarGridItemLayout);
            viewHolder.mCalendarSequenceLayout = convertView.findViewById(R.id.flCalendarSequence);
            viewHolder.mTvCalendarSequence = (TextView) convertView.findViewById(R.id.tvCalendarSequence);
            viewHolder.mTvCalendarTime = (TextView) convertView.findViewById(R.id.tvCalendarTime);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Set onclick
        viewHolder.mCalendarGridItemLayout.setOnClickListener(mOnClickListener);
        // Force the size for item
        viewHolder.mCalendarGridItemLayout.getLayoutParams().width = mItemSize;
        viewHolder.mCalendarGridItemLayout.getLayoutParams().height = mItemSize;
        viewHolder.mCalendarSequenceLayout.getLayoutParams().width = mItemSize / 3;
        viewHolder.mCalendarSequenceLayout.getLayoutParams().height = mItemSize / 3;
        // Set text value for calendar sequence
        viewHolder.mTvCalendarSequence.setText(String.valueOf(position + 1));
        viewHolder.mTvCalendarTime.setText("08:00");

        // Random value to set background and color
        int rand = new Random().nextInt(4);
        if(rand == 0){ // Empty
            viewHolder.mCalendarGridItemLayout.setBackgroundResource(R.color.grid_item_empty_color);
            viewHolder.mTvCalendarTime.setTextColor(mContext.getResources().getColor(R.color.white));
        }else if(rand == 1){ // Waiting
            viewHolder.mCalendarGridItemLayout.setBackgroundResource(R.color.grid_item_waiting_confirmed_color);
            viewHolder.mTvCalendarTime.setTextColor(mContext.getResources().getColor(R.color.textview_color_default));
        }else if(rand == 2){ // Confirmed
            viewHolder.mCalendarGridItemLayout.setBackgroundResource(R.color.grid_item_confirmed_color);
            viewHolder.mTvCalendarTime.setTextColor(mContext.getResources().getColor(R.color.white));
        }else{
            viewHolder.mCalendarGridItemLayout.setBackgroundResource(R.color.grid_item_treated_color);
            viewHolder.mTvCalendarTime.setTextColor(mContext.getResources().getColor(R.color.textview_color_grey));
        }
        return convertView;
    }
    public void setDataList(List<Object> dataList){
        mDataList = dataList;
    }
    private static class ViewHolder{
        private View mCalendarGridItemLayout;
        private View mCalendarSequenceLayout;
        private TextView mTvCalendarSequence;
        private TextView mTvCalendarTime;
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
            Toast.makeText(mContext, "Grid item clicked", Toast.LENGTH_LONG).show();
        }
    };
}
