package vn.easycare.layers.ui.components.adapters;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import vn.easycare.layers.ui.components.views.DatingListLayout;
import vn.easycare.layers.ui.components.views.PatientListLayout;
import vn.easycare.utils.AppConstants;

/**
 * Created by Thu Nguyen on 12/16/2014.
 */
public class DatingListPagerAdapter extends PagerAdapter{
    public interface IBroadCastToSynData{
        public void broadCast(AppConstants.EXAMINATION_STATUS status);
    }
    private ViewPager mViewPager;
    private HashMap<Integer, View> mViewMaps;
    public DatingListPagerAdapter(ViewPager viewPager){
        mViewPager = viewPager;
        mViewMaps = new HashMap<Integer, View>();
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
        DatingListLayout datingListLayout;
        if(mViewMaps.containsKey(position)){
            datingListLayout = (DatingListLayout)mViewMaps.get(position);
        }else{
            datingListLayout = new DatingListLayout(mViewPager.getContext());

            if(position == 0) {
                datingListLayout.setDateType(AppConstants.EXAMINATION_STATUS.WAITING);
            }else if(position == 1){
                datingListLayout.setDateType(AppConstants.EXAMINATION_STATUS.ACCEPTED);
            }else{
                datingListLayout.setDateType(AppConstants.EXAMINATION_STATUS.CANCEL);
            }
            datingListLayout.loadNewData();

            mViewMaps.put(position, datingListLayout);
        }
        container.addView(datingListLayout);
        return datingListLayout;
    }

    /**
     * Load data again for current item if data changed
     */
    public void updateDataIfAnyForCurrentItem(){
        int currentPos = mViewPager.getCurrentItem();
        DatingListLayout curItem = (DatingListLayout) mViewMaps.get(currentPos);
        curItem.enforceToRefreshForDataChanged();
    }
    private IBroadCastToSynData mBroadCastToSynData = new IBroadCastToSynData() {
        @Override
        public void broadCast(AppConstants.EXAMINATION_STATUS status) {
            // Always change on the first item
//            DatingListLayout waitingDatingListLayout = (DatingListLayout) mViewMaps.get(0);
//            waitingDatingListLayout.setNeedToRefresh(true);
            switch (status){
                case ACCEPTED:
                    DatingListLayout acceptDatingListLayout = (DatingListLayout) mViewMaps.get(1);
                    acceptDatingListLayout.refreshDataWithNonSearch();
                    break;
                case WAITING:
                    break;
                case CANCEL:
                    DatingListLayout cancelDatingListLayout = (DatingListLayout) mViewMaps.get(2);
                    cancelDatingListLayout.refreshDataWithNonSearch();
                    break;
            }
        }
    };
}
