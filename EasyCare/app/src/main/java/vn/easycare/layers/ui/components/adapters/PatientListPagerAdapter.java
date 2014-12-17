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
            patientListLayout.renderData(position != 0);
            mViewMaps.put(position, patientListLayout);
        }
        container.addView(patientListLayout, position);
        return patientListLayout;
    }
}
