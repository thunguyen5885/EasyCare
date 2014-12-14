package vn.easycare.layers.ui.components.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import vn.easycare.R;
import vn.easycare.layers.ui.components.views.CustomImageViewCircularShape;
import vn.easycare.layers.ui.components.views.RatingLayout;
import vn.easycare.layers.ui.components.views.foreground.ForegroundRelativeLayout;

/**
 * Created by Thu Nguyen on 12/13/2014.
 */
public class CommentAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;

    public CommentAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(context);
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
        viewHolder.mPatientAvatar.setDefaultImageResId(R.drawable.ic_no_avatar);
        viewHolder.mTvPatientName.setText("Nguyen Van A".toUpperCase());
        viewHolder.mTvCommentDate.setText("10/12/2014");
        viewHolder.mTvCommentHour.setText("12:01");
        viewHolder.mTvCommentComment.setText("EasyCare là 1 sự trải nghiệm tuyệt vời. Tôi chỉ có vài phút là đặt được lịch hẹn với bác sĩ. Tôi sẽ giới thiệu EasyCare đến bạn bè và người thân của tôi.");
        viewHolder.mCommonIdeaRatingLayout.setSelection(4);
        viewHolder.mWaitingTimeRatingLayout.setSelection(2);
        viewHolder.mAssetRatingLayout.setSelection(3);
        if(position % 2 == 0){
            viewHolder.mCommentDisplay.setText(R.string.comment_display);
            viewHolder.mCommentDisplay.setBackgroundResource(R.drawable.layout_disable_button_with_corner);
            viewHolder.mCommentDiplayLayout.setEnabled(false);
        }else{
            viewHolder.mCommentDisplay.setText(R.string.comment_no_display);
            viewHolder.mCommentDisplay.setBackgroundResource(R.drawable.layout_hightlight_button_with_corner);
            viewHolder.mCommentDiplayLayout.setEnabled(true);
        }
        return convertView;
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
