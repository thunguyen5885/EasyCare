package vn.easycare.layers.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

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

/**
 * Created by ThuNguyen on 12/13/2014.
 */
public class CommentFragment extends Fragment implements ICommentAndAssessmentView{
    public static final int COMMENT_NUM_PER_PAGE = 3;
    private int mTotalItemCount = 0;

    // For control, layout
    private ProgressBar mPbLoading;
    private ListView mCommentListView;
    private CommentAdapter mCommentAdapter;
    private LoadMoreLayout mLoadMoreView;

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
        mCommentListView = (ListView) v.findViewById(R.id.commentListView);
        mLoadMoreView = new LoadMoreLayout(getActivity());
        mLoadMoreView.setOnLoadMoreClickListener(mOnLoadMoreClickListener);
        mCommentListView.addFooterView(mLoadMoreView);

        // Apply font
        AppFnUtils.applyFontForTextViewChild(v, null);
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
    private void loadData(){
        mPresenter.loadCommentAndAssessmentForDoctor(null, mPage);
    }
    private void updateUI(){
        mPbLoading.setVisibility(View.GONE);
        mCommentListView.setVisibility(View.VISIBLE);
        if(mCommentAdapter == null){
            mCommentAdapter = new CommentAdapter(getActivity());
            mCommentAdapter.setItemDataList(mCommentAndAssessmentItemDatas);
            mCommentListView.setAdapter(mCommentAdapter);
        }else{
            mCommentAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void DisplayAllCommentAndAssessmentForDoctor(List<CommentAndAssessmentItemData> commentAndAssessmentItemsList) {
        if(commentAndAssessmentItemsList != null && commentAndAssessmentItemsList.size() > 0){
            if(mPage == 1){ // Load for first time
                if(mCommentAndAssessmentItemDatas != null){
                    mCommentAndAssessmentItemDatas.clear();
                }
                mCommentAndAssessmentItemDatas.addAll(commentAndAssessmentItemsList);
            }else{ // Load more here
                mCommentAndAssessmentItemDatas.addAll(commentAndAssessmentItemsList);
            }
            mLoadMoreView.loadMoreComplete();
        }else{ // Maybe failed or data is end of list
            mLoadMoreView.closeView();
        }
        // Update UI anyway
        updateUI();
    }

    @Override
    public void DisplayMessageForHideCommentAndAssessment(String message) {

    }
    private LoadMoreLayout.ILoadMoreClickListener mOnLoadMoreClickListener = new LoadMoreLayout.ILoadMoreClickListener() {
        @Override
        public void onLoadMoreClicked() {
            loadMoreData();
        }
    };
}
