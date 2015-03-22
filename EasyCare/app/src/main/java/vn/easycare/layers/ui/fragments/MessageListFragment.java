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
import vn.easycare.layers.ui.components.adapters.MessagesListAdapter;
import vn.easycare.layers.ui.components.data.MessageManagementItemData;
import vn.easycare.layers.ui.components.data.PatientManagementItemData;
import vn.easycare.layers.ui.components.views.LoadMoreLayout;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.DialogUtil;

/**
 * Created by phannguyen on 3/22/15.
 */
public class MessageListFragment extends BaseFragment{

    private int mTotalItemCount = 0;
    // For control, layout
    private ProgressBar mPbLoading;
    private ListView mMessageListView;
    private TextView mTvNoData;
    private MessagesListAdapter mMessageAdapter;
    private LoadMoreLayout mLoadMoreView;
    private View mRefreshLayout;
    private Dialog mLoadingDialog;

    // For data, object
    private List<MessageManagementItemData> mMessagesItemData;
    //private ICommentAndAssessmentPresenter mPresenter;
    private int mPage;
    public MessageListFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMessagesItemData = new ArrayList<MessageManagementItemData>();
        //mPresenter = new CommentAndAssessmentPresenterImpl(this, getActivity());
        mPage = 1;
        View v = inflater.inflate(R.layout.fragment_messages_list, container, false);
        mPbLoading = (ProgressBar) v.findViewById(R.id.pbLoading);
        mRefreshLayout = v.findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });
        mMessageListView = (ListView) v.findViewById(R.id.messagesListView);
        mTvNoData = (TextView) v.findViewById(R.id.tvNoData);
        mLoadMoreView = new LoadMoreLayout(getActivity());
        mLoadMoreView.setOnLoadMoreClickListener(mOnLoadMoreClickListener);
        mMessageListView.addFooterView(mLoadMoreView);

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
        mMessagesItemData.clear();
        mPbLoading.setVisibility(View.VISIBLE);
        mMessageListView.setVisibility(View.GONE);
        loadData();
    }
    private void refreshData(){
        mPage = 1;
        mMessagesItemData.clear();
        mLoadingDialog = DialogUtil.createLoadingDialog(getActivity(), getString(R.string.loading_dialog_in_progress));
        mLoadingDialog.show();
        loadData();
    }
    /**
     * Reload data to get the newest data after enable "Not-display"
     */
    private void reloadData(){
        mPage = 1;
        mMessagesItemData.clear();
        loadData();
    }
    private void loadData(){
        //mPresenter.loadCommentAndAssessmentForDoctor( mPage);
        final List<MessageManagementItemData> dataList = new ArrayList<MessageManagementItemData>();
        for(int index = 0; index < 10; index++){
            MessageManagementItemData item = new MessageManagementItemData();
            item.setSenderName("Nguyen Van A");
            item.setSentTime("17:00");
            item.setMessageContent("Dau co");
            item.setPlaceExaminationToDoctorName("Nguyen Anh Tuan");
            item.setDepartmentName("Viem co bap");
            //item.setPatientAvatarThumb();
            dataList.add(item);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DisplayAllMessages(dataList);
            }
        }, 2000);

    }
    private void updateUI(){
        mPbLoading.setVisibility(View.GONE);
        mMessageListView.setVisibility(View.VISIBLE);
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
        }
        if(mMessageAdapter == null || mPage == 1){
            mMessageAdapter = new MessagesListAdapter(getActivity());
            mMessageAdapter.setItemDataList(mMessagesItemData);
            mMessageListView.setAdapter(mMessageAdapter);
        }else{
            mMessageAdapter.notifyDataSetChanged();
        }
        if(mMessagesItemData.size() == 0){ // No data
            mTvNoData.setVisibility(View.VISIBLE);
        }else{
            mTvNoData.setVisibility(View.GONE);
        }
    }
    //@Override
    public void DisplayAllMessages(List<MessageManagementItemData> messageItemsList) {

        if(messageItemsList != null && messageItemsList.size() > 0){
            if(mPage == 1){ // Load for first time
                if(mMessagesItemData != null){
                    mMessagesItemData.clear();
                }
                mTotalItemCount = messageItemsList.get(0).getTotalItems();
                mMessagesItemData.addAll(messageItemsList);
            }else{ // Load more here
                mMessagesItemData.addAll(messageItemsList);
            }
            // Decide to hide load more or not
            if(mMessagesItemData.size() == mTotalItemCount){ // End of list
                mLoadMoreView.closeView();
                mMessageListView.removeFooterView(mLoadMoreView);
            }else {
                mMessageListView.removeFooterView(mLoadMoreView);
                mLoadMoreView.loadMoreComplete();
                mMessageListView.addFooterView(mLoadMoreView);
            }
        }else{ // Maybe failed or data is end of list
            mLoadMoreView.closeView();
            mMessageListView.removeFooterView(mLoadMoreView);
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
