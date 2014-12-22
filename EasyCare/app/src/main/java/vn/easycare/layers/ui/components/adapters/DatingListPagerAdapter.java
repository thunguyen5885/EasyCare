package vn.easycare.layers.ui.components.adapters;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import vn.easycare.layers.ui.components.views.DatingListLayout;
import vn.easycare.layers.ui.components.views.PatientListLayout;

/**
 * Created by Thu Nguyen on 12/16/2014.
 */
public class DatingListPagerAdapter extends PagerAdapter{
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
            datingListLayout.setDateType(position);
            mViewMaps.put(position, datingListLayout);
        }
        container.addView(datingListLayout);
        return datingListLayout;
    }
}
