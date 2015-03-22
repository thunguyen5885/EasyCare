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

import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gcm.GCMRegistrar;

import vn.easycare.GCMIntentService;
import vn.easycare.R;
import vn.easycare.layers.services.WSDataSingleton;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.activities.LoginActivity;
import vn.easycare.layers.ui.components.singleton.DataSingleton;
import vn.easycare.layers.ui.components.singleton.MySharePreference;
import vn.easycare.utils.AppFnUtils;

public class MenuFragment extends Fragment implements View.OnClickListener{
    public interface IOnMenuItemOnClickListener{
        public void onMenuItemUserClicked();
        public void onMenuItemMessageListClicked();
        public void onMenuItemAppointmentManagementClicked();
        public void onMenuItemCalendarCreatingClicked();
        public void onMenuItemDoctorListClicked();
        public void onMenuItemPatientListClicked();
        public void onMenuItemCommentClicked();
        public void onMenuItemExitClicked();
    }
    // For layout, control
	private View mMenuItemUser;
    private View mMenuItemMessageManagement;
    private View mMenuItemAppointmentManagement;
    private View mMenuItemCalendarCreating;
    private View mMenuItemDoctorList;
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
        mMenuItemMessageManagement = v.findViewById(R.id.menuItemMessageManagement);
        mMenuItemAppointmentManagement = v.findViewById(R.id.menuItemDatingManagement);
        mMenuItemCalendarCreating = v.findViewById(R.id.menuItemCalendarCreating);
        mMenuItemDoctorList = v.findViewById(R.id.menuItemDoctorList);
        mMenuItemPatientList = v.findViewById(R.id.menuItemPatientList);
        mMenuItemComment = v.findViewById(R.id.menuItemComment);
        mMenuItemExit = v.findViewById(R.id.menuItemExit);

        //initMenuItem(mMenuItemUser, 0, );
        //initMenuItem(mMenuItemUser, R.string.menu_test_user, R.drawable.ic_no_avatar);
        initMenuItem(mMenuItemMessageManagement, R.string.menu_message_management, R.drawable.ic_no_avatar);
        initMenuItem(mMenuItemAppointmentManagement, R.string.menu_dating_management, R.drawable.ic_menu_dating_management);
        initMenuItem(mMenuItemCalendarCreating, R.string.menu_calendar_creating, R.drawable.ic_menu_calendar_creating);
        initMenuItem(mMenuItemDoctorList, R.string.menu_doctor_list, R.drawable.ic_no_avatar);
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
    public void showNotify(){
        if(mMenuItemMessageManagement != null && GCMIntentService.mNotifyCount > 0){
            View notifyLayout = mMenuItemMessageManagement.findViewById(R.id.flNotify);
            notifyLayout.setVisibility(View.VISIBLE);
            TextView tvNotify = (TextView) mMenuItemMessageManagement.findViewById(R.id.tvNotifyCount);
            tvNotify.setText(String.valueOf(GCMIntentService.mNotifyCount));
        }
    }
    public void hideNotify(){
        if(mMenuItemMessageManagement != null){
            View notifyLayout = mMenuItemMessageManagement.findViewById(R.id.flNotify);
            notifyLayout.setVisibility(View.INVISIBLE);

            // Also reset notify count
            GCMIntentService.mNotifyCount = 0;
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
                case R.id.menuItemMessageManagement:
                    mMenuItemSeletedItem = mMenuItemMessageManagement;
                    mMenuItemOnClickListener.onMenuItemMessageListClicked();
                    break;
                case R.id.menuItemDatingManagement:
                    mMenuItemSeletedItem = mMenuItemAppointmentManagement;
                    mMenuItemOnClickListener.onMenuItemAppointmentManagementClicked();
                    break;
                case R.id.menuItemCalendarCreating:
                    mMenuItemSeletedItem = mMenuItemCalendarCreating;
                    mMenuItemOnClickListener.onMenuItemCalendarCreatingClicked();
                    break;
                case R.id.menuItemDoctorList:
                    mMenuItemSeletedItem = mMenuItemDoctorList;
                    mMenuItemOnClickListener.onMenuItemDoctorListClicked();
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
        public void onMenuItemMessageListClicked() {
            MessageListFragment messageListFragment = new MessageListFragment();
            ((HomeActivity) getActivity()).showFragmentFromMenu(messageListFragment);
        }

        @Override
        public void onMenuItemAppointmentManagementClicked() {
            hideNotify();
            // Go to dating management screen
            AppointmentListFragment appointmentListFragment = new AppointmentListFragment();
            ((HomeActivity) getActivity()).showFragmentFromMenu(appointmentListFragment);
        }

        @Override
        public void onMenuItemCalendarCreatingClicked() {
            // Go to calendar creating screen
            CalendarCreatingFragment calendarCreatingFragment = new CalendarCreatingFragment();
            ((HomeActivity) getActivity()).showFragmentFromMenu(calendarCreatingFragment);
        }

        @Override
        public void onMenuItemDoctorListClicked() {
            DoctorListFragment doctorListFragment = new DoctorListFragment();
            ((HomeActivity) getActivity()).showFragmentFromMenu(doctorListFragment);
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
            // Clear access token here
            MySharePreference.clearDoctorInfo(getActivity());
            // Unregister registration id
            GCMRegistrar.unregister(getActivity());
            // Logout, exit here
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    };
}