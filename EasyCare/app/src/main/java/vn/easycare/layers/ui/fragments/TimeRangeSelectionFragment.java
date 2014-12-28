package vn.easycare.layers.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by ThuNguyen on 12/13/2014.
 */
public class TimeRangeSelectionFragment extends Fragment implements View.OnClickListener{

    // For control, layout
    private TextView mTvDate;
    private TextView mTvFromTime;
    private TextView mTvToTime;
    private TextView mTvAverageTime;
    private TextView mTvOfficeAddress;
    private EditText mEdtComment;

    private View mFromTimeLayout;
    private View mToTimeLayout;
    private View mAverageTimeLayout;
    private View mOfficeAddressLayout;
    private View mCancelLayout;
    private View mSaveLayout;

    // For data, object
    private boolean mIsClicked = false;
    public TimeRangeSelectionFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_time_range, container, false);
        mTvDate = (TextView) v.findViewById(R.id.tvTimeRangeDayContent);
        mTvFromTime = (TextView) v.findViewById(R.id.tvFromTime);
        mTvToTime = (TextView) v.findViewById(R.id.tvToTime);
        mTvAverageTime = (TextView) v.findViewById(R.id.tvAverageTime);
        mTvOfficeAddress = (TextView) v.findViewById(R.id.tvOfficeAddress);
        mEdtComment = (EditText) v.findViewById(R.id.edtComment);

        mFromTimeLayout = v.findViewById(R.id.rlFromTime);
        mToTimeLayout = v.findViewById(R.id.rlToTime);
        mAverageTimeLayout = v.findViewById(R.id.rlAverageTime);
        mOfficeAddressLayout = v.findViewById(R.id.rlOfficeAddressLayout);
        mCancelLayout = v.findViewById(R.id.cancelLayout);
        mSaveLayout = v.findViewById(R.id.saveLayout);

        // Set onclick
        mFromTimeLayout.setOnClickListener(this);
        mToTimeLayout.setOnClickListener(this);
        mAverageTimeLayout.setOnClickListener(this);
        mAverageTimeLayout.setOnClickListener(this);
        mOfficeAddressLayout.setOnClickListener(this);
        mCancelLayout.setOnClickListener(this);
        mSaveLayout.setOnClickListener(this);

        // Apply font
        AppFnUtils.applyFontForTextViewChild(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvDate.setText("19/11/2014");
        mTvFromTime.setText("12:45");
        mTvToTime.setText("17:00");
        mTvAverageTime.setText("15");
        mTvOfficeAddress.setText("170 Hoang Van Thu, Thanh Xuan, Ha Noi");

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            ((HomeActivity) getActivity()).showFooterSeparator();
            ((HomeActivity) getActivity()).showHeaderBackButton();
        }
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
        switch (v.getId()) {
            case R.id.rlFromTime:
                Toast.makeText(getActivity(), "FromTime clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rlToTime:
                Toast.makeText(getActivity(), "ToTime clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rlAverageTime:
                Toast.makeText(getActivity(), "AverageTime clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rlOfficeAddressLayout:
                Toast.makeText(getActivity(), "OfficeAddress clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancelLayout:
                ((HomeActivity) getActivity()).onBackPressed();
                break;
            case R.id.saveLayout:
                ((HomeActivity) getActivity()).onBackPressed();
                break;
        }
    }
}
