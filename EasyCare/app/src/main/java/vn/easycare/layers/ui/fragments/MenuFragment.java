package vn.easycare.layers.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
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
import vn.easycare.layers.ui.activities.HomeActivity;

public class MenuFragment extends Fragment implements View.OnClickListener{
    public interface IOnMenuItemOnClickListener{
        public void onMenuItemUserClicked();
        public void onMenuItemDatingManagementClicked();
        public void onMenuItemCalendarCreatingClicked();
        public void onMenuItemCommentClicked();
        public void onMenuItemExitClicked();
    }
    // For layout, control
	private View mMenuItemUser;
    private View mMenuItemDatingManagement;
    private View mMenuItemCalendarCreating;
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
        mMenuItemComment = v.findViewById(R.id.menuItemComment);
        mMenuItemExit = v.findViewById(R.id.menuItemExit);

        //initMenuItem(mMenuItemUser, 0, );
        initMenuItem(mMenuItemDatingManagement, R.string.menu_dating_management, R.drawable.ic_menu_dating_management);
        initMenuItem(mMenuItemCalendarCreating, R.string.menu_calendar_creating, R.drawable.ic_menu_calendar_creating);
        initMenuItem(mMenuItemComment, R.string.menu_comment, R.drawable.ic_menu_comment);
        initMenuItem(mMenuItemExit, R.string.menu_exit, R.drawable.ic_menu_exit);
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

        }

        @Override
        public void onMenuItemCalendarCreatingClicked() {
            // Go to calendar creating screen
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
        }
    };
}