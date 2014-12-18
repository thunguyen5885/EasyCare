package vn.easycare.layers.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by ThuNguyen on 12/18/2014.
 */
public class PatientDetailFragment extends Fragment{
    // For layout, control
    private NetworkImageView mPatientAvatar;
    private TextView mTvPatientName;
    private TextView mTvPatientBirthday;
    private TextView mTvPatientPhone;
    private TextView mTvPatientEmail;
    private TextView mTvPatientAddress;
    private TextView mTvPatientOrderCount;
    private TextView mTvPatientCancelCount;
    private TextView mTvPatientChangeCount;
    private TextView mTvPatientCommentCount;

    // For data, object
    public PatientDetailFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patient_detail, container, false);
        mPatientAvatar = (NetworkImageView) v.findViewById(R.id.patientAvatar);
        mTvPatientName = (TextView) v.findViewById(R.id.tvPatientName);
        mTvPatientBirthday = (TextView) v.findViewById(R.id.tvPatientBirthday);
        mTvPatientPhone = (TextView) v.findViewById(R.id.tvPatientPhone);
        mTvPatientEmail = (TextView) v.findViewById(R.id.tvPatientEmail);
        mTvPatientAddress = (TextView) v.findViewById(R.id.tvPatientLocation);
        mTvPatientOrderCount = (TextView) v.findViewById(R.id.tvOrderCount);
        mTvPatientCancelCount = (TextView) v.findViewById(R.id.tvCancelCount);
        mTvPatientChangeCount = (TextView) v.findViewById(R.id.tvChangeCount);
        mTvPatientCommentCount = (TextView) v.findViewById(R.id.tvCommentCount);

        // Calculate the avatar size
        int screenWidth = AppFnUtils.getScreenWidth((Activity) getActivity());
        int avatarSize = screenWidth / 5;
        mPatientAvatar.getLayoutParams().width = avatarSize;
        mPatientAvatar.getLayoutParams().height = avatarSize;

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateData();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            ((HomeActivity) getActivity()).hideFooterSeparator();
            ((HomeActivity) getActivity()).showHeaderBackButton();
        }
    }
    private void updateData(){
        mPatientAvatar.setDefaultImageResId(R.drawable.ic_no_avatar);
        mTvPatientName.setText("Bùi Hiệu");
        mTvPatientBirthday.setText("01/02/1980");
        mTvPatientPhone.setText("0989658741");
        mTvPatientEmail.setText("abc@gmail.com");
        mTvPatientAddress.setText("Số 42, Lê Trọng Tấn, Thanh Xuân, Hà Nội");
        mTvPatientOrderCount.setText("108");
        mTvPatientCancelCount.setText("30");
        mTvPatientChangeCount.setText("50");
        mTvPatientCommentCount.setText("28");
    }
}
