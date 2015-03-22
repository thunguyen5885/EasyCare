package vn.easycare.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vn.easycare.R;

/**
 * Created by phan on 8/7/2014.
 * For all util common functions
 */
public class AppFnUtils {

    public static String priceWithDecimal (Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###,###");
        return formatter.format(price);
    }

    public static String priceWithoutDecimal (Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###,###.###");
        return formatter.format(price);
    }

    public static String priceToString(Double price) {
        String toShow = priceWithoutDecimal(price);
        if (toShow.indexOf(".") > 0) {
            return priceWithDecimal(price);
        } else {
            return priceWithoutDecimal(price);
        }
    }
    public static int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }
    public static int getScreenHeight(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.heightPixels;
    }
    public static int getScreenWidth(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.widthPixels;
    }

    public static void applyFontForTextViewChild(View parentView){
        if(parentView instanceof ViewGroup){
            Typeface robotoRegular = FontUtil.getRobotoRegular(parentView.getContext());
//            Typeface robotoItalic = FontUtil.getRobotoItalic(parentView.getContext());
//            Typeface robotoBold = FontUtil.getRobotoBold(parentView.getContext());
//            Typeface robotoBoldItalic = FontUtil.getRobotoBoldItalic(parentView.getContext());
            ViewGroup parentViewGroup = (ViewGroup) parentView;
            for(int index = 0; index < parentViewGroup.getChildCount(); index++){
                View childView = parentViewGroup.getChildAt(index);
                if(childView instanceof TextView){
                    TextView textView = (TextView) childView;
                    Typeface typeface = textView.getTypeface();
                    if(typeface != null){
                        boolean isBold = typeface.isBold();
                        boolean isItalic = typeface.isItalic();
                        if(isBold && isItalic){
                            textView.setTypeface(robotoRegular, Typeface.BOLD_ITALIC);
                        }else{
                            if(isBold){
                                textView.setTypeface(robotoRegular, Typeface.BOLD);
                            }else if(isItalic){
                                textView.setTypeface(robotoRegular, Typeface.ITALIC);
                            }else{
                                textView.setTypeface(robotoRegular);
                            }
                        }
                    }else{
                        textView.setTypeface(robotoRegular);
                    }
                }else if(childView instanceof Button){
                    Button  button = (Button) childView;
                    Typeface typeface = button.getTypeface();
                    if(typeface != null){
                        boolean isBold = typeface.isBold();
                        boolean isItalic = typeface.isItalic();
                        if(isBold && isItalic){
                            button.setTypeface(robotoRegular, Typeface.BOLD_ITALIC);
                        }else{
                            if(isBold){
                                button.setTypeface(robotoRegular, Typeface.BOLD);
                            }else if(isItalic){
                                button.setTypeface(robotoRegular, Typeface.ITALIC);
                            }else{
                                button.setTypeface(robotoRegular);
                            }
                        }
                    }else{
                        button.setTypeface(robotoRegular);
                    }
                }else if(childView instanceof ViewGroup){
                    applyFontForTextViewChild(childView);
                }
            }
        }
    }
    public static String convertDateFormat(String fromDateFormat,
                                           String toDateFormat, String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(fromDateFormat);
        Date date = null;
        try {
            //Log.d("CCDateUtilss",
            //		"Need to remove the colon from the date string in the timeszone");
            date = sdf.parse(dateStr);
            SimpleDateFormat timeFormat = new SimpleDateFormat(toDateFormat);
            return timeFormat.format(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public static Calendar getCalendarFromDateFormat(String dateFormat, String dateStr){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        try {
            //Log.d("CCDateUtilss",
            //		"Need to remove the colon from the date string in the timeszone");
            date = sdf.parse(dateStr);
            calendar.setTime(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return calendar;
    }
    public static String getForegroundActivityName(Context context){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        return (taskInfo.size() > 0)?taskInfo.get(0).topActivity.getClassName() : null;
    }

    public static String replaceNullEmptyString(String replacedStr,Context mContext){
        if(replacedStr==null || replacedStr=="null" || replacedStr=="NULL" || replacedStr=="" || replacedStr==AppConstants.NOTHING_TEXTDATA)
            return mContext.getResources().getString(R.string.nothing_data);
        return  replacedStr;
    }
}
