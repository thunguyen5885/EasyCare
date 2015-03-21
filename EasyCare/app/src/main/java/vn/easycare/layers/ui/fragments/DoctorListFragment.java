package vn.easycare.layers.ui.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.base.BaseFragment;
import vn.easycare.layers.ui.components.adapters.DoctorListAdapter;
import vn.easycare.layers.ui.components.data.PatientManagementItemData;
import vn.easycare.layers.ui.components.views.LoadMoreLayout;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.DialogUtil;

/**
 * Created by ThuNguyen on 12/13/2014.
 */
public class DoctorListFragment extends BaseFragment{
    private int mTotalItemCount = 0;

    // For control, layout
    private ProgressBar mPbLoading;
    private ListView mCommentListView;
    private TextView mTvNoData;
    private DoctorListAdapter mDoctorAdapter;
    private LoadMoreLayout mLoadMoreView;
    private View mRefreshLayout;
    private Dialog mLoadingDialog;

    // For data, object
    private List<PatientManagementItemData> mCommentAndAssessmentItemDatas;
    //private ICommentAndAssessmentPresenter mPresenter;
    private int mPage;
    public DoctorListFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mCommentAndAssessmentItemDatas = new ArrayList<PatientManagementItemData>();
        //mPresenter = new CommentAndAssessmentPresenterImpl(this, getActivity());
        mPage = 1;
        View v = inflater.inflate(R.layout.fragment_doctor_list, container, false);
        mPbLoading = (ProgressBar) v.findViewById(R.id.pbLoading);
        mRefreshLayout = v.findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });
        mCommentListView = (ListView) v.findViewById(R.id.doctorListView);
        mTvNoData = (TextView) v.findViewById(R.id.tvNoData);
        mLoadMoreView = new LoadMoreLayout(getActivity());
        mLoadMoreView.setOnLoadMoreClickListener(mOnLoadMoreClickListener);
        mCommentListView.addFooterView(mLoadMoreView);

        // Apply font
        AppFnUtils.applyFontForTextViewChild(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadNewData();
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

    private void loadMoreData(){
        mPage ++;
        mLoadMoreView.beginLoading();
        loadData();
    }
    private void loadNewData(){
        mPage = 1;
        mCommentAndAssessmentItemDatas.clear();
        mPbLoading.setVisibility(View.VISIBLE);
        mCommentListView.setVisibility(View.GONE);
        loadData();
    }
    private void refreshData(){
        mPage = 1;
        mCommentAndAssessmentItemDatas.clear();
        mLoadingDialog = DialogUtil.createLoadingDialog(getActivity(), getString(R.string.loading_dialog_in_progress));
        mLoadingDialog.show();
        loadData();
    }
    /**
     * Reload data to get the newest data after enable "Not-display"
     */
    private void reloadData(){
        mPage = 1;
        mCommentAndAssessmentItemDatas.clear();
        loadData();
    }
    private void loadData(){
        //mPresenter.loadCommentAndAssessmentForDoctor( mPage);
        final List<PatientManagementItemData> dataList = new ArrayList<PatientManagementItemData>();
        for(int index = 0; index < 10; index++){
            PatientManagementItemData item = new PatientManagementItemData();
            item.setPatientName("Nguyen Van A");
            item.setPatientPhoneNumber("0455999000");
            item.setPatientEmailAddress("support@easycare.vn");
            //item.setPatientAvatarThumb();
            dataList.add(item);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DisplayAllCommentAndAssessmentForDoctor(dataList);
            }
        }, 2000);

    }
    private void updateUI(){
        mPbLoading.setVisibility(View.GONE);
        mCommentListView.setVisibility(View.VISIBLE);
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
        }
        if(mDoctorAdapter == null || mPage == 1){
            mDoctorAdapter = new DoctorListAdapter(getActivity());
            mDoctorAdapter.setItemDataList(mCommentAndAssessmentItemDatas);
            mCommentListView.setAdapter(mDoctorAdapter);
        }else{
            mDoctorAdapter.notifyDataSetChanged();
        }
        if(mCommentAndAssessmentItemDatas.size() == 0){ // No data
            mTvNoData.setVisibility(View.VISIBLE);
        }else{
            mTvNoData.setVisibility(View.GONE);
        }
    }
    //@Override
    public void DisplayAllCommentAndAssessmentForDoctor(List<PatientManagementItemData> commentAndAssessmentItemsList) {

        if(commentAndAssessmentItemsList != null && commentAndAssessmentItemsList.size() > 0){
            if(mPage == 1){ // Load for first time
                if(mCommentAndAssessmentItemDatas != null){
                    mCommentAndAssessmentItemDatas.clear();
                }
                mTotalItemCount = commentAndAssessmentItemsList.get(0).getTotalItems();
                mCommentAndAssessmentItemDatas.addAll(commentAndAssessmentItemsList);
            }else{ // Load more here
                mCommentAndAssessmentItemDatas.addAll(commentAndAssessmentItemsList);
            }
            // Decide to hide load more or not
            if(mCommentAndAssessmentItemDatas.size() == mTotalItemCount){ // End of list
                mLoadMoreView.closeView();
                mCommentListView.removeFooterView(mLoadMoreView);
            }else {
                mCommentListView.removeFooterView(mLoadMoreView);
                mLoadMoreView.loadMoreComplete();
                mCommentListView.addFooterView(mLoadMoreView);
            }
        }else{ // Maybe failed or data is end of list
            mLoadMoreView.closeView();
            mCommentListView.removeFooterView(mLoadMoreView);
        }
        // Update UI anyway
        updateUI();
    }

    //@Override
    public void DisplayMessageForHideCommentAndAssessment(String message) {
        boolean mIsUpdatedDone = true;
        if(mIsUpdatedDone) {
            // Load new data
            reloadData();
        }else{
            if(mLoadingDialog != null){
                mLoadingDialog.dismiss();
            }
        }
    }

    //@Override
    public void DisplayMessageIncaseError(String message,String funcTitle) {

        DialogUtil.createInformDialog(this.getActivity(), funcTitle, message,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    private LoadMoreLayout.ILoadMoreClickListener mOnLoadMoreClickListener = new LoadMoreLayout.ILoadMoreClickListener() {
        @Override
        public void onLoadMoreClicked() {
            loadMoreData();
        }
    };
}
