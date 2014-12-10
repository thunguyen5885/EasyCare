package vn.easycare.layers.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import vn.easycare.R;

public class MenuFragment extends Fragment implements View.OnClickListener{
    public interface IOnMenuItemOnClickListener{
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

    // For object
    private IOnMenuItemOnClickListener mOnMenuItemOnClickListener;
    public void setOnMenuItemOnClickListener(IOnMenuItemOnClickListener onMenuItemOnClickListener){
        mOnMenuItemOnClickListener = onMenuItemOnClickListener;
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
		initMenuItemUser(v);
        initMenuItemDatingManagement(v);
        initMenuItemCalendarCreating(v);
        initMenuItemComment(v);
        initMenuItemExit(v);
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
    private void initMenuItemUser(View rootView){
        mMenuItemUser = rootView.findViewById(R.id.menuItemUser);
        View menuItemLayout = mMenuItemUser.findViewById(R.id.menuItemLayout);
        menuItemLayout.setOnClickListener(this);
        menuItemLayout.setTag(R.id.menuItemUser);
        TextView tvTitle = (TextView) mMenuItemUser.findViewById(R.id.tvMenuItemTitle);
        tvTitle.setText("Phan Heo");

        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.text_light_big));
    }
    private void initMenuItemDatingManagement(View rootView){
        mMenuItemDatingManagement = rootView.findViewById(R.id.menuItemDatingManagement);
        View menuItemLayout = mMenuItemDatingManagement.findViewById(R.id.menuItemLayout);
        menuItemLayout.setOnClickListener(this);
        menuItemLayout.setTag(R.id.menuItemDatingManagement);
        TextView tvTitle = (TextView) mMenuItemDatingManagement.findViewById(R.id.tvMenuItemTitle);
        tvTitle.setText(R.string.menu_dating_management);

    }
    private void initMenuItemCalendarCreating(View rootView){
        mMenuItemCalendarCreating = rootView.findViewById(R.id.menuItemCalendarCreating);
        View menuItemLayout = mMenuItemCalendarCreating.findViewById(R.id.menuItemLayout);
        menuItemLayout.setOnClickListener(this);
        menuItemLayout.setTag(R.id.menuItemCalendarCreating);
        TextView tvTitle = (TextView) mMenuItemCalendarCreating.findViewById(R.id.tvMenuItemTitle);
        tvTitle.setText(R.string.menu_calendar_creating);
    }

    private void initMenuItemComment(View rootView){
        mMenuItemComment = rootView.findViewById(R.id.menuItemComment);
        View menuItemLayout = mMenuItemComment.findViewById(R.id.menuItemLayout);
        menuItemLayout.setOnClickListener(this);
        menuItemLayout.setTag(R.id.menuItemComment);
        TextView tvTitle = (TextView) mMenuItemComment.findViewById(R.id.tvMenuItemTitle);
        tvTitle.setText(R.string.menu_comment);
    }
    private void initMenuItemExit(View rootView){
        mMenuItemExit = rootView.findViewById(R.id.menuItemExit);
        View menuItemLayout = mMenuItemExit.findViewById(R.id.menuItemLayout);
        menuItemLayout.setOnClickListener(this);
        menuItemLayout.setTag(R.id.menuItemExit);
        TextView tvTitle = (TextView) mMenuItemExit.findViewById(R.id.tvMenuItemTitle);
        tvTitle.setText(R.string.menu_exit);
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
        // Reset the selected view before
        resetSelectedView();
        int selectedId = (Integer) v.getTag();
        switch (selectedId){
            case R.id.menuItemUser:
                mMenuItemSeletedItem = mMenuItemUser;
                break;
            case R.id.menuItemDatingManagement:
                mMenuItemSeletedItem = mMenuItemDatingManagement;
                break;
            case R.id.menuItemCalendarCreating:
                mMenuItemSeletedItem = mMenuItemCalendarCreating;
                break;
            case R.id.menuItemComment:
                mMenuItemSeletedItem = mMenuItemComment;
                break;
            case R.id.menuItemExit:
                mMenuItemSeletedItem = mMenuItemExit;
                break;
        }
        updateSelectedView();
    }
}