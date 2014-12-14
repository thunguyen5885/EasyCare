package vn.easycare.layers.ui.components.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;

import vn.easycare.R;

/**
 * Created by Thu Nguyen on 12/13/2014.
 */
public class RatingLayout extends LinearLayout{
    private static final int CHILD_NUM = 5;
    public RatingLayout(Context context) {
        super(context);
        init();
    }

    public RatingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RatingLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    private void init(){
        for(int index = 0; index < CHILD_NUM; index++){
            ImageView child = new ImageView(getContext());
            child.setImageResource(R.drawable.ic_star_unactive);
            int childSize = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 13, getResources().getDisplayMetrics());
            LayoutParams params = new LayoutParams(childSize, childSize);
            child.setLayoutParams(params);

            addView(child);
        }
    }
    public void setSelection(int selection){
        if(selection > -1 && selection < CHILD_NUM){
            for(int index = 0; index <= selection; index++){
                ImageView child = (ImageView) getChildAt(index);
                child.setImageResource(R.drawable.ic_star_active);
            }
        }
    }
}
