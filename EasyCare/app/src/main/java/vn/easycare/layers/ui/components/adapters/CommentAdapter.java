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

import org.w3c.dom.Text;

import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.components.data.CommentAndAssessmentItemData;
import vn.easycare.layers.ui.components.singleton.DataSingleton;
import vn.easycare.layers.ui.components.views.CustomImageViewCircularShape;
import vn.easycare.layers.ui.components.views.RatingLayout;
import vn.easycare.layers.ui.components.views.foreground.ForegroundRelativeLayout;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by Thu Nguyen on 12/13/2014.
 */
public class CommentAdapter extends BaseAdapter implements View.OnClickListener{
    private Context mContext;
    private LayoutInflater mInflater;
    private boolean  mIsClicked = false;
    private List<CommentAndAssessmentItemData> mItemDataList;
    public CommentAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }
    public void setItemDataList(List<CommentAndAssessmentItemData> itemDataList){
        mItemDataList = itemDataList;
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
            convertView = mInflater.inflate(R.layout.comment_item_ctrl, null);
            viewHolder = new ViewHolder();
            viewHolder.mPatientAvatar = (CustomImageViewCircularShape) convertView.findViewById(R.id.commentPatientAvatar);
            viewHolder.mTvPatientName = (TextView) convertView.findViewById(R.id.tvCommentPatientName);
            viewHolder.mTvCommentDate = (TextView) convertView.findViewById(R.id.tvCommentDate);
            viewHolder.mTvCommentHour = (TextView) convertView.findViewById(R.id.tvCommentHour);
            viewHolder.mTvCommentComment = (TextView) convertView.findViewById(R.id.tvCommentComment);
            viewHolder.mCommonIdeaRatingLayout = (RatingLayout) convertView.findViewById(R.id.commonIdeaRatingLayout);
            viewHolder.mAssetRatingLayout = (RatingLayout) convertView.findViewById(R.id.assetRatingLayout);
            viewHolder.mWaitingTimeRatingLayout = (RatingLayout) convertView.findViewById(R.id.waitingTimeRatingLayout);
            viewHolder.mCommentDisplay = (TextView) convertView.findViewById(R.id.tvCommentDisplay);
            viewHolder.mCommentDiplayLayout = (ForegroundRelativeLayout) convertView.findViewById(R.id.commentDisplayLayout);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Set onclick
        viewHolder.mCommentDiplayLayout.setOnClickListener(this);

        // Set data
        CommentAndAssessmentItemData itemData = mItemDataList.get(position);
        viewHolder.mPatientAvatar.setDefaultImageResId(R.drawable.ic_no_avatar);
        viewHolder.mPatientAvatar.setImageUrl(itemData.getPatientAvatar(), DataSingleton.getInstance(mContext).getImageLoader());
        viewHolder.mTvPatientName.setText(itemData.getPatientName().toUpperCase());
        viewHolder.mTvCommentComment.setText(itemData.getCommentContent());
        viewHolder.mCommonIdeaRatingLayout.setSelection(itemData.getGeneralPoint() - 1);
        viewHolder.mWaitingTimeRatingLayout.setSelection(itemData.getWaitingTimePoint() - 1);
        viewHolder.mAssetRatingLayout.setSelection(itemData.getFacilityPoint() - 1);
//        viewHolder.mTvPatientName.setText("Nguyen Van A".toUpperCase());
//        viewHolder.mTvCommentDate.setText("10/12/2014");
//        viewHolder.mTvCommentHour.setText("12:01");
//        viewHolder.mTvCommentComment.setText("EasyCare là 1 sự trải nghiệm tuyệt vời. Tôi chỉ có vài phút là đặt được lịch hẹn với bác sĩ. Tôi sẽ giới thiệu EasyCare đến bạn bè và người thân của tôi.");
//        viewHolder.mCommonIdeaRatingLayout.setSelection(4);
//        viewHolder.mWaitingTimeRatingLayout.setSelection(2);
//        viewHolder.mAssetRatingLayout.setSelection(3);

        // Apply font
        AppFnUtils.applyFontForTextViewChild(convertView, null);
        return convertView;
    }

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
            case R.id.commentDisplayLayout:
                break;
        }
    }

    private static class ViewHolder{
        private CustomImageViewCircularShape mPatientAvatar;
        private TextView mTvPatientName;
        private TextView mTvCommentDate;
        private TextView mTvCommentHour;
        private TextView mTvCommentComment;
        private RatingLayout mCommonIdeaRatingLayout;
        private RatingLayout mAssetRatingLayout;
        private RatingLayout mWaitingTimeRatingLayout;
        private TextView mCommentDisplay;
        private ForegroundRelativeLayout mCommentDiplayLayout;
    }

}
