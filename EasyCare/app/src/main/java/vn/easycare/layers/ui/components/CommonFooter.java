package vn.easycare.layers.ui.components;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import vn.easycare.R;

/**
 * Created by ThuNguyen on 12/8/2014.
 */
public class CommonFooter implements View.OnClickListener{
    public interface IOnFooterClickListener{
        public void onPrivacyPolicyClicked();
        public void onTermOfUseClicked();
        public void onFullVersionClicked();
        public void onForDoctorClicked();
        public void onForEasyCareClicked();

    }
    private IOnFooterClickListener mOnFooterClickListener;
    private View mRootView;
    private boolean mIsClicked = false;
    /*Constructor*/
    public CommonFooter(){}
    public CommonFooter(View view){
        mRootView = view;
        TextView tvPrivacyPolicy = (TextView) mRootView.findViewById(R.id.tvFooterPrivacyPolicy);
        tvPrivacyPolicy.setOnClickListener(this);
        TextView tvTermOfUse = (TextView) mRootView.findViewById(R.id.tvFooterTermOfUse);
        tvTermOfUse.setOnClickListener(this);
        TextView tvFullVersion = (TextView) mRootView.findViewById(R.id.tvFooterFullVersion);
        tvFullVersion.setOnClickListener(this);
        TextView tvForDoctor = (TextView) mRootView.findViewById(R.id.tvFooterForDoctor);
        tvForDoctor.setOnClickListener(this);
        TextView tvForEasyCare = (TextView) mRootView.findViewById(R.id.tvFooterForEasyCare);
        tvForEasyCare.setOnClickListener(this);
    }
    /*Getter and setter*/
    public void setOnFooterClickListener(IOnFooterClickListener onFooterClickListener){
        mOnFooterClickListener = onFooterClickListener;
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

        }
    }
}
