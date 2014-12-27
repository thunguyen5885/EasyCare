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
import android.widget.Toast;

import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.layers.ui.components.views.DatingListLayout;
import vn.easycare.layers.ui.presenters.ExaminationAppointmentPresenterImpl;
import vn.easycare.layers.ui.presenters.base.IExaminationAppointmentPresenter;
import vn.easycare.layers.ui.views.IExaminationAppointmentView;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.DialogUtil;

/**
 * Created by ThuNguyen on 12/13/2014.
 */
public class DatingDetailFragment extends Fragment implements View.OnClickListener, IExaminationAppointmentView{
    // For control, layout
    private View mDateLayout;
    private View mDatingCodeLayout;
    private View mLocationLayout;
    private View mPersonInDateLayout;
    private View mRequestReasonLayout;
    private View mNameLayout;
    private View mGenderLayout;
    private View mPhoneLayout;
    private View mEmailLayout;
    private View mDateCreatingLayout;
    private View mStatusLayout;
    private View mMoreInfoLayout;
    private View mSendLayout;
    private EditText mEdtDoctorComment;
    private Button mBtnCalendarChange;
    private Button mBtnCalendarCancel;
    private Button mBtnCalendarAccept;

    // For data, object
    boolean mIsCLicked = false;
    private ExaminationAppointmentItemData mItemData;
    private IExaminationAppointmentPresenter mPresenter;
    private Dialog mLoadingDialog;
    private AppointmentTime mAppointmentTime;
    public DatingDetailFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new ExaminationAppointmentPresenterImpl(this, getActivity());
        mAppointmentTime = new AppointmentTime();
        View v = inflater.inflate(R.layout.fragment_dating_detail, container, false);
        mDateLayout = v.findViewById(R.id.datingDetailDateLayout);
        mDatingCodeLayout = v.findViewById(R.id.datingDetailDatingCodeLayout);
        mLocationLayout = v.findViewById(R.id.datingDetailLocationLayout);
        mPersonInDateLayout = v.findViewById(R.id.datingDetailPersonInDateLayout);
        mRequestReasonLayout = v.findViewById(R.id.datingDetailRequestReasonLayout);
        mNameLayout = v.findViewById(R.id.datingDetailNameLayout);
        mGenderLayout = v.findViewById(R.id.datingDetailGenderLayout);
        mPhoneLayout = v.findViewById(R.id.datingDetailPhoneLayout);
        mEmailLayout = v.findViewById(R.id.datingDetailEmailLayout);
        mDateCreatingLayout = v.findViewById(R.id.datingDetailDateCreatingLayout);
        mStatusLayout = v.findViewById(R.id.datingDetailStatusLayout);
        mMoreInfoLayout = v.findViewById(R.id.datingDetailMoreInfoLayout);
        mSendLayout = v.findViewById(R.id.sendLayout);
        mEdtDoctorComment = (EditText)v.findViewById(R.id.edtDoctorComment);
        mBtnCalendarChange = (Button) v.findViewById(R.id.btnCalendarChange);
        mBtnCalendarCancel = (Button) v.findViewById(R.id.btnCalendarCancel);
        mBtnCalendarAccept = (Button) v.findViewById(R.id.btnCalendarAccept);

        // Init title for all views
        initTitleForChildView(mDateLayout, R.string.dating_detail_day);
        initTitleForChildView(mDatingCodeLayout, R.string.dating_detail_dating_code);
        initTitleForChildView(mLocationLayout, R.string.dating_detail_location);
        initTitleForChildView(mPersonInDateLayout, R.string.dating_detail_person_in_date);
        initTitleForChildView(mRequestReasonLayout, R.string.dating_detail_treat_reason);
        initTitleForChildView(mNameLayout, R.string.dating_detail_name);
        initTitleForChildView(mGenderLayout, R.string.dating_detail_gender);
        initTitleForChildView(mPhoneLayout, R.string.dating_detail_phone);
        initTitleForChildView(mEmailLayout, R.string.dating_detail_email);
        initTitleForChildView(mDateCreatingLayout, R.string.dating_detail_date_creating);
        initTitleForChildView(mStatusLayout, R.string.dating_detail_status);
        initTitleForChildView(mMoreInfoLayout, R.string.dating_detail_extra_info);
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
        AppFnUtils.applyFontForTextViewChild(v, null);
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
        }

        if(mItemData != null){
            // Begin update UI
            // Set value
            initContentForChildView(mDateLayout, mItemData.getExaminationDateTime());
            initContentForChildView(mDatingCodeLayout, mItemData.getExaminationCode());
            initContentForChildView(mLocationLayout, mItemData.getExaminationAddress());
            initContentForChildView(mPersonInDateLayout, mItemData.getExaminationByPerson());
            initContentForChildView(mRequestReasonLayout, mItemData.getExaminationReason());
            initContentForChildView(mNameLayout, mItemData.getPatientName());
            initContentForChildView(mGenderLayout, mItemData.getPatientGender());
            initContentForChildView(mPhoneLayout, mItemData.getPatientPhone());
            initContentForChildView(mEmailLayout, mItemData.getPatientEmail());
            initContentForChildView(mDateCreatingLayout, mItemData.getExaminationDateTimeAppointmentCreated());
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
                        mPresenter.ChangeAnExaminationAppointment(mItemData.getExaminationId(), null, 0, 0, mEdtDoctorComment.getText().toString());
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
                                mPresenter.ChangeAnExaminationAppointment(mItemData.getExaminationId(), null, 0, 0, null);
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
    public void DisplayPopupForAnAppointment(ExaminationAppointmentItemData item) {

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
