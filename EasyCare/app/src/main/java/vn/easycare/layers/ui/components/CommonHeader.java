package vn.easycare.layers.ui.components;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import vn.easycare.R;

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
    private boolean mIsClicked = false;
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
    }
    /*Getter and setter*/
    public void setOnHeaderClickListener(IOnHeaderClickListener onHeaderClickListener){
        mOnHeaderClickListener = onHeaderClickListener;
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
        }, 1000);
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
}
