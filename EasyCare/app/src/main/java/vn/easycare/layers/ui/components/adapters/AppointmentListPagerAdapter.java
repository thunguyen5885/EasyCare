package vn.easycare.layers.ui.components.adapters;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import vn.easycare.layers.ui.components.views.AppointmentListGridLayout;
import vn.easycare.layers.ui.components.views.AppointmentListLayout;
import vn.easycare.layers.ui.components.views.PatientListLayout;
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
        return 2;
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
        View mCurrentView = null;
        if(mViewMaps.containsKey(position)){
            mCurrentView = mViewMaps.get(position);

        }else{
            if(position == 0){
                AppointmentListGridLayout appointmentListGridLayout = new AppointmentListGridLayout(mViewPager.getContext());
                appointmentListGridLayout.loadNewData();
                mViewMaps.put(position, appointmentListGridLayout);
                mCurrentView = appointmentListGridLayout;
            }else {
                AppointmentListLayout appointmentListLayout = new AppointmentListLayout(mViewPager.getContext());
//                if (position == 0) {
//                    appointmentListLayout.setDateType(AppConstants.EXAMINATION_STATUS.WAITING);
//                } else if (position == 1) {
//                    appointmentListLayout.setDateType(AppConstants.EXAMINATION_STATUS.ACCEPTED);
//                } else {
//                    appointmentListLayout.setDateType(AppConstants.EXAMINATION_STATUS.CANCEL);
//                }
                appointmentListLayout.setDateType(AppConstants.EXAMINATION_STATUS.WAITING);
                appointmentListLayout.setIBroadCast(mBroadCastToSynData);
                appointmentListLayout.loadNewData();
                mViewMaps.put(position, appointmentListLayout);
                mCurrentView = appointmentListLayout;
            }
        }

        container.addView(mCurrentView);
        return mCurrentView;
    }

    /**
     * Load data again for current item if data changed
     */
    public void updateDataIfAnyForCurrentItem(){
//        int currentPos = mViewPager.getCurrentItem();
//        AppointmentListLayout curItem = (AppointmentListLayout) mViewMaps.get(currentPos);
//        if(curItem != null) {
//            curItem.enforceToRefreshForDataChanged();
//        }
    }

    private IBroadCastToSynData mBroadCastToSynData = new IBroadCastToSynData() {
        @Override
        public void broadCast(AppConstants.EXAMINATION_STATUS status) {
            // Always change on the first item
//            DatingListLayout waitingDatingListLayout = (DatingListLayout) mViewMaps.get(0);
//            waitingDatingListLayout.setNeedToRefresh(true);
//            switch (status){
//                case ACCEPTED:
//                    AppointmentListLayout acceptAppointmentListLayout = (AppointmentListLayout) mViewMaps.get(1);
//                    if(acceptAppointmentListLayout != null) {
//                        acceptAppointmentListLayout.refreshDataWhenDataChanged();
//                    }
//                    break;
//                case WAITING:
//                    break;
//                case CANCEL:
//                    AppointmentListLayout cancelAppointmentListLayout = (AppointmentListLayout) mViewMaps.get(2);
//                    if(cancelAppointmentListLayout != null) {
//                        cancelAppointmentListLayout.refreshDataWhenDataChanged();
//                    }
//                    break;
//            }
        }
    };
    public void refreshAllItems(){
//        for (Map.Entry<Integer, View> entry : mViewMaps.entrySet()) {
//            View view = entry.getValue();
//            int key = entry.getKey();
//            if(view instanceof AppointmentListLayout){
//                AppointmentListLayout appointmentListLayout = (AppointmentListLayout)view;
//                if(mViewPager.getCurrentItem() == key) {
//                    appointmentListLayout.refreshDataAndShowLoading();
//                }else {
//                    appointmentListLayout.refreshDataWhenDataChanged();
//                }
//            }
//        }
    }
}
