package vn.easycare.layers.ui.components;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import vn.easycare.R;

public class CompatSlidingPaneLayout extends SlidingPaneLayout {

	private boolean mDisableTouch = false;
    private boolean mTouchedDown = false;
    private boolean mForwardTouchesToChildren = false;
    private int mHeaderHeight = 100;
    private int mCommonPadding = 50;
	
	public CompatSlidingPaneLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle); 
		
		init();
	}
	
	public CompatSlidingPaneLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		init();
	}
	
	public CompatSlidingPaneLayout(Context context) {
		super(context);
		
		init();
	}
	
	private void init() {		
		this.setSliderFadeColor(getResources().getColor(android.R.color.transparent)); 
		
		this.mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
		this.mCommonPadding = getResources().getDimensionPixelSize(R.dimen.common_padding);				
	}

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        /*
         * If not slideable or if the "sliding" pane is open, let super method
         * handle it.
         */    	
        if (!isSlideable() || isOpen())
            return super.onInterceptTouchEvent(e);

        if (mDisableTouch) {
        	return false;
        }
        
        switch (e.getActionMasked()) {
        case MotionEvent.ACTION_DOWN: {
            mTouchedDown = true;

            /*
             * "100" should be defined as a constant. Also this should be
             * re-calculated in case the "sliding" pane is put at right side.
             */            
            if (e.getX() > (mCommonPadding * 2) || e.getY() < mHeaderHeight) {
                mForwardTouchesToChildren = true;
                return false;
            } else
                mForwardTouchesToChildren = false;

            break;
        }

        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_CANCEL: {
            mTouchedDown = false;
            mForwardTouchesToChildren = false;

            break;
        }
        }

        if (mTouchedDown && mForwardTouchesToChildren)
            return false;

        if (mTouchedDown)
            return true;

        return super.onInterceptTouchEvent(e);
    }// onInterceptTouchEvent()
	
    /**
     * Disable touch on slide menu
     * 
     * @param disableTouch
     */
    public void setDisableTouch(boolean disableTouch) {
		this.mDisableTouch = disableTouch;
	}
    
}
