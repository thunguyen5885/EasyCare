package vn.easycare.layers.ui.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.presenters.ExaminationAppointmentPresenterImpl;
import vn.easycare.layers.ui.presenters.base.IExaminationAppointmentPresenter;
import vn.easycare.layers.ui.views.IExaminationAppointmentView;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.DialogUtil;

/**
 * Created by ThuNguyen on 12/13/2014.
 */
public class AppointmentDetailFragment extends Fragment implements View.OnClickListener, IExaminationAppointmentView{
    // For control, layout
    private View mAppointmentLayout;
    private View mAppointmentCodeLayout;
    private View mLocationLayout;
    private View mPersonInDateLayout;
    private View mRequestReasonLayout;
    private View mNameLayout;
    private View mGenderLayout;
    private View mPhoneLayout;
    private View mEmailLayout;
    private View mCalendarCreatingLayout;
    private View mStatusLayout;
    private View mMoreInfoLayout;
    private View mSendLayout;
    private EditText mEdtDoctorComment;
    private View mDatingListButtonLayout;
    private Button mBtnCalendarChange;
    private Button mBtnCalendarCancel;
    private Button mBtnCalendarAccept;

    // For data, object
    boolean mIsCLicked = false;
    private ExaminationAppointmentItemData mItemData;
    private IExaminationAppointmentPresenter mPresenter;
    private Dialog mLoadingDialog;
    private AppointmentTime mAppointmentTime;
    public AppointmentDetailFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new ExaminationAppointmentPresenterImpl(this, getActivity());
        mAppointmentTime = new AppointmentTime();
        View v = inflater.inflate(R.layout.fragment_appointment_detail, container, false);
        mAppointmentLayout = v.findViewById(R.id.appointmentDetailDateLayout);
        mAppointmentCodeLayout = v.findViewById(R.id.appointmentDetailDatingCodeLayout);
        mLocationLayout = v.findViewById(R.id.appointmentDetailLocationLayout);
        mPersonInDateLayout = v.findViewById(R.id.appointmentDetailPersonInDateLayout);
        mRequestReasonLayout = v.findViewById(R.id.appointmentDetailRequestReasonLayout);
        mNameLayout = v.findViewById(R.id.appointmentDetailNameLayout);
        mGenderLayout = v.findViewById(R.id.appointmentDetailGenderLayout);
        mPhoneLayout = v.findViewById(R.id.appointmentDetailPhoneLayout);
        mEmailLayout = v.findViewById(R.id.appointmentDetailEmailLayout);
        mCalendarCreatingLayout = v.findViewById(R.id.appointmentDetailDateCreatingLayout);
        mStatusLayout = v.findViewById(R.id.appointmentDetailStatusLayout);
        mMoreInfoLayout = v.findViewById(R.id.appointmentDetailMoreInfoLayout);
        mSendLayout = v.findViewById(R.id.sendLayout);
        mEdtDoctorComment = (EditText)v.findViewById(R.id.edtDoctorComment);
        mDatingListButtonLayout = v.findViewById(R.id.appointmentListButtonLayout);
        mBtnCalendarChange = (Button) v.findViewById(R.id.btnCalendarChange);
        mBtnCalendarCancel = (Button) v.findViewById(R.id.btnCalendarCancel);
        mBtnCalendarAccept = (Button) v.findViewById(R.id.btnCalendarAccept);

        // Init title for all views
        initTitleForChildView(mAppointmentLayout, R.string.appointment_detail_day);
        initTitleForChildView(mAppointmentCodeLayout, R.string.appointment_detail_dating_code);
        initTitleForChildView(mLocationLayout, R.string.appointment_detail_location);
        initTitleForChildView(mPersonInDateLayout, R.string.appointment_detail_person_in_date);
        initTitleForChildView(mRequestReasonLayout, R.string.appointment_detail_treat_reason);
        initTitleForChildView(mNameLayout, R.string.appointment_detail_name);
        initTitleForChildView(mGenderLayout, R.string.appointment_detail_gender);
        initTitleForChildView(mPhoneLayout, R.string.appointment_detail_phone);
        initTitleForChildView(mEmailLayout, R.string.appointment_detail_email);
        initTitleForChildView(mCalendarCreatingLayout, R.string.appointment_detail_date_creating);
        initTitleForChildView(mStatusLayout, R.string.appointment_detail_status);
        initTitleForChildView(mMoreInfoLayout, R.string.appointment_detail_extra_info);
        // Set on click
        mSendLayout.setOnClickListener(this);
        mBtnCalendarChange.setOnClickListener(this);
        mBtnCalendarAccept.setOnClickListener(this);
        mBtnCalendarCancel.setOnClickListener(this);

        // Set text changed listener
        mEdtDoctorComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String curText = s.toString().trim();
                if(curText.length() > 0){
                    // Enable "Send" button
                    mSendLayout.setEnabled(true);
                    mSendLayout.setBackgroundResource(R.drawable.layout_hightlight_button_with_corner);
                }else {
                    // Disable "Send" button
                    mSendLayout.setEnabled(false);
                    mSendLayout.setBackgroundResource(R.drawable.layout_disable_button_with_corner);
                }
            }
        });

        // Apply font here
        AppFnUtils.applyFontForTextViewChild(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null){
            Object object = bundle.getSerializable(AppConstants.APPOINTMENT_ID_KEY);
            if(object != null && object instanceof ExaminationAppointmentItemData){
                mItemData = (ExaminationAppointmentItemData) object;
            }
            boolean isWaitingType = bundle.getBoolean(AppConstants.EXAMINATION_TYPE_KEY, false);
            if(isWaitingType){
                mDatingListButtonLayout.setVisibility(View.VISIBLE);
            }else{
                mDatingListButtonLayout.setVisibility(View.GONE);
            }
        }

        if(mItemData != null){
            // Begin update UI
            // Set value
            String dateTime = AppFnUtils.convertDateFormat(AppConstants.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, AppConstants.DATE_FORMAT_DD_MM_YYYY_HH_MM, mItemData.getExaminationDateTime());
            String createdDateTime = AppFnUtils.convertDateFormat(AppConstants.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, AppConstants.DATE_FORMAT_DD_MM_YYYY_HH_MM, mItemData.getExaminationDateTimeAppointmentCreated());
            initContentForChildView(mAppointmentLayout, dateTime);
            initContentForChildView(mAppointmentCodeLayout, mItemData.getExaminationCode());
            initContentForChildView(mLocationLayout, mItemData.getExaminationAddress());
            initContentForChildView(mPersonInDateLayout, mItemData.getExaminationByPerson());
            initContentForChildView(mRequestReasonLayout, mItemData.getExaminationReason());
            initContentForChildView(mNameLayout, mItemData.getPatientName());
            initContentForChildView(mGenderLayout, mItemData.getPatientGender());
            initContentForChildView(mPhoneLayout, mItemData.getPatientPhone());
            initContentForChildView(mEmailLayout, mItemData.getPatientEmail());
            initContentForChildView(mCalendarCreatingLayout, createdDateTime);
            initContentForChildView(mStatusLayout, mItemData.getExaminationState());
            initContentForChildView(mMoreInfoLayout, mItemData.getExaminationExtraInfo());

            initExtraMoreForChildView(mPersonInDateLayout, mItemData.isExaminationFirstVisit());

            mEdtDoctorComment.setText(mItemData.getExaminationDoctorNote());
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            ((HomeActivity) getActivity()).showFooterSeparator();
            ((HomeActivity) getActivity()).showHeaderBackButton();
        }
    }

    @Override
    public void onClick(View v) {
        if(mIsCLicked){
            return;
        }
        mIsCLicked = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mIsCLicked = false;
            }
        }, 500);
        switch (v.getId()) {
            case R.id.sendLayout:
                mLoadingDialog = DialogUtil.createLoadingDialog(getActivity(), getResources().getString(R.string.loading_dialog_in_progress));
                mLoadingDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Change examination based on appointment time
                        mPresenter.ChangeAnExaminationAppointment(mItemData.getExaminationId(), "", "", 0, mEdtDoctorComment.getText().toString());
                    }
                }, 2000);
                break;
            case R.id.btnCalendarChange:
                // Update appointment time from data
                //...................
                //itemData.
                // Show datetime dialog here
                DialogUtil.showDateTimeDialog(getActivity(), mAppointmentTime, new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mAppointmentTime.set(year, monthOfYear, dayOfMonth);
                    }
                }, new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        mAppointmentTime.set(hourOfDay, minute);
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mLoadingDialog = DialogUtil.createLoadingDialog(getActivity(), getResources().getString(R.string.loading_dialog_in_progress));
                        mLoadingDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Change examination based on appointment time
                                mPresenter.ChangeAnExaminationAppointment(mItemData.getExaminationId(), "", "", 0, null);
                            }
                        }, 2000);
                    }
                });
                break;
            case R.id.btnCalendarCancel:
                mLoadingDialog = DialogUtil.createLoadingDialog(getActivity(), getResources().getString(R.string.loading_dialog_in_progress));
                mLoadingDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.CancelAnExaminationAppointment(mItemData.getExaminationId());
                    }
                }, 2000);
                break;
            case R.id.btnCalendarAccept:
                mLoadingDialog = DialogUtil.createLoadingDialog(getActivity(), getResources().getString(R.string.loading_dialog_in_progress));
                mLoadingDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.AcceptAnExaminationAppointment(mItemData.getExaminationId());
                    }
                }, 2000);
                break;
        }
    }
    private void initTitleForChildView(View parentView, int titleId){
        if(parentView != null){
            TextView tvKey = (TextView)parentView.findViewById(R.id.tvKey);
            tvKey.setText(titleId);
        }
    }
    private void initContentForChildView(View parentView, String contentVal){
        if(parentView != null){
            TextView tvValue = (TextView)parentView.findViewById(R.id.tvValue);
            if(contentVal != null && contentVal.length() > 0) {
                tvValue.setText(contentVal);
            }else{
                tvValue.setText(R.string.nothing_data);
                tvValue.setTextColor(getResources().getColor(R.color.textview_color_grey));
            }
        }
    }
    private void initExtraMoreForChildView(View parentView, boolean isShow){
        if(parentView != null){
            TextView tvExtraMore = (TextView)parentView.findViewById(R.id.tvExtraMore);
            if(isShow) {
                tvExtraMore.setText(String.format("(%s)", getResources().getString(R.string.nothing_data)));
            }else{

            }
        }
    }

    @Override
    public void DisplayExaminationAppointmentsForDoctor(List<ExaminationAppointmentItemData> examinationAppointmentItemsList) {

    }

    @Override
    public void DisplayMessageForAcceptAppointment(String message) {
        processWhenUpdateDone(message);
    }

    @Override
    public void DisplayMessageForCancelAppointment(String message) {
        processWhenUpdateDone(message);
    }

    @Override
    public void DisplayMessageForChangeAppointment(String message) {
        processWhenUpdateDone(message);
    }

    @Override
    public void DisplayDetailForAnAppointment(ExaminationAppointmentItemData item) {

    }

    @Override
    public void DisplayMessageIncaseError(String message) {

    }

    private void processWhenUpdateDone(String message){
        boolean isUpdatedDone = true;
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
        }
        if(isUpdatedDone) {
            // Can show message update done here
            // .....
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Gone this screen
                    ((HomeActivity) getActivity()).onBackPressed();
                }
            }, 500);

        }else{

        }
    }
    public class AppointmentTime{
        private int year;
        private int month;
        private int day;
        private int hour;
        private int minute;

        public AppointmentTime(){
            year = -1;
            month = -1;
            day = -1;
            hour = -1;
            minute = -1;
        }
        public void set(int year, int month, int day){
            this.year = year;
            this.month = month;
            this.day = day;
        }
        public void set(int hour, int minute){
            this.hour = hour;
            this.minute = minute;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }
    }
}
