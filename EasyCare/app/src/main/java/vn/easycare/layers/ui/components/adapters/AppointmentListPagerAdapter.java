package vn.easycare.layers.ui.components.adapters;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import vn.easycare.layers.ui.components.views.AppointmentListLayout;
import vn.easycare.utils.AppConstants;

/**
 * Created by Thu Nguyen on 12/16/2014.
 */
public class AppointmentListPagerAdapter extends PagerAdapter{
    public interface IBroadCastToSynData{
        public void broadCast(AppConstants.EXAMINATION_STATUS status);
    }
    private ViewPager mViewPager;
    private HashMap<Integer, View> mViewMaps;
    private String mPatientId;

    public AppointmentListPagerAdapter(ViewPager viewPager){
        mViewPager = viewPager;
        mViewMaps = new HashMap<Integer, View>();
    }
    public void setPatientId(String patientId){
        mPatientId = patientId;
    }
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView((View)object);
        mViewMaps.remove(object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        AppointmentListLayout appointmentListLayout;
        if(mViewMaps.containsKey(position)){
            appointmentListLayout = (AppointmentListLayout)mViewMaps.get(position);
        }else{
            appointmentListLayout = new AppointmentListLayout(mViewPager.getContext());
            if(position == 0) {
                appointmentListLayout.setDateType(AppConstants.EXAMINATION_STATUS.WAITING);
            }else if(position == 1){
                appointmentListLayout.setDateType(AppConstants.EXAMINATION_STATUS.ACCEPTED);
            }else{
                appointmentListLayout.setDateType(AppConstants.EXAMINATION_STATUS.CANCEL);
            }
            appointmentListLayout.loadNewData();
            mViewMaps.put(position, appointmentListLayout);
        }

        container.addView(appointmentListLayout);
        return appointmentListLayout;
    }

    /**
     * Load data again for current item if data changed
     */
    public void updateDataIfAnyForCurrentItem(){
        int currentPos = mViewPager.getCurrentItem();
        AppointmentListLayout curItem = (AppointmentListLayout) mViewMaps.get(currentPos);
        if(curItem != null) {
            curItem.enforceToRefreshForDataChanged();
        }
    }

    private IBroadCastToSynData mBroadCastToSynData = new IBroadCastToSynData() {
        @Override
        public void broadCast(AppConstants.EXAMINATION_STATUS status) {
            // Always change on the first item
//            DatingListLayout waitingDatingListLayout = (DatingListLayout) mViewMaps.get(0);
//            waitingDatingListLayout.setNeedToRefresh(true);
            switch (status){
                case ACCEPTED:
                    AppointmentListLayout acceptAppointmentListLayout = (AppointmentListLayout) mViewMaps.get(1);
                    acceptAppointmentListLayout.refreshDataWithNonSearch();
                    break;
                case WAITING:
                    break;
                case CANCEL:
                    AppointmentListLayout cancelAppointmentListLayout = (AppointmentListLayout) mViewMaps.get(2);
                    cancelAppointmentListLayout.refreshDataWithNonSearch();
                    break;
            }
        }
    };
    public void refreshAllItems(){
        for(int index = 0; index < mViewMaps.size(); index++){
            View view = mViewMaps.get(index);
            if(view instanceof AppointmentListLayout){
                AppointmentListLayout appointmentListLayout = (AppointmentListLayout)view;
                if(mViewPager.getCurrentItem() == index) {
                    appointmentListLayout.refreshDataAndShowLoading();
                }else {
                    appointmentListLayout.refreshDataWhenDataChanged();
                }
            }
        }
    }
}
