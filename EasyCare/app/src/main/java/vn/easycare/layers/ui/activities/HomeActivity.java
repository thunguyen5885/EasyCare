package vn.easycare.layers.ui.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.View;

import com.google.android.gcm.GCMRegistrar;

import vn.easycare.R;
import vn.easycare.layers.ui.base.BaseActivity;
import vn.easycare.layers.ui.components.CommonFooter;
import vn.easycare.layers.ui.components.CommonHeader;
import vn.easycare.layers.ui.fragments.AppointmentListFragment;
import vn.easycare.layers.ui.fragments.HomeFragment;
import vn.easycare.layers.ui.fragments.MenuFragment;
import vn.easycare.layers.ui.presenters.LoginPresenterImpl;
import vn.easycare.layers.ui.presenters.base.ILoginPresenter;
import vn.easycare.layers.ui.views.ILoginView;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.DialogUtil;

/**
 * Created by ThuNguyen on 12/8/2014.
 */
public class HomeActivity extends BaseActivity implements ILoginView, CommonHeader.IOnHeaderClickListener{
    // For object
    private CommonHeader mCommonHeader;
    private CommonFooter mCommonFooter;
    private ILoginPresenter mLoginPresenter;
    // For layout, control, view
    private MenuFragment mMenuFragment;
    private View mMainContent;
    private SlidingPaneLayout mSlidingPanelLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        mLoginPresenter = new LoginPresenterImpl(this, this.getApplicationContext());
        // Initialize layout here
        View headerView = findViewById(R.id.header);
        mCommonHeader = new CommonHeader(headerView);
        mCommonHeader.setOnHeaderClickListener(this);

        View footerView = findViewById(R.id.footer);
        mCommonFooter = new CommonFooter(footerView);

        mMenuFragment = (MenuFragment) getFragmentManager().findFragmentById(R.id.menuFragment);

        mSlidingPanelLayout = (SlidingPaneLayout) findViewById(R.id.slidingPanelLayout);
        mSlidingPanelLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View view, float v) {

            }

            @Override
            public void onPanelOpened(View view) {
            }

            @Override
            public void onPanelClosed(View view) {

            }
        });
        mMenuFragment.setSlidingLayout(mSlidingPanelLayout);
        if(mSlidingPanelLayout != null){
            mSlidingPanelLayout.closePane();
        }
        // Check GCM for push notification
        checkToPostRegistrationToServer();

        decideWhichScreenToShow();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        decideWhichScreenToShow();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
        mSlidingPanelLayout.closePane();
    }
    private void decideWhichScreenToShow(){
        boolean isComeFromNotify = getIntent().getBooleanExtra(AppConstants.APPOINTMENT_NOTIFICATION_KEY, false);
        if(isComeFromNotify){ // From notify
            // Show appointment_list screen
            AppointmentListFragment appointmentListFragment = new AppointmentListFragment();
            showFragmentFromMenu(appointmentListFragment);
        }else{
            // Show home fragment as default
            HomeFragment homeFragment = new HomeFragment();
            showFragmentFromMenu(homeFragment);
        }
    }
    public void showFragment(Fragment frag) {
        FragmentManager fragmentManager = getFragmentManager();
        String backStateName = frag.getClass().getName();

        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.setCustomAnimations(
                R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit,
                R.animator.fragment_slide_right_enter,
                R.animator.fragment_slide_right_exit);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.flRightContent, frag);
        fragmentTransaction.commit();

        // Alway close pane layout
        if(mSlidingPanelLayout != null) {
            mSlidingPanelLayout.closePane();
        }
    }

    /**
     * Show fragment from left menu
     *
     * @param frag
     */
    public void showFragmentFromMenu(Fragment frag) {
        FragmentManager fragmentManager = getFragmentManager();
        String backStateName = frag.getClass().getName();

        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        // Decide to show animation or not
        boolean isTransitionFrag = false;
        Fragment curFrag = fragmentManager.findFragmentById(R.id.flRightContent);
        if(curFrag != null && curFrag.getClass().getName().equals(backStateName)) {
            isTransitionFrag = true;
            fragmentTransaction.setCustomAnimations(0,
                    0,
                    R.animator.fragment_slide_right_enter,
                    R.animator.fragment_slide_right_exit);
        }else{
            isTransitionFrag = false;
        }

        boolean fragmentPopped = fragmentManager.popBackStackImmediate(
                backStateName, 0);
        if (!fragmentPopped
                && fragmentManager.findFragmentByTag(backStateName) == null) {
            fragmentTransaction.replace(R.id.flRightContent, frag,
                    backStateName);
            fragmentTransaction.addToBackStack(backStateName);
        } else {
            Fragment currFrag = (Fragment) fragmentManager
                    .findFragmentByTag(backStateName);
            fragmentTransaction.show(currFrag);
        }

        fragmentTransaction.commit();
        if(isTransitionFrag) {
            // Alway close pane layout
            if (mSlidingPanelLayout != null) {
                mSlidingPanelLayout.closePane();
            }
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Alway close pane layout
                    if (mSlidingPanelLayout != null) {
                        mSlidingPanelLayout.closePane();
                    }
                }
            }, 10);
        }
    }
    private void checkToPostRegistrationToServer() {
        // Make sure the device has the proper dependencies.
        GCMRegistrar.checkDevice(this);
        // Make sure the manifest was properly set - comment out this line
        // while developing the app, then uncomment it when it's ready.
        GCMRegistrar.checkManifest(this);

        final String regId = GCMRegistrar.getRegistrationId(this);
        if (regId.equals("")) {
            // Automatically registers application on startup.
            GCMRegistrar.register(this, AppConstants.SENDER_ID);
        } else {
            Log.i("== checkGcm", "else");
            // Device is already registered on GCM, check server.
            if (GCMRegistrar.isRegisteredOnServer(this)) {
                // Skips registration.
            } else {
                Log.i("== checkGcm", "Add device token " + regId);
                mLoginPresenter.DoRegisterDeviceId(regId);
            }
        }
    }
    /**
     * Hide the separator on footer
     */
    public void hideFooterSeparator(){
        if(mCommonFooter != null){
            mCommonFooter.hideIndicator();
        }
    }

    /**
     * Show the separator on footer
     */
    public void showFooterSeparator(){
        if(mCommonFooter != null){
            mCommonFooter.showIndicator();
        }
    }
    public void showHeaderBackButton(){
        if(mCommonHeader != null){
            mCommonHeader.showBackButton();
        }
    }
    public void hideHeaderBackButton(){
        if(mCommonHeader != null){
            mCommonHeader.hideBackButton();
        }
    }
    @Override
    public void onMenuClicked() {
        if(mSlidingPanelLayout != null){
            if(mSlidingPanelLayout.isOpen()){
                mSlidingPanelLayout.closePane();
            }else{
                mSlidingPanelLayout.openPane();
            }
        }
    }

    @Override
    public void onBack() {
        onBackPressed();
    }

    @Override
    public void LoginOK(String message) {
        //do nothing
    }

    @Override
    public void LoginFail(String message) {
        //do nothing
    }

    @Override
    public void RegisterGCMIdOK(String message) {
        GCMRegistrar.setRegisteredOnServer(this, true);
    }

    @Override
    public void DisplayMessageIncaseError(String message, String funcTitle) {
        DialogUtil.createInformDialog(this, funcTitle, message,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }
}
