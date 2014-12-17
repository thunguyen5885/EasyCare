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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import vn.easycare.R;
import vn.easycare.layers.ui.components.adapters.DatingListAdapter;

/**
 * Created by ThuNguyen on 12/17/2014.
 */
public class DatingListLayout extends LinearLayout{
    // For control, layout
    private ListView mLvDatingList;
    private DatingListAdapter mAdapter;
    private EditText mEdtDatingCode;
    private EditText mEdtPatientName;
    private View mSelectCalendarView;
    private TextView mTvCalendarText;
    private View mSearchLayout;
    private View mLoadMoreView;

    // For data, object
    private int mSelectedYear;
    private int mSelectedMonth;
    private int mSelectedDay;

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

        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dating_pager_item_ctrl, null);
        mLoadMoreView = mLayoutInflater.inflate(R.layout.load_more_ctrl, null);
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
    }

    /**
     *
     * @param isWaiting
     */
    public void renderData(boolean isWaiting){
        mAdapter = new DatingListAdapter(getContext());
        mAdapter.setWaitingList(isWaiting);
        mLvDatingList.setAdapter(mAdapter);
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
