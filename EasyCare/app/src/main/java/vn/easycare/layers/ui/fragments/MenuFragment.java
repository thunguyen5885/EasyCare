package vn.easycare.layers.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.easycare.R;

public class MenuFragment extends Fragment implements View.OnClickListener{
    // For layout, control
	private View mMenuItemUser;
    private View mMenuItemDatingManagement;
    private View mMenuItemCalendarCreating;
    private View mMenuItemAccountManagement;
    private View mMenuItemCommonInfo;
    private View mMenuItemTermOfUse;
    private View mMenuItemPrivacyPolicy;
    private View mMenuItemQA;
    private View mMenuItemContact;
    private View mMenuItemExit;

    private View mMenuItemSeletedItem;

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
        initMenuItemAccountManagement(v);
        initMenuItemCommonInfo(v);
        initMenuItemTermOfUse(v);
        initMenuItemPrivacyPolicy(v);
        initMenuItemQA(v);
        initMenuItemContact(v);
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

    }
    private void initMenuItemDatingManagement(View rootView){
        mMenuItemDatingManagement = rootView.findViewById(R.id.menuItemDatingManagement);
        mMenuItemDatingManagement.setClickable(true);
        mMenuItemDatingManagement.setOnClickListener(this);
        TextView tvTitle = (TextView) mMenuItemDatingManagement.findViewById(R.id.tvMenuItemTitle);
        tvTitle.setText(R.string.menu_dating_management);

    }
    private void initMenuItemCalendarCreating(View rootView){
        mMenuItemCalendarCreating = rootView.findViewById(R.id.menuItemCalendarCreating);
        mMenuItemCalendarCreating.setClickable(true);
        mMenuItemCalendarCreating.setOnClickListener(this);
        TextView tvTitle = (TextView) mMenuItemCalendarCreating.findViewById(R.id.tvMenuItemTitle);
        tvTitle.setText(R.string.menu_calendar_creating);
    }
    private void initMenuItemAccountManagement(View rootView){
        mMenuItemAccountManagement = rootView.findViewById(R.id.menuItemAccountManagement);
        mMenuItemAccountManagement.setClickable(true);
        mMenuItemAccountManagement.setOnClickListener(this);
        TextView tvTitle = (TextView) mMenuItemAccountManagement.findViewById(R.id.tvMenuItemTitle);
        tvTitle.setText(R.string.menu_account_management);
    }
    private void initMenuItemCommonInfo(View rootView){
        mMenuItemCommonInfo = rootView.findViewById(R.id.menuItemCommonInfo);
        mMenuItemCommonInfo.setClickable(true);
        mMenuItemCommonInfo.setOnClickListener(this);
        TextView tvTitle = (TextView) mMenuItemCommonInfo.findViewById(R.id.tvMenuItemTitle);
        tvTitle.setText(R.string.menu_common_info);
    }
    private void initMenuItemTermOfUse(View rootView){
        mMenuItemTermOfUse = rootView.findViewById(R.id.menuItemTermOfUse);
        mMenuItemTermOfUse.setClickable(true);
        mMenuItemTermOfUse.setOnClickListener(this);
        TextView tvTitle = (TextView) mMenuItemTermOfUse.findViewById(R.id.tvMenuItemTitle);
        tvTitle.setText(R.string.menu_term_of_use);
    }
    private void initMenuItemPrivacyPolicy(View rootView){
        mMenuItemPrivacyPolicy = rootView.findViewById(R.id.menuItemPrivacyPolicy);
        mMenuItemPrivacyPolicy.setClickable(true);
        mMenuItemPrivacyPolicy.setOnClickListener(this);
        TextView tvTitle = (TextView) mMenuItemPrivacyPolicy.findViewById(R.id.tvMenuItemTitle);
        tvTitle.setText(R.string.menu_privacy_policy);
    }
    private void initMenuItemQA(View rootView){
        mMenuItemQA = rootView.findViewById(R.id.menuItemQA);
        mMenuItemQA.setClickable(true);
        mMenuItemQA.setOnClickListener(this);
        TextView tvTitle = (TextView) mMenuItemQA.findViewById(R.id.tvMenuItemTitle);
        tvTitle.setText(R.string.menu_qa);
    }
    private void initMenuItemContact(View rootView){
        mMenuItemContact = rootView.findViewById(R.id.menuItemContact);
        mMenuItemContact.setClickable(true);
        mMenuItemContact.setOnClickListener(this);
        TextView tvTitle = (TextView) mMenuItemContact.findViewById(R.id.tvMenuItemTitle);
        tvTitle.setText(R.string.menu_contact);
    }
    private void initMenuItemExit(View rootView){
        mMenuItemExit = rootView.findViewById(R.id.menuItemExit);
        mMenuItemExit.setClickable(true);
        mMenuItemExit.setOnClickListener(this);
        TextView tvTitle = (TextView) mMenuItemExit.findViewById(R.id.tvMenuItemTitle);
        tvTitle.setText(R.string.menu_exit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menuItemUser:
                break;
            case R.id.menuItemDatingManagement:
                break;
            case R.id.menuItemCalendarCreating:
                break;
            case R.id.menuItemAccountManagement:
                break;
            case R.id.menuItemCommonInfo:
                break;
            case R.id.menuItemTermOfUse:
                break;
            case R.id.menuItemPrivacyPolicy:
                break;
            case R.id.menuItemQA:
                break;
            case R.id.menuItemContact:
                break;
            case R.id.menuItemExit:
                break;
        }
    }
}