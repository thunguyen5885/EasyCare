package vn.easycare.layers.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.adapters.AppointmentListPagerAdapter;
import vn.easycare.layers.ui.components.views.TabLayout;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by Thu Nguyen on 12/16/2014.
 */
public class AppointmentListFragment extends Fragment{
    // For view, control
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private View mRefreshLayout;
    private AppointmentListPagerAdapter mPagerAdapter;

    // For data, object
    private boolean mIsClicked = false;
    private List<String> mAppointmentList;
    public AppointmentListFragment(){
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
        View v = inflater.inflate(R.layout.fragment_appointment_list, container, false);
        mRefreshLayout = v.findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnClickListener(new View.OnClickListener() {
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
                mPagerAdapter.refreshAllItems();
            }
        });
        mTabLayout = (TabLayout) v.findViewById(R.id.appointmentListTabLayout);
        mTabLayout.createChild(mAppointmentList, false);
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


        mPagerAdapter = new AppointmentListPagerAdapter(mViewPager);
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

            // Hide notify layout here
            ((HomeActivity) getActivity()).hideNotifyLayout();
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
