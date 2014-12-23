package vn.easycare.layers.ui.components.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.components.adapters.PatientListAdapter;
import vn.easycare.layers.ui.components.data.PatientManagementItemData;
import vn.easycare.layers.ui.presenters.PatientManagementPresenterImpl;
import vn.easycare.layers.ui.presenters.base.IPatientManagementPresenter;
import vn.easycare.layers.ui.views.IPatientManagementView;

/**
 * Created by ThuNguyen on 12/16/2014.
 */
public class PatientListLayout extends FrameLayout implements IPatientManagementView{
    private LayoutInflater mLayoutInflater;
    private LoadMoreLayout mLoadMoreView;
    private ListView mPatientListView;
    private ProgressBar mPbLoading;

    // For data object
    private PatientListAdapter mAdapter;
    private List<PatientManagementItemData> mManagementItemDatas;
    private IPatientManagementPresenter mPresenter;
    private int mPage;
    private boolean mIsBlackList;

    public PatientListLayout(Context context) {
        super(context);
        init(context);
    }

    public PatientListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PatientListLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    private void init(Context context){
        mManagementItemDatas = new ArrayList<PatientManagementItemData>();
        mPage = 1;
        mPresenter = new PatientManagementPresenterImpl(this, getContext());

        // For layout
        mLayoutInflater = LayoutInflater.from(context);
        View v = mLayoutInflater.inflate(R.layout.patient_pager_item_ctrl, null);
        mPatientListView = (ListView) v.findViewById(R.id.lvPatientList);
        mPbLoading = (ProgressBar) v.findViewById(R.id.pbLoading);

        mLoadMoreView = new LoadMoreLayout(context);
        mLoadMoreView.setOnLoadMoreClickListener(mOnLoadMoreClickListener);
        mPatientListView.addFooterView(mLoadMoreView);

        addView(v);
    }
    public void setBlackList(boolean isBlackList){
        mIsBlackList = isBlackList;
    }
    public void loadNewData(){
        mPage = 1;
        mManagementItemDatas.clear();
        mPbLoading.setVisibility(View.VISIBLE);
        mPatientListView.setVisibility(View.GONE);

        loadData();
    }
    private void loadMoreData(){
        mPage ++;
        mLoadMoreView.beginLoading();
        loadData();
    }
    private void loadData(){
        if(mIsBlackList){
            mPresenter.loadAllBlockedPatientsForDoctor(null);
        }else{
            mPresenter.loadAllAvailablePatientsForDoctor(null);
        }
    }
    public void updateUI(){
        mPbLoading.setVisibility(View.GONE);
        mPatientListView.setVisibility(View.VISIBLE);

        if(mAdapter == null){
            mAdapter = new PatientListAdapter(getContext());
            mAdapter.setBlackList(mIsBlackList);
            mAdapter.setItemDataList(mManagementItemDatas);
            mPatientListView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }
    private LoadMoreLayout.ILoadMoreClickListener mOnLoadMoreClickListener = new LoadMoreLayout.ILoadMoreClickListener() {
        @Override
        public void onLoadMoreClicked() {
            loadMoreData();
        }
    };

    @Override
    public void DisplayAllAvailablePatientsForDoctor(List<PatientManagementItemData> patientManagementItemsList) {
        if(patientManagementItemsList != null && patientManagementItemsList.size() > 0) {
            if (mPage == 1) {
                if (mManagementItemDatas != null) {
                    mManagementItemDatas.clear();
                }
                mManagementItemDatas.addAll(patientManagementItemsList);
            } else { // Failed or go to end of list
                mManagementItemDatas.addAll(patientManagementItemsList);
            }
            mLoadMoreView.loadMoreComplete();
        }else { // Failed or go to end of list
            mLoadMoreView.closeView();
        }
        updateUI();
    }

    @Override
    public void DisplayAllBlockedPatientsForDoctor(List<PatientManagementItemData> patientManagementItemsList) {
        if(patientManagementItemsList != null && patientManagementItemsList.size() > 0) {
            if (mPage == 1) {
                if (mManagementItemDatas != null) {
                    mManagementItemDatas.clear();
                }
                mManagementItemDatas.addAll(patientManagementItemsList);
            } else { // Failed or go to end of list
                mManagementItemDatas.addAll(patientManagementItemsList);
            }
            mLoadMoreView.loadMoreComplete();
        }else { // Failed or go to end of list
            mLoadMoreView.closeView();
        }
        updateUI();
    }

    @Override
    public void DisplayMessageForBlockPatient(String message) {

    }

    @Override
    public void DisplayAllAppointmentForPatient(String doctorID, String patientID) {

    }
}
