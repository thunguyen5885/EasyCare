package vn.easycare.layers.ui.components.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.components.data.MessageManagementItemData;
import vn.easycare.layers.ui.components.data.PatientManagementItemData;
import vn.easycare.layers.ui.components.singleton.DataSingleton;
import vn.easycare.utils.AppFnUtils;


/**
 * Created by phannguyen on 3/22/15.
 */
public class MessagesListAdapter extends BaseAdapter {
    public interface IMessageListClickListener {
        public void goToMessageDetail(String MessageId);
        public void deleteMessage(String MessageId);
    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private boolean mIsClicked = false;
    private boolean mIsEndOfList = false;
    private IMessageListClickListener mMessageClickListener;
    private List<MessageManagementItemData> mItemDataList;

    public MessagesListAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    public void setItemDataList(List<MessageManagementItemData> itemDataList){
        mItemDataList = itemDataList;
    }
    public void setDoctorListClickListener(IMessageListClickListener messageClickListener){
        mMessageClickListener = messageClickListener;
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.message_item_ctrl, null);
            viewHolder = new ViewHolder();
            viewHolder.mSenderAvatar = (NetworkImageView) convertView.findViewById(R.id.senderAvatarThumb);
            viewHolder.mSenderName = (TextView) convertView.findViewById(R.id.tvSenderName);
            viewHolder.mSentTime = (TextView) convertView.findViewById(R.id.tvSentTime);
            viewHolder.mMessageContent = (TextView) convertView.findViewById(R.id.tvContentMessage);
            viewHolder.mExaminateToDoctorName = (TextView) convertView.findViewById(R.id.tvPlaceExaminationTo);
            viewHolder.mDepartmentName = (TextView) convertView.findViewById(R.id.tvDepartmentName);
            viewHolder.mTvMessageDetail = (TextView) convertView.findViewById(R.id.tvDetailMessage);
            viewHolder.mTvMessageDelete = (TextView) convertView.findViewById(R.id.tvDeleteMessage);
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
        viewHolder.mTvMessageDetail.setTag(position);
        viewHolder.mTvMessageDetail.setOnClickListener(mOnClickListener);
        viewHolder.mTvMessageDelete.setTag(position);
        viewHolder.mTvMessageDelete.setOnClickListener(mOnClickListener);

        // Calculate the avatar size
        int screenWidth = AppFnUtils.getScreenWidth((Activity) mContext);
        int avatarSize = screenWidth / 5;
        viewHolder.mSenderAvatar.getLayoutParams().width = avatarSize;
        viewHolder.mSenderAvatar.getLayoutParams().height = avatarSize;

        // Set fake data
        MessageManagementItemData itemData = mItemDataList.get(position);
        viewHolder.mSenderAvatar.setDefaultImageResId(R.drawable.ic_no_avatar);
        viewHolder.mSenderAvatar.setImageUrl(itemData.getSenderAvatar(), DataSingleton.getInstance(mContext).getImageLoader());
        viewHolder.mSenderName.setText(itemData.getSenderName());
        viewHolder.mSentTime.setText(itemData.getSentTime());
        viewHolder.mMessageContent.setText(itemData.getMessageContent());
        viewHolder.mExaminateToDoctorName.setText(itemData.getPlaceExaminationToDoctorName());
        viewHolder.mDepartmentName.setText(itemData.getDepartmentName());

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
                case R.id.tvDetailMessage:
                    break;
                case R.id.tvDeleteMessage:
                    break;
            }
        }
    };

    private static class ViewHolder{
        private NetworkImageView mSenderAvatar;
        private TextView mSenderName;
        private TextView mSentTime;
        private TextView mMessageContent;
        private TextView mExaminateToDoctorName;
        private TextView mDepartmentName;
        private TextView mTvMessageDetail;
        private TextView mTvMessageDelete;
        private View mBottomIndicator;
        private View mEndOfListIndicator;
    }
}
