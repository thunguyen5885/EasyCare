package vn.easycare.layers.ui.components.views;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.easycare.R;
import vn.easycare.utils.AppFnUtils;

public class TextExpandableLayout extends LinearLayout {
	
	private static final int MAX_LINE = 3;
	
	private String mShowMoreText = "Show More";
	
	private String mShowLessText = "Show Less";
	
	private Context mContext;
	
	private TextView tvDescription, tvMoreLess;
	
	private int countLine;
	
	private boolean isFirstLoad;
	
	public TextExpandableLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		initView(context);
	}

	public TextExpandableLayout(Context context, AttributeSet attrs) {
		super(context, attrs);

		initView(context);
	}

	public TextExpandableLayout(Context context) {
		super(context);

		initView(context);
	}

	private void initView(Context context) {
	
		mContext = context;

		isFirstLoad = true;

        mShowMoreText = getResources().getString(R.string.view_more);
        mShowLessText = getResources().getString(R.string.view_less);

		View v = View.inflate(mContext, R.layout.text_expandable_ctrl, this);
		
		tvDescription = (TextView) v.findViewById(R.id.tvDescription);
		tvMoreLess = (TextView) v.findViewById(R.id.tvMoreLess);
		tvMoreLess.setPaintFlags(tvMoreLess.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
		// Set action for tv More Less
		tvMoreLess.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				
				int currentLines = tvDescription.getLineCount();
				
				if (currentLines == MAX_LINE) {
					expand();
				} else {
					collapse();
				}				
			}
		});
		
		tvDescription.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {			
			@Override
			public void onGlobalLayout() {
				
				String str = tvDescription.getText().toString();
				if (isFirstLoad && !str.equalsIgnoreCase("")) {
				
					countLine = tvDescription.getLineCount();
					
					// Calculate the line of text
					if (countLine > MAX_LINE) {
						tvDescription.setMaxLines(MAX_LINE);
						tvDescription.setEllipsize(TextUtils.TruncateAt.END);
						
						tvMoreLess.setText(mShowMoreText);
						tvMoreLess.setVisibility(View.VISIBLE);
					} else {
						tvMoreLess.setVisibility(View.GONE);
					}
					
					isFirstLoad = false;
				}				
			}
		});
	}
	public void setTypeface(int style) {
		tvDescription.setTypeface(null, style);
		tvMoreLess.setTypeface(null, style);
        AppFnUtils.applyFontForTextViewChild(this);
	}
	
	public void setText(String str) {
		if(str != null && str.length() > 0){
			// Trim first
			str = str.trim();
			// Remove html tag
			str = str.replaceAll("\\<.*?>", "");
			
			// Set text 
			tvDescription.setText(str);
		}
	}
    public void setTextColor(int color){
        tvDescription.setTextColor(color);
    }

	/**
	 * Expand all text
	 */
	public void expand(){
		isFirstLoad = false;
		tvDescription.setMaxLines(Integer.MAX_VALUE);
		tvMoreLess.setText(mShowLessText);
		tvMoreLess.setVisibility(View.VISIBLE);

	}
	/**
	 * Show as view more
	 */
	public void collapse(){
		isFirstLoad = false;
		tvDescription.setMaxLines(MAX_LINE);
		tvDescription.setEllipsize(TextUtils.TruncateAt.END);
		
		tvMoreLess.setText(mShowMoreText);
		tvMoreLess.setVisibility(View.VISIBLE);
	}
}
