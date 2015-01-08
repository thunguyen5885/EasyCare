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
import vn.easycare.layers.ui.components.data.PatientManagementItemData;
import vn.easycare.layers.ui.components.singleton.DataSingleton;
import vn.easycare.layers.ui.fragments.AppointmentListForAPatientFragment;
import vn.easycare.layers.ui.fragments.PatientDetailFragment;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by ThuNguyen on 12/16/2014.
 */
public class PatientListAdapter extends BaseAdapter{
    public interface IPatientListClickListener{
        public void onBlockClicked(String patientId);
        public void onUnblockClicked(String patientId);
    }
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private boolean mIsBlackList = false;
    private boolean mIsClicked = false;
    private boolean mIsEndOfList = false;
    private IPatientListClickListener mPatientListClickListener;
    private List<PatientManagementItemData> mItemDataList;

    public PatientListAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    public void setBlackList(boolean isBlackList){
        mIsBlackList = isBlackList;
    }
    public void setItemDataList(List<PatientManagementItemData> itemDataList){
        mItemDataList = itemDataList;
    }
    public void setPatientListClickListener(IPatientListClickListener patientListClickListener){
        mPatientListClickListener = patientListClickListener;
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
            convertView = mLayoutInflater.inflate(R.layout.patient_item_ctrl, null);
            viewHolder = new ViewHolder();
            viewHolder.mPatientItemLayout = convertView.findViewById(R.id.patientItemLayout);
            viewHolder.mPatientAvatar = (NetworkImageView) convertView.findViewById(R.id.patientAvatarThumb);
            viewHolder.mPatientName = (TextView) convertView.findViewById(R.id.tvPatientName);
            viewHolder.mPatientPhone = (TextView) convertView.findViewById(R.id.tvPatientPhone);
            viewHolder.mPatientEmail = (TextView) convertView.findViewById(R.id.tvPatientEmail);
            viewHolder.mBtnBlock = (TextView) convertView.findViewById(R.id.btnBlock);
            viewHolder.mBtnAppointment = (TextView) convertView.findViewById(R.id.btnAppointment);
            viewHolder.mBottomIndicator = convertView.findViewById(R.id.bottomIndicator);
            viewHolder.mEndOfListIndicator = convertView.findViewById(R.id.endOfListIndicator);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(mIsBlackList){
            //viewHolder.mBtnAppointment.setVisibility(View.GONE);
            viewHolder.mBtnBlock.setText(R.string.patient_list_unblock);
        }else{
            viewHolder.mBtnAppointment.setVisibility(View.VISIBLE);
            viewHolder.mBtnBlock.setText(R.string.patient_list_block);
        }
        if(position == getCount() - 1 && mIsEndOfList){
            viewHolder.mBottomIndicator.setVisibility(View.GONE);
            viewHolder.mEndOfListIndicator.setVisibility(View.VISIBLE);
        }else{
            viewHolder.mBottomIndicator.setVisibility(View.VISIBLE);
            viewHolder.mEndOfListIndicator.setVisibility(View.GONE);
        }
        // Set on click
        viewHolder.mPatientItemLayout.setTag(position);
        viewHolder.mPatientItemLayout.setOnClickListener(mOnClickListener);
        viewHolder.mBtnBlock.setTag(position);
        viewHolder.mBtnBlock.setOnClickListener(mOnClickListener);
        viewHolder.mBtnAppointment.setTag(position);
        viewHolder.mBtnAppointment.setOnClickListener(mOnClickListener);

        // Calculate the avatar size
        int screenWidth = AppFnUtils.getScreenWidth((Activity)mContext);
        int avatarSize = screenWidth / 5;
        viewHolder.mPatientAvatar.getLayoutParams().width = avatarSize;
        viewHolder.mPatientAvatar.getLayoutParams().height = avatarSize;

        // Set fake data
        PatientManagementItemData itemData = mItemDataList.get(position);
        viewHolder.mPatientAvatar.setDefaultImageResId(R.drawable.ic_no_avatar);
        viewHolder.mPatientAvatar.setImageUrl(itemData.getPatientAvatar(), DataSingleton.getInstance(mContext).getImageLoader());
        viewHolder.mPatientName.setText(itemData.getPatientName());
        if(itemData.getPatientEmailAddress() != null && itemData.getPatientEmailAddress().length() > 0) {
            viewHolder.mPatientEmail.setText(itemData.getPatientEmailAddress());
            viewHolder.mPatientEmail.setTextColor(mContext.getResources().getColor(R.color.textview_color_default));
        }else{
            viewHolder.mPatientEmail.setText(R.string.nothing_data);
            viewHolder.mPatientEmail.setTextColor(mContext.getResources().getColor(R.color.textview_color_grey));
        }
        if(itemData.getPatientPhoneNumber() != null && itemData.getPatientPhoneNumber().length() > 0) {
            viewHolder.mPatientPhone.setText(itemData.getPatientPhoneNumber());
            viewHolder.mPatientPhone.setTextColor(mContext.getResources().getColor(R.color.textview_color_default));
        }else{
            viewHolder.mPatientPhone.setText(R.string.nothing_data);
            viewHolder.mPatientPhone.setTextColor(mContext.getResources().getColor(R.color.textview_color_grey));
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
                case R.id.btnBlock:
                    //Toast.makeText(mContext, "Block clicked", Toast.LENGTH_SHORT).show();
                    int selectedPos = (Integer) v.getTag();
                    PatientManagementItemData selectedItem = mItemDataList.get(selectedPos);
                    if(mPatientListClickListener != null){
                        if(mIsBlackList){
                            mPatientListClickListener.onUnblockClicked(selectedItem.getPatientId());
                        }else{
                            mPatientListClickListener.onBlockClicked(selectedItem.getPatientId());
                        }
                    }
                    break;
                case R.id.btnAppointment:
                    selectedPos = (Integer) v.getTag();
                    selectedItem = mItemDataList.get(selectedPos);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(AppConstants.PATIENT_DETAIL_KEY, selectedItem);

                    // Go to patient_detail screen
                    AppointmentListForAPatientFragment appointmentListForAPatientFragment = new AppointmentListForAPatientFragment();
                    appointmentListForAPatientFragment.setArguments(bundle);
                    ((HomeActivity) mContext).showFragment(appointmentListForAPatientFragment);
                    break;
                case R.id.patientItemLayout:
                    selectedPos = (Integer) v.getTag();
                    selectedItem = mItemDataList.get(selectedPos);

                    bundle = new Bundle();
                    bundle.putSerializable(AppConstants.PATIENT_DETAIL_KEY, selectedItem);

                    // Go to patient_detail screen
                    PatientDetailFragment patientDetailFragment = new PatientDetailFragment();
                    patientDetailFragment.setArguments(bundle);
                    ((HomeActivity) mContext).showFragment(patientDetailFragment);
                    break;
            }
        }
    };
    private static class ViewHolder{
        private View mPatientItemLayout;
        private NetworkImageView mPatientAvatar;
        private TextView mPatientName;
        private TextView mPatientPhone;
        private TextView mPatientEmail;
        private TextView mBtnBlock;
        private TextView mBtnAppointment;
        private View mBottomIndicator;
        private View mEndOfListIndicator;
    }
}
