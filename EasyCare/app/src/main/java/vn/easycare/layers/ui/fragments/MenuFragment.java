package vn.easycare.layers.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import vn.easycare.R;
import vn.easycare.layers.services.WSDataSingleton;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.activities.LoginActivity;
import vn.easycare.layers.ui.components.singleton.DataSingleton;
import vn.easycare.utils.AppFnUtils;

public class MenuFragment extends Fragment implements View.OnClickListener{
    public interface IOnMenuItemOnClickListener{
        public void onMenuItemUserClicked();
        public void onMenuItemDatingManagementClicked();
        public void onMenuItemCalendarCreatingClicked();
        public void onMenuItemPatientListClicked();
        public void onMenuItemCommentClicked();
        public void onMenuItemExitClicked();
    }
    // For layout, control
	private View mMenuItemUser;
    private View mMenuItemDatingManagement;
    private View mMenuItemCalendarCreating;
    private View mMenuItemPatientList;
    private View mMenuItemComment;
    private View mMenuItemExit;
    private View mMenuItemSeletedItem;

    // For flags
    private boolean mIsItemClicked = false;
    // For object
    private SlidingPaneLayout mSlidingLayout;
    public void setSlidingLayout(SlidingPaneLayout slidingLayout){
        mSlidingLayout = slidingLayout;
    }
    public MenuFragment(){

    }
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		
		View v = inflater.inflate(R.layout.fragment_menu, container, false);
		mMenuItemUser = v.findViewById(R.id.menuItemUser);
        mMenuItemDatingManagement = v.findViewById(R.id.menuItemDatingManagement);
        mMenuItemCalendarCreating = v.findViewById(R.id.menuItemCalendarCreating);
        mMenuItemPatientList = v.findViewById(R.id.menuItemPatientList);
        mMenuItemComment = v.findViewById(R.id.menuItemComment);
        mMenuItemExit = v.findViewById(R.id.menuItemExit);

        //initMenuItem(mMenuItemUser, 0, );
        //initMenuItem(mMenuItemUser, R.string.menu_test_user, R.drawable.ic_no_avatar);
        initMenuItem(mMenuItemDatingManagement, R.string.menu_dating_management, R.drawable.ic_menu_dating_management);
        initMenuItem(mMenuItemCalendarCreating, R.string.menu_calendar_creating, R.drawable.ic_menu_calendar_creating);
        initMenuItem(mMenuItemPatientList, R.string.menu_patient_list, R.drawable.ic_patient_list);
        initMenuItem(mMenuItemComment, R.string.menu_comment, R.drawable.ic_menu_comment);
        initMenuItem(mMenuItemExit, R.string.menu_exit, R.drawable.ic_menu_exit);

        showUserMenuItem(mMenuItemUser);
        // Apply font
        AppFnUtils.applyFontForTextViewChild(v);
		return v;
	}
	/*ThuNguyen Add 20141022 Start*/

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
    private void showUserMenuItem(View rootView){
        View menuItemLayout = rootView.findViewById(R.id.menuItemLayout);
        TextView tvUserName = (TextView) rootView.findViewById(R.id.tvMenuItemTitle);
        tvUserName.setText(WSDataSingleton.getInstance(getActivity()).getDoctorFullName());
        NetworkImageView ivPoster = (NetworkImageView) rootView.findViewById(R.id.ivMenuItemPoster);
        ivPoster.setDefaultImageResId(R.drawable.ic_no_avatar);
        ivPoster.setImageUrl(WSDataSingleton.getInstance(getActivity()).getDoctorAvatarThumb(), DataSingleton.getInstance(getActivity()).getImageLoader());

    }
    private void initMenuItem(View rootView, int titleId, int posterId){
        View menuItemLayout = rootView.findViewById(R.id.menuItemLayout);
        menuItemLayout.setOnClickListener(this);
        menuItemLayout.setTag(rootView.getId());
        TextView tvTitle = (TextView) rootView.findViewById(R.id.tvMenuItemTitle);
        tvTitle.setText(titleId);
        NetworkImageView tvPoster = (NetworkImageView) rootView.findViewById(R.id.ivMenuItemPoster);
        tvPoster.setDefaultImageResId(posterId);
    }

    /**
     * Update background, text color for selected view
     */
    private void updateSelectedView(){
        if(mMenuItemSeletedItem != null){
            mMenuItemSeletedItem.setBackgroundColor(getResources().getColor(R.color.menu_item_selected_background_color));
            TextView tvTitle = (TextView) mMenuItemSeletedItem.findViewById(R.id.tvMenuItemTitle);
            tvTitle.setTextColor(getResources().getColor(R.color.white));
        }
    }
    private void resetSelectedView(){
        if(mMenuItemSeletedItem != null){
            mMenuItemSeletedItem.setBackgroundColor(Color.TRANSPARENT);
            TextView tvTitle = (TextView) mMenuItemSeletedItem.findViewById(R.id.tvMenuItemTitle);
            tvTitle.setTextColor(getResources().getColor(R.color.textview_color_grey));
        }
    }
    @Override
    public void onClick(View v) {
        if(mSlidingLayout != null && mSlidingLayout.isOpen() &&
                !mIsItemClicked) {
            // Not permit 2 items run at the same time
            mIsItemClicked = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                   mIsItemClicked = false;
                }
            }, 500);
            // Reset the selected view before
            resetSelectedView();
            int selectedId = (Integer) v.getTag();
            switch (selectedId) {
                case R.id.menuItemUser:
                    mMenuItemSeletedItem = mMenuItemUser;
                    mMenuItemOnClickListener.onMenuItemUserClicked();
                    break;
                case R.id.menuItemDatingManagement:
                    mMenuItemSeletedItem = mMenuItemDatingManagement;
                    mMenuItemOnClickListener.onMenuItemDatingManagementClicked();
                    break;
                case R.id.menuItemCalendarCreating:
                    mMenuItemSeletedItem = mMenuItemCalendarCreating;
                    mMenuItemOnClickListener.onMenuItemCalendarCreatingClicked();
                    break;
                case R.id.menuItemPatientList:
                    mMenuItemSeletedItem = mMenuItemPatientList;
                    mMenuItemOnClickListener.onMenuItemPatientListClicked();
                    break;
                case R.id.menuItemComment:
                    mMenuItemSeletedItem = mMenuItemComment;
                    mMenuItemOnClickListener.onMenuItemCommentClicked();
                    break;
                case R.id.menuItemExit:
                    mMenuItemSeletedItem = mMenuItemExit;
                    mMenuItemOnClickListener.onMenuItemExitClicked();
                    break;
            }
            updateSelectedView();
        }
    }
    private IOnMenuItemOnClickListener mMenuItemOnClickListener = new IOnMenuItemOnClickListener() {
        @Override
        public void onMenuItemUserClicked() {
            // Go to user info screen
        }

        @Override
        public void onMenuItemDatingManagementClicked() {
            // Go to dating management screen
            DatingListFragment datingListFragment = new DatingListFragment();
            ((HomeActivity) getActivity()).showFragmentFromMenu(datingListFragment);
        }

        @Override
        public void onMenuItemCalendarCreatingClicked() {
            // Go to calendar creating screen
            DateCreatingFragment dateCreatingFragment = new DateCreatingFragment();
            ((HomeActivity) getActivity()).showFragmentFromMenu(dateCreatingFragment);
        }

        @Override
        public void onMenuItemPatientListClicked() {
            PatientListFragment patientListFragment = new PatientListFragment();
            ((HomeActivity) getActivity()).showFragmentFromMenu(patientListFragment);
        }

        @Override
        public void onMenuItemCommentClicked() {
            // Go to comment screen
            CommentFragment commentFragment = new CommentFragment();
            ((HomeActivity) getActivity()).showFragmentFromMenu(commentFragment);
        }

        @Override
        public void onMenuItemExitClicked() {
            // Logout, exit here
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    };
}