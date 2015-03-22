package vn.easycare.layers.ui.components.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.data.DoctorManagementItemData;
import vn.easycare.layers.ui.components.data.PatientManagementItemData;
import vn.easycare.layers.ui.components.singleton.DataSingleton;
import vn.easycare.layers.ui.fragments.AppointmentListForAPatientFragment;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by ThuNguyen on 12/16/2014.
 */
public class DoctorListAdapter extends BaseAdapter{
    public interface IDoctorListClickListener {
        public void goToAppointmentPage(String doctorId);
        public void goToCalendarPage(String doctorId);
    }
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private boolean mIsClicked = false;
    private boolean mIsEndOfList = false;
    private IDoctorListClickListener mDoctorListClickListener;
    private List<DoctorManagementItemData> mItemDataList;

    public DoctorListAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    public void setItemDataList(List<DoctorManagementItemData> itemDataList){
        mItemDataList = itemDataList;
    }
    public void setDoctorListClickListener(IDoctorListClickListener doctorListClickListener){
        mDoctorListClickListener = doctorListClickListener;
    }
    public void setIsEndOfList(boolean isEndOfList){
        mIsEndOfList = isEndOfList;
    }
    @Override
    public int getCount() {
        return (mItemDataList != null) ? mItemDataList.size() : 0;
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
            convertView = mLayoutInflater.inflate(R.layout.doctor_item_ctrl, null);
            viewHolder = new ViewHolder();
            viewHolder.mDoctorAvatar = (NetworkImageView) convertView.findViewById(R.id.doctorAvatarThumb);
            viewHolder.mDoctorName = (TextView) convertView.findViewById(R.id.tvDoctorName);
            viewHolder.mDoctorPhone = (TextView) convertView.findViewById(R.id.tvDoctorPhone);
            viewHolder.mDoctorEmail = (TextView) convertView.findViewById(R.id.tvDoctorEmail);
            viewHolder.mTvAppointment = (TextView) convertView.findViewById(R.id.tvAppointmentForDoctor);
            viewHolder.mTvCalendar = (TextView) convertView.findViewById(R.id.tvCalendarForDoctor);
            viewHolder.mBottomIndicator = convertView.findViewById(R.id.bottomIndicator);
            viewHolder.mEndOfListIndicator = convertView.findViewById(R.id.endOfListIndicator);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(position == getCount() - 1 && mIsEndOfList){
            viewHolder.mBottomIndicator.setVisibility(View.GONE);
            viewHolder.mEndOfListIndicator.setVisibility(View.VISIBLE);
        }else{
            viewHolder.mBottomIndicator.setVisibility(View.VISIBLE);
            viewHolder.mEndOfListIndicator.setVisibility(View.GONE);
        }
        // Set on click
        viewHolder.mTvAppointment.setTag(position);
        viewHolder.mTvAppointment.setOnClickListener(mOnClickListener);
        viewHolder.mTvCalendar.setTag(position);
        viewHolder.mTvCalendar.setOnClickListener(mOnClickListener);

        // Calculate the avatar size
        int screenWidth = AppFnUtils.getScreenWidth((Activity)mContext);
        int avatarSize = screenWidth / 5;
        viewHolder.mDoctorAvatar.getLayoutParams().width = avatarSize;
        viewHolder.mDoctorAvatar.getLayoutParams().height = avatarSize;

        // Set fake data
        DoctorManagementItemData itemData = mItemDataList.get(position);
        viewHolder.mDoctorAvatar.setDefaultImageResId(R.drawable.ic_no_avatar);
        viewHolder.mDoctorAvatar.setImageUrl(itemData.getDoctorAvatar(), DataSingleton.getInstance(mContext).getImageLoader());
        viewHolder.mDoctorName.setText(itemData.getDoctorFullName());
        if(itemData.getDoctorEmail() != null && itemData.getDoctorEmail().length() > 0) {
            viewHolder.mDoctorEmail.setText(itemData.getDoctorEmail());
            viewHolder.mDoctorEmail.setTextColor(mContext.getResources().getColor(R.color.textview_color_default));
        }else{
            viewHolder.mDoctorEmail.setText(R.string.nothing_data);
            viewHolder.mDoctorEmail.setTextColor(mContext.getResources().getColor(R.color.textview_color_grey));
        }
        if(itemData.getDoctorPhone() != null && itemData.getDoctorPhone().length() > 0) {
            viewHolder.mDoctorPhone.setText(itemData.getDoctorPhone());
            viewHolder.mDoctorPhone.setTextColor(mContext.getResources().getColor(R.color.textview_color_default));
        }else{
            viewHolder.mDoctorPhone.setText(R.string.nothing_data);
            viewHolder.mDoctorPhone.setTextColor(mContext.getResources().getColor(R.color.textview_color_grey));
        }

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
                case R.id.tvAppointmentForDoctor:
                    break;
                case R.id.tvCalendarForDoctor:
                    break;
            }
        }
    };
    private static class ViewHolder{
        private NetworkImageView mDoctorAvatar;
        private TextView mDoctorName;
        private TextView mDoctorPhone;
        private TextView mDoctorEmail;
        private TextView mTvAppointment;
        private TextView mTvCalendar;
        private View mBottomIndicator;
        private View mEndOfListIndicator;
    }
}
