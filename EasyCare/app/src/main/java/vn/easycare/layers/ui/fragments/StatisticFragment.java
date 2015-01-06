package vn.easycare.layers.ui.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.data.InformationStatisticItemData;
import vn.easycare.layers.ui.presenters.InformationStatisticPresenterImpl;
import vn.easycare.layers.ui.presenters.base.IInformationStatisticPresenter;
import vn.easycare.layers.ui.views.IInformationStatisticView;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.DialogUtil;

/**
 * Created by ThuNguyen on 12/13/2014.
 */
public class StatisticFragment extends Fragment implements IInformationStatisticView{


    public interface IStatisticOnClickListener{
        public void onSeeWatingList();
    }
    // For control, layout
    private View mCommonCommentDetailInfoLayout;
    private View mCommonCommentOrderCountLayout;
    private View mCommonCommentWaitingDatingLayout;
    private View mUserCommentCommonCommentLayout;
    private View mUserCommentWaitingCommentLayout;
    private View mUserCommentAssetCommentLayout;
    private View mStatisticLayout;
    private View mRefreshLayout;
    private ProgressBar mPbLoading;
    private Dialog mLoadingDialog;

    // For data, object
    private InformationStatisticItemData mItemData;
    private IInformationStatisticPresenter mPresenter;

    public StatisticFragment(){
        mPresenter = new InformationStatisticPresenterImpl(this, getActivity());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_statistic, container, false);
        mStatisticLayout = v.findViewById(R.id.mainStatisticLayout);

        mPbLoading = (ProgressBar) v.findViewById(R.id.pbLoading);
        mCommonCommentDetailInfoLayout = v.findViewById(R.id.statisticDetailInfoLayout);
        mCommonCommentOrderCountLayout = v.findViewById(R.id.statisticOrderCountLayout);
        mCommonCommentWaitingDatingLayout = v.findViewById(R.id.statisticWaitingDatingLayout);
        mUserCommentCommonCommentLayout = v.findViewById(R.id.statisticCommonCommentLayout);
        mUserCommentWaitingCommentLayout = v.findViewById(R.id.statisticWaitingCommentLayout);
        mUserCommentAssetCommentLayout = v.findViewById(R.id.statisticAssetCommentLayout);
        mRefreshLayout = v.findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });
        // Init view for common info
        initViewForCommonInfo(mCommonCommentDetailInfoLayout, R.drawable.ic_detail_info, R.string.statistic_detail_info);
        initViewForCommonInfo(mCommonCommentOrderCountLayout, R.drawable.ic_seat_count, R.string.statistic_order_count);
        initViewForCommonInfo(mCommonCommentWaitingDatingLayout, R.drawable.ic_waiting_for_approved, R.string.statistic_waiting_dating);

        // Init view for user comment
        initViewForUserComment(mUserCommentCommonCommentLayout, R.drawable.ic_common_comment, R.string.statistic_common_comment);
        initViewForUserComment(mUserCommentWaitingCommentLayout, R.drawable.ic_comment_about_waiting_time, R.string.statistic_waiting_comment);
        initViewForUserComment(mUserCommentAssetCommentLayout, R.drawable.ic_comment_about_assets, R.string.statistic_asset_comment);

        // Apply font
        AppFnUtils.applyFontForTextViewChild(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initViewForCommonInfo(View parentView, int posterId, int titleId){
        ImageView ivPoster = (ImageView) parentView.findViewById(R.id.ivCommonCommentPoster);
        ivPoster.setImageResource(posterId);
        TextView tvTitle = (TextView) parentView.findViewById(R.id.tvCommonCommentTitle);
        tvTitle.setText(titleId);
        TextView tvSeeMore = (TextView) parentView.findViewById(R.id.tvCommonCommentSeeMore);
        tvSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnStatisticOnClickListener.onSeeWatingList();
            }
        });
    }
    private void getData(){
        mPbLoading.setVisibility(View.VISIBLE);
        mStatisticLayout.setVisibility(View.GONE);
        mPresenter = new InformationStatisticPresenterImpl(this, getActivity());
        mPresenter.loadAllInfoStatisticForDoctor();

    }
    private void refreshData(){
        mLoadingDialog = DialogUtil.createLoadingDialog(getActivity(), getString(R.string.loading_dialog_in_progress));
        mLoadingDialog.show();
        if(mPresenter == null){
            mPresenter = new InformationStatisticPresenterImpl(this, getActivity());
        }
        mPresenter.loadAllInfoStatisticForDoctor();
    }
    private void initViewForUserComment(View parentView, int posterId, int titleId){
        ImageView ivPoster = (ImageView) parentView.findViewById(R.id.ivUserCommentPoster);
        ivPoster.setImageResource(posterId);
        TextView tvTitle = (TextView) parentView.findViewById(R.id.tvUserCommentTitle);
        tvTitle.setText(titleId);
    }
    private void updateViewForCommonInfo(View parentView, int value){
        TextView tvValue = (TextView) parentView.findViewById(R.id.tvCommonCommentContent);
        tvValue.setText(String.valueOf(value));

        if(parentView.getId() == R.id.statisticWaitingDatingLayout){
            if(value > 0){
                TextView tvSeeMore = (TextView) parentView.findViewById(R.id.tvCommonCommentSeeMore);
                tvSeeMore.setVisibility(View.VISIBLE);
            }
        }
    }
    private void updateViewForUserComment(View parentView, int commentCount, float rating){
        TextView tvCommentCount = (TextView) parentView.findViewById(R.id.tvUserCommentCommentCount);
        tvCommentCount.setText(String.valueOf(commentCount));
        TextView tvRating = (TextView) parentView.findViewById(R.id.tvUserCommentAverageRate);
        tvRating.setText(String.valueOf(rating));
    }
    private void updateData(){
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
        }
        if(mItemData != null) {
            mStatisticLayout.setVisibility(View.VISIBLE);
            mPbLoading.setVisibility(View.GONE);

            updateViewForCommonInfo(mCommonCommentDetailInfoLayout, mItemData.getDetailViewCount());
            updateViewForCommonInfo(mCommonCommentOrderCountLayout, mItemData.getOrderedCount());
            updateViewForCommonInfo(mCommonCommentWaitingDatingLayout, mItemData.getExaminationPendingCount());
            updateViewForUserComment(mUserCommentCommonCommentLayout, mItemData.getGeneralCommentCount(), mItemData.getGeneralAverageRate());
            updateViewForUserComment(mUserCommentWaitingCommentLayout, mItemData.getWaitingTimeCommentCount(), mItemData.getWaitingTimeAverageRate());
            updateViewForUserComment(mUserCommentAssetCommentLayout, mItemData.getFacilityCommentCount(), mItemData.getFacilityAverageRate());
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            ((HomeActivity) getActivity()).showFooterSeparator();
            ((HomeActivity) getActivity()).showHeaderBackButton();
        }
    }

    private IStatisticOnClickListener mOnStatisticOnClickListener = new IStatisticOnClickListener() {
        @Override
        public void onSeeWatingList() {
            // Later......
            // Go to dating list
            AppointmentListFragment appointmentListFragment = new AppointmentListFragment();
            ((HomeActivity) getActivity()).showFragment(appointmentListFragment);
        }
    };

    @Override
    public void DisplayAllInfoStatisticForDoctor(InformationStatisticItemData InfoStatisticItem) {
        mItemData = InfoStatisticItem;
        if(mItemData != null){
            updateData();
        }
    }

    @Override
    public void DisplayMessageIncaseError(String message,String funcTitle) {

        DialogUtil.createInformDialog(this.getActivity(), funcTitle, message,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }
}
