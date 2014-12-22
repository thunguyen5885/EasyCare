package vn.easycare.layers.ui.components.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.components.adapters.DatingListAdapter;
import vn.easycare.layers.ui.components.data.ExaminationAppointmentItemData;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by ThuNguyen on 12/17/2014.
 */
public class DatingListLayout extends LinearLayout{
    public static final int DATING_WAITING_FOR_APPROVED = 0;
    public static final int DATING_APPROVED = 1;
    public static final int DATING_CANCEL = 2;
    private static final int DATE_ITEM_PER_PAGE = 10;
    // For control, layout
    private ListView mLvDatingList;
    private ProgressBar mPbLoading;
    private DatingListAdapter mAdapter;
    private EditText mEdtDatingCode;
    private EditText mEdtPatientName;
    private View mSelectCalendarView;
    private TextView mTvCalendarText;
    private View mSearchLayout;
    private LoadMoreLayout mLoadMoreView;

    // For data, object
    private int mSelectedYear;
    private int mSelectedMonth;
    private int mSelectedDay;
    private int mDatingType;
    private int mTotalItemCount;
    private int mPage;
    private List<ExaminationAppointmentItemData> mExaminationAppointmentItemDataList;

    // Key search
    private String mDatingCode;
    private String mPatientName;
    private String mDatingDate;

    private LayoutInflater mLayoutInflater;
    public DatingListLayout(Context context) {
        super(context);
        init(context);
    }

    public DatingListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DatingListLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    private void init(Context context) {
        mSelectedYear = -1;
        mSelectedMonth = -1;
        mSelectedDay = -1;

        // For key and result search
        mTotalItemCount = 0;
        mDatingType = DATING_WAITING_FOR_APPROVED;
        mPage = 1;
        mExaminationAppointmentItemDataList = new ArrayList<ExaminationAppointmentItemData>();

        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dating_pager_item_ctrl, null);
        mLoadMoreView = new LoadMoreLayout(getContext());
        mLoadMoreView.setOnLoadMoreClickListener(mILoadMoreClickListener);
        mLvDatingList = (ListView)view.findViewById(R.id.lvDatingList);
        mLvDatingList.addFooterView(mLoadMoreView);

        mEdtDatingCode = (EditText)view.findViewById(R.id.edtDatingCode);
        mEdtPatientName = (EditText)view. findViewById(R.id.edtPatientName);
        mSelectCalendarView = view.findViewById(R.id.rlSelectCalendarLayout);
        mSelectCalendarView.setOnClickListener(mOnClickListener);
        mTvCalendarText = (TextView)view.findViewById(R.id.tvCalendarText);
        mSearchLayout = view.findViewById(R.id.datingListSearchLayout);
        mSearchLayout.setOnClickListener(mOnClickListener);
        addView(view);

        // Apply font
        AppFnUtils.applyFontForTextViewChild(this, null);

        // Call API here
        loadNewData();
    }
    private void loadNewData(){
        mTotalItemCount = 0;
        mPage = 1;
        mExaminationAppointmentItemDataList.clear();
        // Show loading
        mPbLoading.setVisibility(View.VISIBLE);
        // Hide listview
        mLvDatingList.setVisibility(View.GONE);

        // load data
        loadData();

    }

    /**
     * Load more when click on loadmore button
     */
    private void loadMoreData(){
        mPage++;
        mLoadMoreView.beginLoading();

        loadData();
    }

    /**
     * Begin call API here
     */
    private void loadData(){

    }

    /**
     * Update GUI from list of data
     */
    private void updateUI(){
        mPbLoading.setVisibility(View.GONE);
        mLvDatingList.setVisibility(View.VISIBLE);
        if(mExaminationAppointmentItemDataList.size() == mTotalItemCount){ // load end of list
            // Close loadmore layout
            mLoadMoreView.closeView();
        }else{
            mLoadMoreView.loadMoreComplete();
        }
        if(mAdapter == null){
            mAdapter = new DatingListAdapter(getContext());
            mAdapter.setWaitingList(mDatingType == DATING_WAITING_FOR_APPROVED);
            mAdapter.setmExaminationAppointmentItemDatas(mExaminationAppointmentItemDataList);
            mLvDatingList.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }
    public void setDateType(int dateType){
        mDatingType = dateType;
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rlSelectCalendarLayout:
                    showDatePickerDialog();
                    break;
                case R.id.datingListSearchLayout:
                    Toast.makeText(getContext(), "Search clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private LoadMoreLayout.ILoadMoreClickListener mILoadMoreClickListener = new LoadMoreLayout.ILoadMoreClickListener() {
        @Override
        public void onLoadMoreClicked() {
            loadMoreData();
        }
    };
    private DatePickerDialog.OnDateSetListener mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mSelectedYear = year;
            mSelectedMonth = monthOfYear;
            mSelectedDay = dayOfMonth;

            // Update on UI
            mTvCalendarText.setText(mSelectedDay + "/" + (mSelectedMonth + 1) + "/" + mSelectedYear);
        }
    };
    private void showDatePickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int yearToSet = 0;
        int monthToSet = 0;
        int dayToSet = 0;
        if(mSelectedYear != -1){
            yearToSet = mSelectedYear;
        }else{
            yearToSet = calendar.get(Calendar.YEAR);
        }
        if(mSelectedMonth != -1){
            monthToSet = mSelectedMonth;
        }else{
            monthToSet = calendar.get(Calendar.MONTH);
        }
        if(mSelectedDay != -1){
            dayToSet = mSelectedDay;
        }else{
            dayToSet = calendar.get(Calendar.DAY_OF_MONTH);
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), mOnDateSetListener, yearToSet, monthToSet, dayToSet);
        datePickerDialog.show();
    }

}
