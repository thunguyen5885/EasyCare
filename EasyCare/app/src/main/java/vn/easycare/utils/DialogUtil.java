package vn.easycare.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.components.adapters.SimpleTextAdapter;
import vn.easycare.layers.ui.components.data.AppointmentTimeData;
import vn.easycare.layers.ui.components.views.AppointmentListLayout;
import vn.easycare.layers.ui.fragments.AppointmentDetailFragment;


public class DialogUtil {
	public static void showDateTimeDialog(Context context, AppointmentTimeData appointmentTime,
                                          final DatePicker.OnDateChangedListener onDateChangedListener,
                                          TimePicker.OnTimeChangedListener onTimeChangeListener,
                                          final OnClickListener mOnAcceptClickListener){
		final Dialog dialog = new Dialog(context,android.R.style.Theme_Holo_Dialog);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.date_time_picker_ctrl);

        // Get screen width
        int screenWidth = AppFnUtils.getScreenWidth((Activity) context);
        int screenHeight = AppFnUtils.getScreenHeight((Activity) context);
//        int datePickerWidth = screenWidth / 3;
//        int datePickerHeight = datePickerWidth;
//        int timePickerHeight = screenWidth / 3;
//        int timePickerWidth = 2*timePickerHeight / 3;
        //dialog.getWindow().setLayout(LayoutParams.WRAP_CONTENT, screenHeight / 2);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.layout_border_dialog);

        // Get date picker and time picker
        DatePicker datePicker = (DatePicker)dialog.findViewById(R.id.datePicker);
        datePicker.init(appointmentTime.getYear(), appointmentTime.getMonth(), appointmentTime.getDay(), onDateChangedListener);
        datePicker.setCalendarViewShown(false);
        datePicker.setClipToPadding(false);
        //datePicker.setSpinnersShown(true);

        TimePicker timePicker = (TimePicker)dialog.findViewById(R.id.timePicker);
        timePicker.setCurrentHour(appointmentTime.getHour());
        timePicker.setCurrentMinute(appointmentTime.getMinute());
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(onTimeChangeListener);

        View acceptView = dialog.findViewById(R.id.datetimePickerAcceptLayout);
        acceptView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(mOnAcceptClickListener != null){
                    mOnAcceptClickListener.onClick(v);
                }
            }
        });
		dialog.show();
	}
    public static void showListViewDialog(Context context, Object dataList,
                                          final SimpleTextAdapter.IOnItemClickListener onItemClickListener){
        final Dialog dialog = new Dialog(context,android.R.style.Theme_Holo_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.simple_list_view_ctrl);

        // Get screen width
        int screenWidth = AppFnUtils.getScreenWidth((Activity) context);
        int screenHeight = AppFnUtils.getScreenHeight((Activity) context);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.layout_border_dialog);

        ListView lvListView = (ListView) dialog.findViewById(R.id.lvSimple);
        SimpleTextAdapter adapter = new SimpleTextAdapter(context);
        if(dataList instanceof List) {
            adapter.setItemDataList((List<Object>)dataList);
        }
        adapter.setOnItemClickListener(new SimpleTextAdapter.IOnItemClickListener() {
            @Override
            public void onItemClickListener(int selectedPos) {
                dialog.dismiss();
                if(onItemClickListener != null){
                    onItemClickListener.onItemClickListener(selectedPos);
                }
            }
        });
        lvListView.setAdapter(adapter);
        dialog.show();
    }
    public static Dialog createLoadingDialog(Context context, String title){
        Dialog dialog = new Dialog(context,android.R.style.Theme_Holo_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading_dialog_ctrl);
        dialog.setCancelable(false);
        // Get screen width
        int screenWidth = AppFnUtils.getScreenWidth((Activity) context);
        int screenHeight = AppFnUtils.getScreenHeight((Activity) context);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.layout_border_dialog);

        TextView tvTitle = (TextView) dialog.findViewById(R.id.tvLoadingTitle);
        tvTitle.setText(title);

        return dialog;
    }

    public static Dialog createConfirmYesNoDialog(Context context, String title, String message,DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener){
        AlertDialog.Builder confirmDialogBuilder = new AlertDialog.Builder(context,android.R.style.Theme_Holo_Dialog);
        confirmDialogBuilder.setTitle(title);
        confirmDialogBuilder.setMessage(message);
        confirmDialogBuilder.setPositiveButton("Yes",yesListener);
        confirmDialogBuilder.setNegativeButton("No",noListener);
        return confirmDialogBuilder.create();
    }

    public static Dialog createInformDialog(Context context, String title, String message,DialogInterface.OnClickListener okListener){
        AlertDialog.Builder confirmDialogBuilder = new AlertDialog.Builder(context);
        confirmDialogBuilder.setTitle(title);
        confirmDialogBuilder.setMessage(message);
        confirmDialogBuilder.setPositiveButton("OK",okListener);
        return confirmDialogBuilder.create();
    }

}
