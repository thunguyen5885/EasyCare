package vn.easycare.layers.ui.components.views;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import vn.easycare.R;

/**
 * Created by phannguyen on 1/11/15.
 */
public class SpannableTextView extends TextView{

    public SpannableTextView(Context context) {
        super(context);
        addTextChangeListener(this);
        makeTextViewResizable(this, 3, this.getContext().getResources().getString(R.string.view_more), true);

    }

    public SpannableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addTextChangeListener(this);
        makeTextViewResizable(this, 3, this.getContext().getResources().getString(R.string.view_more), true);
    }

    public SpannableTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addTextChangeListener(this);
        makeTextViewResizable(this, 3, this.getContext().getResources().getString(R.string.view_more), true);
    }

    public void resetTextViewResizable(){
        this.setTag(null);
        makeTextViewResizable(this, 3, this.getContext().getResources().getString(R.string.view_more), true);
    }
    private void addTextChangeListener(final TextView tv){
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (tv.getTag() == null) {
                    tv.setTag(tv.getText());
                }
            }
        });
    }

    private void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {


        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                int countline = countLines(tv.getTag().toString());
                if (maxLine > 0 && countline >= maxLine) {
                    int lineEndIndex = lastIndexOfLineNth(tv.getTag().toString(),maxLine);
                    String text = tv.getTag().toString().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(text), tv, maxLine, expandText,
                                    viewMore), BufferType.SPANNABLE);
                }else if(maxLine == -1) {//view less
                    String text = tv.getTag().toString() + " " + expandText;
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(text), tv, maxLine, expandText,
                                    viewMore), BufferType.SPANNABLE);
                }
            }
        });

    }


    private SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                    final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {


            ssb.setSpan(new MySpannable(true){
                @Override
                public void onClick(View widget) {
                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, tv.getContext().getResources().getString(R.string.view_less), false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.invalidate();
                        makeTextViewResizable(tv, 3, tv.getContext().getResources().getString(R.string.view_more), true);
                    }
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }

    private int countLines(String str){
        String[] lines = str.split("\r\n|\r|\n");
        return  lines.length;
    }

    private int lastIndexOfLineNth(String text,int lineNth){
        String[] lines = text.split("\r\n|\r|\n");
        int lastIndex = 0;
        for(int i=0;i<lineNth;i++){
            lastIndex += lines[i].length();
        }
        return  lastIndex+lineNth-1;
    }
    public class MySpannable extends ClickableSpan {

        private boolean isUnderline = true;

        /**
         * Constructor
         */
        public MySpannable(boolean isUnderline) {
            this.isUnderline = isUnderline;
        }

        @Override
        public void updateDrawState(TextPaint ds) {

            ds.setUnderlineText(isUnderline);
            ds.setColor(Color.BLUE);

        }

        @Override
        public void onClick(View widget) {

        }
    }
}


