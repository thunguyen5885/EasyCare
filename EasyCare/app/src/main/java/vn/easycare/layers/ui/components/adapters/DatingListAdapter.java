package vn.easycare.layers.ui.components.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.fragments.DatingDetailFragment;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by ThuNguyen on 12/16/2014.
 */
public class DatingListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private boolean mIsWaitingList = false;
    private boolean mIsClicked = false;
    public DatingListAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    public void setWaitingList(boolean isWaitingList){
        mIsWaitingList = isWaitingList;
    }
    @Override
    public int getCount() {
        return 10;
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
            convertView = mLayoutInflater.inflate(R.layout.dating_item_ctrl, null);
            viewHolder = new ViewHolder();
            viewHolder.mPatientAvatar = (NetworkImageView) convertView.findViewById(R.id.patientAvatar);
            viewHolder.mTvPatientName = (TextView) convertView.findViewById(R.id.tvPatientName);
            viewHolder.mTvDatingTime = (TextView) convertView.findViewById(R.id.tvDatingTime);
            viewHolder.mTvPatientDisease = (TextView) convertView.findViewById(R.id.tvPatientDisease);
            viewHolder.mButtonLayout = convertView.findViewById(R.id.datingListButtonLayout);
            viewHolder.mBtnCalendarChange = (Button) convertView.findViewById(R.id.btnCalendarChange);
            viewHolder.mBtnCalendarAccept = (Button) convertView.findViewById(R.id.btnCalendarAccept);
            viewHolder.mBtnCalendarCancel = (Button) convertView.findViewById(R.id.btnCalendarCancel);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(mIsWaitingList){
            viewHolder.mButtonLayout.setVisibility(View.GONE);
        }else{
            viewHolder.mButtonLayout.setVisibility(View.VISIBLE);
        }
        // Set onClick
//        viewHolder.mTvPatientName.setTag(position);
//        viewHolder.mTvPatientName.setOnClickListener(mOnClickListener);
        viewHolder.mBtnCalendarChange.setTag(position);
        viewHolder.mBtnCalendarChange.setOnClickListener(mOnClickListener);
        viewHolder.mBtnCalendarAccept.setTag(position);
        viewHolder.mBtnCalendarAccept.setOnClickListener(mOnClickListener);
        viewHolder.mBtnCalendarCancel.setTag(position);
        viewHolder.mBtnCalendarCancel.setOnClickListener(mOnClickListener);
        if(mIsWaitingList){
            viewHolder.mButtonLayout.setVisibility(View.VISIBLE);
        }else{
            viewHolder.mButtonLayout.setVisibility(View.GONE);
        }
        // Calculate the avatar size
        int screenWidth = AppFnUtils.getScreenWidth((Activity)mContext);
        int avatarSize = screenWidth / 5;
        viewHolder.mPatientAvatar.getLayoutParams().width = avatarSize;
        viewHolder.mPatientAvatar.getLayoutParams().height = avatarSize;

        // Set fake data
        viewHolder.mPatientAvatar.setDefaultImageResId(R.drawable.ic_no_avatar);
        viewHolder.mTvPatientName.setText("Nguyen Van A");
        viewHolder.mTvDatingTime.setText("12/12/2014, 13:13");
        viewHolder.mTvPatientDisease.setText("Bệnh thấp khớp, đau xương nhức mỏi, khó di chuyển và rất khó chịu");
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
                case R.id.tvPatientName:
                    // Go to dating detail screen
                    DatingDetailFragment datingDetailFragment = new DatingDetailFragment();
                    ((HomeActivity) mContext).showFragment(datingDetailFragment);
                    break;
                case R.id.btnCalendarChange:
                    break;
                case R.id.btnCalendarCancel:
                    break;
                case R.id.btnCalendarAccept:
                    break;
            }
        }
    };
    private static class ViewHolder{
        private NetworkImageView mPatientAvatar;
        private TextView mTvPatientName;
        private TextView mTvDatingTime;
        private TextView mTvPatientDisease;
        private View mButtonLayout;
        private Button mBtnCalendarChange;
        private Button mBtnCalendarAccept;
        private Button mBtnCalendarCancel;
    }
}
