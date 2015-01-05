package vn.easycare.layers.ui.components.adapters;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
public class SimpleTextAdapter extends BaseAdapter implements View.OnClickListener{
    public interface IOnItemClickListener{
        public void onItemClickListener(int selectedPos);
    }
    private Context mContext;
    private LayoutInflater mInflater;
    private boolean mIsClicked = false;
    private IOnItemClickListener mItemClickListener;
    private List<Object> mItemDataList;
    public SimpleTextAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }
    public void setItemDataList(List<Object> itemDataList){
        mItemDataList = itemDataList;
    }
    public void setOnItemClickListener(IOnItemClickListener itemClickListener){
        mItemClickListener = itemClickListener;
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
            convertView = mInflater.inflate(R.layout.simple_text_item_ctrl, null);
            viewHolder = new ViewHolder();
            viewHolder.mTvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.mSeparator = convertView.findViewById(R.id.vSeparator);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Set onclick
        viewHolder.mTvTitle.setTag(position);
        viewHolder.mTvTitle.setOnClickListener(this);

        // Set data
        String itemData = mItemDataList.get(position).toString();
        viewHolder.mTvTitle.setText(itemData);
        if(position == getCount() - 1){
            viewHolder.mSeparator.setVisibility(View.INVISIBLE);
        }else{
            viewHolder.mSeparator.setVisibility(View.VISIBLE);
        }
        // Apply font
        AppFnUtils.applyFontForTextViewChild(convertView);
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
            case R.id.tvTitle:
                Integer selectedPos = (Integer)v.getTag();
                if(mItemClickListener != null){
                    mItemClickListener.onItemClickListener(selectedPos);
                }
                break;
        }
    }

    private static class ViewHolder{
        private TextView mTvTitle;
        private View mSeparator;
    }

}
