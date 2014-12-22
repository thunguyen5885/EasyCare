package vn.easycare.layers.ui.components;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import vn.easycare.R;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by ThuNguyen on 12/8/2014.
 */
public class CommonHeader implements View.OnClickListener{
    public interface IOnHeaderClickListener{
        public void onMenuClicked();
        public void onBack();
    }
    private IOnHeaderClickListener mOnHeaderClickListener;
    private View mRootView;
    /*Constructor*/
    public CommonHeader(){}
    public CommonHeader(View view){
        mRootView = view;
        View menuView = mRootView.findViewById(R.id.llLeft);
        menuView.setOnClickListener(this);
        View backView = mRootView.findViewById(R.id.llRight);
        backView.setOnClickListener(this);
        TextView tvHeader = (TextView) mRootView.findViewById(R.id.tvHeader);
        tvHeader.setText(R.string.app_name);

        // Apply font
        AppFnUtils.applyFontForTextViewChild(mRootView, null);
    }
    /*Getter and setter*/
    public void setOnHeaderClickListener(IOnHeaderClickListener onHeaderClickListener){
        mOnHeaderClickListener = onHeaderClickListener;
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.llLeft:
                if(mOnHeaderClickListener != null){
                    mOnHeaderClickListener.onMenuClicked();
                }
                break;
            case R.id.llRight:
                if(mOnHeaderClickListener != null){
                    mOnHeaderClickListener.onBack();
                }
                break;
        }
    }
    public void showBackButton(){
        View backView = mRootView.findViewById(R.id.llRight);
        backView.setVisibility(View.VISIBLE);
    }
    public void hideBackButton(){
        View backView = mRootView.findViewById(R.id.llRight);
        backView.setVisibility(View.INVISIBLE);
    }
}
