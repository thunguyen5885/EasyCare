package vn.easycare.layers.ui.components.adapters;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import vn.easycare.layers.ui.components.views.PatientListLayout;

/**
 * Created by Thu Nguyen on 12/16/2014.
 */
public class PatientListPagerAdapter extends PagerAdapter{
    public interface IPatientListBroadcastListener{
        public void onUpdateData(boolean isBlackList);
    }
    private ViewPager mViewPager;
    private HashMap<Integer, View> mViewMaps;

    public PatientListPagerAdapter(ViewPager viewPager){
        mViewPager = viewPager;
        mViewMaps = new HashMap<Integer, View>();
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
        PatientListLayout patientListLayout;
        if(mViewMaps.containsKey(position)){
            patientListLayout = (PatientListLayout)mViewMaps.get(position);
        }else{
            patientListLayout = new PatientListLayout(mViewPager.getContext());
            patientListLayout.setPatientListBroadcastListener(mPatientListBroadcastListener);
            mViewMaps.put(position, patientListLayout);
        }
        patientListLayout.setBlackList(position != 0);
        patientListLayout.loadNewData();
        container.addView(patientListLayout);
        return patientListLayout;
    }

    private IPatientListBroadcastListener mPatientListBroadcastListener = new IPatientListBroadcastListener() {
        @Override
        public void onUpdateData(boolean isBlackList) {
            PatientListLayout layoutNeedUpdate = null;
            if(isBlackList){
                layoutNeedUpdate = (PatientListLayout) mViewMaps.get(1);
            }else{
                layoutNeedUpdate = (PatientListLayout) mViewMaps.get(0);
            }
            if(layoutNeedUpdate != null){
                layoutNeedUpdate.refreshData();
            }
        }
    };
    public void refreshAllItems(){
        for(int index = 0; index < mViewMaps.size(); index++){
            View view = mViewMaps.get(index);
            if(view instanceof PatientListLayout){
                PatientListLayout patientListLayout = (PatientListLayout)view;
                if(mViewPager.getCurrentItem() == index) {
                    patientListLayout.refreshDataWithLoadingDialog();
                }else {
                    patientListLayout.refreshData();
                }
            }
        }
    }
}
