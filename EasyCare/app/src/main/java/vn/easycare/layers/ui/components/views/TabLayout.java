package vn.easycare.layers.ui.components.views;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vn.easycare.R;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by ThuNguyen on 12/16/2014.
 */
public class TabLayout extends LinearLayout{
    public interface IOnTabItemClickListener{
        public void onTabItemClickListener(int position);
    }
    private LayoutInflater mInflater;
    private View mSelectedView;
    private int mSelectedPos;
    private IOnTabItemClickListener mOnTabItemClickListner;
    public TabLayout(Context context) {
        super(context);
        init(context);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    public void setOnTabItemClickListner(IOnTabItemClickListener onTabItemClickListner){
        mOnTabItemClickListner = onTabItemClickListner;
    }
    private void init(Context context){
        mSelectedPos = -1;
        mInflater = LayoutInflater.from(context);
    }

    public void createChild(List<String> childList){
        if(childList != null && childList.size() > 0){
            // Calculate the child width
            int screenWidth = AppFnUtils.getScreenWidth((Activity) getContext());
            int childWidth = screenWidth / childList.size();
            for(int index = 0; index < childList.size(); index++){
                View child = mInflater.inflate(R.layout.tab_item_ctrl, null);
                LayoutParams params = new LayoutParams(childWidth, ViewGroup.LayoutParams.MATCH_PARENT);
                if(index != 0){
                    params.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
                }
                child.setLayoutParams(params);
                child.setTag(index);
                child.setClickable(true);
                child.setOnClickListener(mOnClickListener);
                updateChildTitle(child, childList.get(index));
                updateChildView(child, false);
                addView(child);
            }
        }
    }
    public void setSelection(int selection){
        if(selection > -1 && selection < getChildCount() && selection != mSelectedPos){
            // Reset selected item and set for current item
            updateChildView(mSelectedView, false);
            View currentView = getChildAt(selection);
            updateChildView(currentView, true);

            // Keep object
            mSelectedPos = selection;
            mSelectedView = currentView;
        }
    }
    private void updateChildView(View childView, boolean isSelected){
        if(childView != null) {
            View defaultTab = childView.findViewById(R.id.vDefaultTab);
            View selectedTab = childView.findViewById(R.id.vSelectedTab);
            TextView title = (TextView) childView.findViewById(R.id.tvTabTitle);
            if (isSelected){
                defaultTab.setVisibility(View.GONE);
                selectedTab.setVisibility(View.VISIBLE);
                title.setTextColor(getResources().getColor(R.color.textview_color_highlight_tab));
            }else{
                defaultTab.setVisibility(View.VISIBLE);
                selectedTab.setVisibility(View.GONE);
                title.setTextColor(getResources().getColor(R.color.textview_color_default));
            }
        }
    }
    private void updateChildTitle(View childView, String title){
        if(childView != null) {
            TextView tvTitle = (TextView) childView.findViewById(R.id.tvTabTitle);
            tvTitle.setText(title);
        }
    }
    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int pos = (Integer) v.getTag();
            if(pos != mSelectedPos && mOnTabItemClickListner != null){
                mOnTabItemClickListner.onTabItemClickListener(pos);
            }
            setSelection(pos);
        }
    };
}
