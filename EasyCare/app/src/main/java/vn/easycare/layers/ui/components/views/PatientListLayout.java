package vn.easycare.layers.ui.components.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.components.adapters.PatientListAdapter;
import vn.easycare.layers.ui.components.adapters.PatientListPagerAdapter;
import vn.easycare.layers.ui.components.data.PatientManagementItemData;
import vn.easycare.layers.ui.presenters.PatientManagementPresenterImpl;
import vn.easycare.layers.ui.presenters.base.IPatientManagementPresenter;
import vn.easycare.layers.ui.views.IPatientManagementView;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.DialogUtil;

/**
 * Created by ThuNguyen on 12/16/2014.
 */
public class PatientListLayout extends FrameLayout implements IPatientManagementView{
    private LayoutInflater mLayoutInflater;
    private LoadMoreLayout mLoadMoreView;
    private ListView mPatientListView;
    private ProgressBar mPbLoading;
    private TextView mTvNoData;

    // For data object
    private PatientListAdapter mAdapter;
    private List<PatientManagementItemData> mManagementItemDatas;
    private IPatientManagementPresenter mPresenter;
    private PatientListPagerAdapter.IPatientListBroadcastListener mPatientListBroadcastListener;
    private Dialog mLoadingDialog;
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
        mTvNoData = (TextView) v.findViewById(R.id.tvNoData);

        mLoadMoreView = new LoadMoreLayout(context);
        mLoadMoreView.setOnLoadMoreClickListener(mOnLoadMoreClickListener);
        mPatientListView.addFooterView(mLoadMoreView);

        // Apply font
        AppFnUtils.applyFontForTextViewChild(v);
        addView(v);
    }
    public void setBlackList(boolean isBlackList){
        mIsBlackList = isBlackList;
    }
    public void setPatientListBroadcastListener(PatientListPagerAdapter.IPatientListBroadcastListener patientListBroadcastListener){
        mPatientListBroadcastListener = patientListBroadcastListener;
    }
    public void loadNewData(){
        mPage = 1;
        mManagementItemDatas.clear();
        mPbLoading.setVisibility(View.VISIBLE);
        mPatientListView.setVisibility(View.GONE);

        loadData();
    }
    public void refreshData(){
        mPage = 1;
        mManagementItemDatas.clear();

        loadData();
    }
    private void loadMoreData(){
        mPage ++;
        mLoadMoreView.beginLoading();
        loadData();
    }

    private void loadData(){
        // Delay 2s(just for fake data)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mIsBlackList){
                    mPresenter.loadAllBlockedPatientsForDoctor(mPage);
                }else{
                    mPresenter.loadAllAvailablePatientsForDoctor(mPage);
                }
            }
        }, 2000);

    }
    public void updateUI(boolean isEndOfList){
        mPbLoading.setVisibility(View.GONE);
        mPatientListView.setVisibility(View.VISIBLE);
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
        }
        if(mAdapter == null){
            mAdapter = new PatientListAdapter(getContext());
            mAdapter.setBlackList(mIsBlackList);
            mAdapter.setIsEndOfList(isEndOfList);
            mAdapter.setPatientListClickListener(mOnPatientListClickListener);
            mAdapter.setItemDataList(mManagementItemDatas);
            mPatientListView.setAdapter(mAdapter);
        }else{
            mAdapter.setIsEndOfList(isEndOfList);
            mAdapter.notifyDataSetChanged();
        }
        if(mManagementItemDatas.size() == 0){
            mTvNoData.setVisibility(VISIBLE);
        }else{
            mTvNoData.setVisibility(GONE);
        }
    }
    private LoadMoreLayout.ILoadMoreClickListener mOnLoadMoreClickListener = new LoadMoreLayout.ILoadMoreClickListener() {
        @Override
        public void onLoadMoreClicked() {
            loadMoreData();
        }
    };
    private PatientListAdapter.IPatientListClickListener mOnPatientListClickListener = new PatientListAdapter.IPatientListClickListener() {
        @Override
        public void onBlockClicked(final String patientId) {
            mLoadingDialog = DialogUtil.createLoadingDialog(getContext(), getResources().getString(R.string.loading_dialog_in_progress));
            mLoadingDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPresenter.blockAPatient(patientId);
                }
            }, 2000);

        }

        @Override
        public void onUnblockClicked(final String patientId) {
            mLoadingDialog = DialogUtil.createLoadingDialog(getContext(), getResources().getString(R.string.loading_dialog_in_progress));
            mLoadingDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPresenter.unblockAPatient(patientId);
                }
            }, 2000);
        }
    };
    @Override
    public void DisplayAllAvailablePatientsForDoctor(List<PatientManagementItemData> patientManagementItemsList) {

        boolean isEndOfList = false;
        if(patientManagementItemsList != null && patientManagementItemsList.size() > 0) {
            if (mPage == 1) {
                if (mManagementItemDatas != null) {
                    mManagementItemDatas.clear();
                }
                mManagementItemDatas.addAll(patientManagementItemsList);
            } else { // Failed or go to end of list
                mManagementItemDatas.addAll(patientManagementItemsList);
            }
            isEndOfList = false;
            //mPatientListView.removeFooterView();
            mPatientListView.removeFooterView(mLoadMoreView);
            mLoadMoreView.loadMoreComplete();
            mPatientListView.addFooterView(mLoadMoreView);
        }else { // Failed or go to end of list
            isEndOfList = true;
            mLoadMoreView.closeView();
            mPatientListView.removeFooterView(mLoadMoreView);
        }
        updateUI(isEndOfList);
    }

    @Override
    public void DisplayAllBlockedPatientsForDoctor(List<PatientManagementItemData> patientManagementItemsList) {
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
        }
        boolean isEndOfList = false;
        if(patientManagementItemsList != null && patientManagementItemsList.size() > 0) {
            if (mPage == 1) {
                if (mManagementItemDatas != null) {
                    mManagementItemDatas.clear();
                }
                mManagementItemDatas.addAll(patientManagementItemsList);
            } else { // Failed or go to end of list
                mManagementItemDatas.addAll(patientManagementItemsList);
            }
            isEndOfList = false;
            mPatientListView.removeFooterView(mLoadMoreView);
            mLoadMoreView.loadMoreComplete();
            mPatientListView.addFooterView(mLoadMoreView);
        }else { // Failed or go to end of list
            isEndOfList = true;
            mLoadMoreView.closeView();
            mPatientListView.removeFooterView(mLoadMoreView);
        }
        updateUI(isEndOfList);
    }

    @Override
    public void DisplayMessageForBlockPatient(String message) {
        boolean isUpdatedDone = true;
        if(isUpdatedDone){
            refreshData();

            // Also update on blacklist tab
            if(mPatientListBroadcastListener != null){
                mPatientListBroadcastListener.onUpdateData(true);
            }
        }else{
            if(mLoadingDialog != null){
                mLoadingDialog.dismiss();
            }
        }
    }

    @Override
    public void DisplayMessageForUnblockPatient(String message) {
        boolean isUpdatedDone = true;
        if(isUpdatedDone){
            refreshData();

            // Also update on patient list tab
            if(mPatientListBroadcastListener != null){
                mPatientListBroadcastListener.onUpdateData(false);
            }
        }else{
            if(mLoadingDialog != null){
                mLoadingDialog.dismiss();
            }
        }
    }

    @Override
    public void DisplayAllAppointmentForPatient(String patientID) {

    }

    @Override
    public void DisplayMessageIncaseError(String message) {

    }
}
