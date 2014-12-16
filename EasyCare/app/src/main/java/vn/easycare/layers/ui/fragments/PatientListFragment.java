package vn.easycare.layers.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.components.adapters.PatientListPagerAdapter;
import vn.easycare.layers.ui.components.views.TabLayout;

/**
 * Created by Thu Nguyen on 12/16/2014.
 */
public class PatientListFragment extends Fragment{
    // For view, control
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private PatientListPagerAdapter mPagerAdapter;

    // For data, object
    private List<String> mPatientList;
    public PatientListFragment(){
        mPatientList = new ArrayList<String>();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mPatientList.add(activity.getString(R.string.patient_list_patient));
        mPatientList.add(activity.getString(R.string.patient_list_blacklist));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patient_list, container, false);
        mTabLayout = (TabLayout) v.findViewById(R.id.patientListTabLayout);
        mTabLayout.createChild(mPatientList);
        mTabLayout.setOnTabItemClickListner(mOnTabItemClickListener);
        mViewPager = (ViewPager) v.findViewById(R.id.patientListViewPager);
        mViewPager.setOnPageChangeListener(mOnPageChangeListener);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPagerAdapter = new PatientListPagerAdapter(mViewPager);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setSelection(0);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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