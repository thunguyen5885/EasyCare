package vn.easycare.layers.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.adapters.AppointmentListPagerAdapter;
import vn.easycare.layers.ui.components.data.PatientManagementItemData;
import vn.easycare.layers.ui.components.singleton.DataSingleton;
import vn.easycare.layers.ui.components.views.TabLayout;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by Thu Nguyen on 12/16/2014.
 */
public class AppointmentListForAPatientFragment extends Fragment{
    // For view, control
    private NetworkImageView mPatientAvatar;
    private TextView mTvPatientName;
    private TextView mTvPatientBirthday;
    private TextView mTvPatientPhone;
    private TextView mTvPatientEmail;
    private TextView mTvPatientAddress;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private AppointmentListPagerAdapter mPagerAdapter;

    // For data, object
    private List<String> mAppointmentList;
    private PatientManagementItemData mPatientManagementItemData;
    public AppointmentListForAPatientFragment(){
        mAppointmentList = new ArrayList<String>();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mAppointmentList.add(activity.getString(R.string.appointment_list_waiting));
        mAppointmentList.add(activity.getString(R.string.appointment_list_approved));
        mAppointmentList.add(activity.getString(R.string.appointment_list_cancel));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_appointment_list_for_a_patient, container, false);
        mPatientAvatar = (NetworkImageView) v.findViewById(R.id.patientAvatarThumb);
        mTvPatientName = (TextView) v.findViewById(R.id.tvPatientName);
        mTvPatientBirthday = (TextView) v.findViewById(R.id.tvPatientBirthday);
        mTvPatientPhone = (TextView) v.findViewById(R.id.tvPatientPhone);
        mTvPatientEmail = (TextView) v.findViewById(R.id.tvPatientEmail);
        mTvPatientAddress = (TextView) v.findViewById(R.id.tvPatientLocation);

        mTabLayout = (TabLayout) v.findViewById(R.id.appointmentListTabLayout);
        mTabLayout.createChild(mAppointmentList);
        mTabLayout.setOnTabItemClickListner(mOnTabItemClickListener);
        mViewPager = (ViewPager) v.findViewById(R.id.appointmentListViewPager);
        mViewPager.setOnPageChangeListener(mOnPageChangeListener);

        // Apply font
        AppFnUtils.applyFontForTextViewChild(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Show patient info here
        Bundle bundle = getArguments();
        if(bundle != null){
            mPatientManagementItemData = (PatientManagementItemData) bundle.getSerializable(AppConstants.PATIENT_DETAIL_KEY);
            if(mPatientManagementItemData != null) {
                // Update UI
                mPatientAvatar.setDefaultImageResId(R.drawable.ic_no_avatar);
                mPatientAvatar.setImageUrl(mPatientManagementItemData.getPatientAvatar(),
                        DataSingleton.getInstance(getActivity()).getImageLoader());

                mTvPatientName.setText(mPatientManagementItemData.getPatientName());
                mTvPatientBirthday.setText(mPatientManagementItemData.getPatientBirthday());
                mTvPatientPhone.setText(mPatientManagementItemData.getPatientPhoneNumber());
                mTvPatientEmail.setText(mPatientManagementItemData.getPatientEmailAddress());
                mTvPatientAddress.setText(mPatientManagementItemData.getPatientAddress());
            }
        }

        // Show tabs
        mPagerAdapter = new AppointmentListPagerAdapter(mViewPager);
        if(mPatientManagementItemData != null){
            mPagerAdapter.setPatientId(mPatientManagementItemData.getPatientId());
        }
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setSelection(0);
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
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if(mTabLayout != null){
                mTabLayout.setSelection(position);
            }
            // Reload the current item if data changed
            if(mPagerAdapter != null){
                mPagerAdapter.updateDataIfAnyForCurrentItem();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private TabLayout.IOnTabItemClickListener mOnTabItemClickListener = new TabLayout.IOnTabItemClickListener() {
        @Override
        public void onTabItemClickListener(int position) {
            if(mViewPager != null){
                mViewPager.setCurrentItem(position);
            }
        }
    };
}
