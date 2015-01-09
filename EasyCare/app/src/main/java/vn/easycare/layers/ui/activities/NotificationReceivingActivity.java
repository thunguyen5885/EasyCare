package vn.easycare.layers.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import vn.easycare.layers.ui.base.BaseActivity;
import vn.easycare.utils.AppConstants;


public class NotificationReceivingActivity extends BaseActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		navigateToAppointmentListScreen();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		navigateToAppointmentListScreen();
	}
	private void navigateToAppointmentListScreen(){
        // Go to home activity with appointment_list fragment
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(AppConstants.APPOINTMENT_NOTIFICATION_KEY, true);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
	}
}
