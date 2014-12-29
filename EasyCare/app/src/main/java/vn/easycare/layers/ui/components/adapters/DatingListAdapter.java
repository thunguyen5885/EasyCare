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

import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.components.singleton.DataSingleton;
import vn.easycare.layers.ui.fragments.DatingDetailFragment;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by ThuNguyen on 12/16/2014.
 */
public class DatingListAdapter extends BaseAdapter{
    public interface IDatingItemClickListener{
        public void onDatingDetail(ExaminationAppointmentItemData itemData);
        public void onDatingCalendarChange(ExaminationAppointmentItemData itemData);
        public void onDatingCalendarCancel(String appointmentId);
        public void onDatingCalendarAccept(String appointmentId);
    }
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private boolean mIsWaitingList = false;
    private boolean mIsClicked = false;
    private boolean mIsEndOfList = false;
    private IDatingItemClickListener mDatingItemClickListener;

    private List<ExaminationAppointmentItemData> mExaminationAppointmentItemDatas;

    public DatingListAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    public void setWaitingList(boolean isWaitingList){
        mIsWaitingList = isWaitingList;
    }
    public void setExaminationAppointmentItemDatas(List<ExaminationAppointmentItemData> data){
        mExaminationAppointmentItemDatas = data;
    }
    public void setEndOfList(boolean isEndOfList){
        mIsEndOfList = isEndOfList;
    }
    public void setDatingItemClickListener(IDatingItemClickListener datingItemClickListener){
        mDatingItemClickListener = datingItemClickListener;
    }
    @Override
    public int getCount() {

        return (mExaminationAppointmentItemDatas != null)?mExaminationAppointmentItemDatas.size(): 0;
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
            viewHolder.mPatientAvatar = (NetworkImageView) convertView.findViewById(R.id.patientAvatarThumb);
            viewHolder.mTvPatientName = (TextView) convertView.findViewById(R.id.tvPatientName);
            viewHolder.mTvDatingTime = (TextView) convertView.findViewById(R.id.tvDatingTime);
            viewHolder.mTvPatientDisease = (TextView) convertView.findViewById(R.id.tvPatientDisease);
            viewHolder.mButtonLayout = convertView.findViewById(R.id.datingListButtonLayout);
            viewHolder.mBtnCalendarChange = (TextView) convertView.findViewById(R.id.btnCalendarChange);
            viewHolder.mBtnCalendarAccept = (TextView) convertView.findViewById(R.id.btnCalendarAccept);
            viewHolder.mBtnCalendarCancel = (TextView) convertView.findViewById(R.id.btnCalendarCancel);
            viewHolder.mBottomSeparatorLayout = convertView.findViewById(R.id.bottomIndicator);
            viewHolder.mEndOfListLayout = convertView.findViewById(R.id.endOfListIndicator);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Set onClick
        viewHolder.mTvPatientName.setTag(position);
        viewHolder.mTvPatientName.setOnClickListener(mOnClickListener);
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

        if(mIsEndOfList && position == getCount() - 1){
            viewHolder.mEndOfListLayout.setVisibility(View.VISIBLE);
            viewHolder.mBottomSeparatorLayout.setVisibility(View.GONE);
        }else{
            viewHolder.mEndOfListLayout.setVisibility(View.GONE);
            viewHolder.mBottomSeparatorLayout.setVisibility(View.VISIBLE);
        }
        // Calculate the avatar size
        int screenWidth = AppFnUtils.getScreenWidth((Activity)mContext);
        int avatarSize = screenWidth / 5;
        viewHolder.mPatientAvatar.getLayoutParams().width = avatarSize;
        viewHolder.mPatientAvatar.getLayoutParams().height = avatarSize;

        // Set item data
        ExaminationAppointmentItemData itemData = mExaminationAppointmentItemDatas.get(position);
        viewHolder.mPatientAvatar.setDefaultImageResId(R.drawable.ic_no_avatar);
        viewHolder.mPatientAvatar.setImageUrl(itemData.getPatientAvatar(), DataSingleton.getInstance(mContext).getImageLoader());
        viewHolder.mTvPatientName.setText(itemData.getPatientName());
        String displayDate = AppFnUtils.convertDateFormat(AppConstants.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, AppConstants.DATE_FORMAT_DD_MM_YYYY_HH_MM, itemData.getExaminationDateTime().toString());
        viewHolder.mTvDatingTime.setText(displayDate);
        viewHolder.mTvPatientDisease.setText(itemData.getExaminationReason());
//        viewHolder.mTvPatientName.setText("Nguyen Van A");
//        viewHolder.mTvDatingTime.setText("12/12/2014, 13:13");
//        viewHolder.mTvPatientDisease.setText("Bệnh thấp khớp, đau xương nhức mỏi, khó di chuyển và rất khó chịu");

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
            int selectedPos = (Integer) v.getTag();
            ExaminationAppointmentItemData selectedItem = mExaminationAppointmentItemDatas.get(selectedPos);
            String appointmentId = selectedItem.getExaminationId();
            switch (v.getId()){
                case R.id.tvPatientName:
                    if(mDatingItemClickListener != null){
                        mDatingItemClickListener.onDatingDetail(selectedItem);
                    }
                    break;
                case R.id.btnCalendarChange:
                    if(mDatingItemClickListener != null){
                        mDatingItemClickListener.onDatingCalendarChange(selectedItem);
                    }
                    break;
                case R.id.btnCalendarCancel:
                    if(mDatingItemClickListener != null){
                        mDatingItemClickListener.onDatingCalendarCancel(appointmentId);
                    }
                    break;
                case R.id.btnCalendarAccept:
                    if(mDatingItemClickListener != null){
                        mDatingItemClickListener.onDatingCalendarAccept(appointmentId);
                    }
                    break;
            }
        }
    };
    private class ViewHolder{
        private NetworkImageView mPatientAvatar;
        private TextView mTvPatientName;
        private TextView mTvDatingTime;
        private TextView mTvPatientDisease;
        private View mButtonLayout;
        private TextView mBtnCalendarChange;
        private TextView mBtnCalendarAccept;
        private TextView mBtnCalendarCancel;
        private View mBottomSeparatorLayout;
        private View mEndOfListLayout;
    }
}
