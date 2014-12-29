package vn.easycare.layers.ui.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
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
import vn.easycare.layers.ui.components.adapters.CommentAdapter;
import vn.easycare.layers.ui.components.data.CommentAndAssessmentItemData;
import vn.easycare.layers.ui.components.views.LoadMoreLayout;
import vn.easycare.layers.ui.presenters.CommentAndAssessmentPresenterImpl;
import vn.easycare.layers.ui.presenters.base.ICommentAndAssessmentPresenter;
import vn.easycare.layers.ui.views.ICommentAndAssessmentView;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.DialogUtil;

/**
 * Created by ThuNguyen on 12/13/2014.
 */
public class CommentFragment extends Fragment implements ICommentAndAssessmentView{
    public static final int COMMENT_NUM_PER_PAGE = 3;
    private int mTotalItemCount = 0;

    // For control, layout
    private ProgressBar mPbLoading;
    private ListView mCommentListView;
    private TextView mTvNoData;
    private CommentAdapter mCommentAdapter;
    private LoadMoreLayout mLoadMoreView;
    private View mRefreshLayout;
    private Dialog mLoadingDialog;

    // For data, object
    private List<CommentAndAssessmentItemData> mCommentAndAssessmentItemDatas;
    private ICommentAndAssessmentPresenter mPresenter;
    private int mPage;
    public CommentFragment(){
        mCommentAndAssessmentItemDatas = new ArrayList<CommentAndAssessmentItemData>();
        mPresenter = new CommentAndAssessmentPresenterImpl(this, getActivity());
        mPage = 1;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comment, container, false);
        mPbLoading = (ProgressBar) v.findViewById(R.id.pbLoading);
        mRefreshLayout = v.findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });
        mCommentListView = (ListView) v.findViewById(R.id.commentListView);
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
        mPresenter.loadCommentAndAssessmentForDoctor( mPage);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mPresenter.loadCommentAndAssessmentForDoctor( mPage);
//            }
//        }, 2000);
    }
    private void updateUI(){
        mPbLoading.setVisibility(View.GONE);
        mCommentListView.setVisibility(View.VISIBLE);
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
        }
        if(mCommentAdapter == null){
            mCommentAdapter = new CommentAdapter(getActivity());
            mCommentAdapter.setCommentClickListener(mCommentClickListener);
            mCommentAdapter.setItemDataList(mCommentAndAssessmentItemDatas);
            mCommentListView.setAdapter(mCommentAdapter);
        }else{
            mCommentAdapter.notifyDataSetChanged();
        }
        if(mCommentAndAssessmentItemDatas.size() == 0){ // No data
            mTvNoData.setVisibility(View.VISIBLE);
        }else{
            mTvNoData.setVisibility(View.GONE);
        }
    }
    @Override
    public void DisplayAllCommentAndAssessmentForDoctor(List<CommentAndAssessmentItemData> commentAndAssessmentItemsList) {

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

    @Override
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

    @Override
    public void DisplayMessageIncaseError(String message) {

    }

    private LoadMoreLayout.ILoadMoreClickListener mOnLoadMoreClickListener = new LoadMoreLayout.ILoadMoreClickListener() {
        @Override
        public void onLoadMoreClicked() {
            loadMoreData();
        }
    };
    private CommentAdapter.ICommentClickListener mCommentClickListener = new CommentAdapter.ICommentClickListener() {
        @Override
        public void onHideTheComment(final String commentId) {
            mLoadingDialog = DialogUtil.createLoadingDialog(getActivity(), getActivity().getString(R.string.loading_dialog_in_progress));
            mLoadingDialog.show();
            mPresenter.HideACommentAndAssessment(commentId);
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    mPresenter.HideACommentAndAssessment(commentId);
//                }
//            }, 2000);

        }
    };
}
