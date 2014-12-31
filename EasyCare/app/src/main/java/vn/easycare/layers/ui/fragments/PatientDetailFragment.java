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
import vn.easycare.layers.ui.components.data.PatientManagementItemData;
import vn.easycare.layers.ui.components.singleton.DataSingleton;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by ThuNguyen on 12/18/2014.
 */
public class PatientDetailFragment extends Fragment {
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
    private PatientManagementItemData mPatientManagementItemData;

    public PatientDetailFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patient_detail, container, false);
        mPatientAvatar = (NetworkImageView) v.findViewById(R.id.patientAvatarThumb);
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

        // Apply font
        AppFnUtils.applyFontForTextViewChild(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAndUpdateDataFromBundle();
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
    private void getAndUpdateDataFromBundle(){
        Bundle bundle = getArguments();
        if(bundle != null){
            Object object = bundle.get(AppConstants.PATIENT_DETAIL_KEY);
            if(object != null && object instanceof PatientManagementItemData){
                mPatientManagementItemData = (PatientManagementItemData)object;

                // Update UI
                mPatientAvatar.setDefaultImageResId(R.drawable.ic_no_avatar);
                mPatientAvatar.setImageUrl(mPatientManagementItemData.getPatientAvatar(),
                        DataSingleton.getInstance(getActivity()).getImageLoader());

                mTvPatientName.setText(mPatientManagementItemData.getPatientName());

                if(mPatientManagementItemData.getPatientBirthday() != null &&
                        mPatientManagementItemData.getPatientBirthday().length() > 0){
                    String birthDay = AppFnUtils.convertDateFormat(AppConstants.DATE_FORMAT_YYYY_MM_DD, AppConstants.DATE_FORMAT_DD_MM_YYYY, mPatientManagementItemData.getPatientBirthday());
                    mTvPatientBirthday.setText(birthDay);
                    mTvPatientBirthday.setTextColor(getResources().getColor(R.color.textview_color_default));
                }else{
                    mTvPatientBirthday.setText(R.string.nothing_data);
                    mTvPatientBirthday.setTextColor(getResources().getColor(R.color.textview_color_grey));
                }
                if(mPatientManagementItemData.getPatientPhoneNumber() != null &&
                        mPatientManagementItemData.getPatientPhoneNumber().length() > 0){
                    mTvPatientPhone.setText(mPatientManagementItemData.getPatientPhoneNumber());
                    mTvPatientPhone.setTextColor(getResources().getColor(R.color.textview_color_default));
                }else{
                    mTvPatientPhone.setText(R.string.nothing_data);
                    mTvPatientPhone.setTextColor(getResources().getColor(R.color.textview_color_grey));
                }
                if(mPatientManagementItemData.getPatientEmailAddress() != null &&
                        mPatientManagementItemData.getPatientEmailAddress().length() > 0){
                    mTvPatientEmail.setText(mPatientManagementItemData.getPatientEmailAddress());
                    mTvPatientEmail.setTextColor(getResources().getColor(R.color.textview_color_default));
                }else{
                    mTvPatientEmail.setText(R.string.nothing_data);
                    mTvPatientEmail.setTextColor(getResources().getColor(R.color.textview_color_grey));
                }
                if(mPatientManagementItemData.getPatientAddress() != null &&
                        mPatientManagementItemData.getPatientAddress().length() > 0){
                    mTvPatientAddress.setText(mPatientManagementItemData.getPatientAddress());
                    mTvPatientAddress.setTextColor(getResources().getColor(R.color.textview_color_default));
                }else{
                    mTvPatientAddress.setText(R.string.nothing_data);
                    mTvPatientAddress.setTextColor(getResources().getColor(R.color.textview_color_grey));
                }
                mTvPatientOrderCount.setText(String.valueOf(mPatientManagementItemData.getPatientOrderCount()));
                mTvPatientCancelCount.setText(String.valueOf(mPatientManagementItemData.getPatientCancelCount()));
                mTvPatientChangeCount.setText(String.valueOf(mPatientManagementItemData.getPatientChangeCount()));
                mTvPatientCommentCount.setText(String.valueOf(mPatientManagementItemData.getPatientCommentCount()));
            }
        }

    }
}
