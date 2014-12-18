package vn.easycare.layers.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.adapters.CommentAdapter;

/**
 * Created by ThuNguyen on 12/13/2014.
 */
public class DatingDetailFragment extends Fragment implements View.OnClickListener{
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

    public DatingDetailFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set value
        initContentForChildView(mDateLayout, "19/11/2014, 11:15");
        initContentForChildView(mDatingCodeLayout, "EA0012L");
        initContentForChildView(mLocationLayout, "Phòng khám Bạch Mai");
        initContentForChildView(mPersonInDateLayout, "Bùi Hiệu");
        initContentForChildView(mRequestReasonLayout, "Viêm cơ xương");
        initContentForChildView(mNameLayout, "Bùi Hiệu");
        initContentForChildView(mGenderLayout, "Nam");
        initContentForChildView(mPhoneLayout, "09875245625");
        initContentForChildView(mEmailLayout, "abcd@gmail.com");
        initContentForChildView(mDateCreatingLayout, "18/11/2014");
        initContentForChildView(mStatusLayout, "Chấp nhận khám");
        initContentForChildView(mMoreInfoLayout, "không có");

        initExtraMoreForChildView(mPersonInDateLayout, "Lần đầu tới khám");


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
        switch (v.getId()) {
            case R.id.sendLayout:
                Toast.makeText(getActivity(), "Send clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCalendarChange:
                Toast.makeText(getActivity(), "Calendar change clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCalendarCancel:
                Toast.makeText(getActivity(), "Calendar cancel clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCalendarAccept:
                Toast.makeText(getActivity(), "Calendar accept clicked", Toast.LENGTH_SHORT).show();
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
            tvValue.setText(contentVal);
        }
    }
    private void initExtraMoreForChildView(View parentView, String contentVal){
        if(parentView != null){
            TextView tvExtraMore = (TextView)parentView.findViewById(R.id.tvExtraMore);
            tvExtraMore.setText(contentVal);
        }
    }
}
