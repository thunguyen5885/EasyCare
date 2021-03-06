/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vn.easycare;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
import com.google.android.gcm.GCMRegistrar;

import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.activities.NotificationReceivingActivity;
import vn.easycare.layers.ui.presenters.LoginPresenterImpl;
import vn.easycare.layers.ui.presenters.base.ILoginPresenter;
import vn.easycare.layers.ui.views.ILoginView;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;

/**
 * IntentService responsible for handling GCM messages.
 */
public class GCMIntentService extends GCMBaseIntentService{

    @SuppressWarnings("hiding")
    private static final String TAG = "GCMIntentService";
    public static int mNotifyCount = 0;
    private Context mContext;
    public GCMIntentService() {
        super(AppConstants.SENDER_ID);
        
    }

    @Override
    protected void onRegistered(Context context, String registrationId) {
        Log.e(TAG, "Device registered: regId = " + registrationId);
        mContext = context;

        // Register registrationId to server
        ILoginPresenter presenter = new LoginPresenterImpl(mLoginView, context);
        presenter.DoRegisterDeviceId(registrationId);
    }

    @Override
    protected void onUnregistered(Context context, String registrationId) {
        Log.e(TAG, "Device unregistered for Device");
        
        GCMRegistrar.setRegisteredOnServer(context, false);      
    }

    @Override
    protected void onMessage(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.e(TAG, "Received message for Device. Extras: " + bundle.toString());
        Log.e(TAG, "Received message for Device Device");
        // Process message later
        String message = bundle.getString("msg_content", "");
        mNotifyCount = Integer.parseInt(bundle.getString("count", "0"));
        Log.e(TAG,"Received message for Device Device" + message);
        Log.e(TAG,"Received count for Device Device" + mNotifyCount);
        generateNotification(context, message);
        // Also update notify count
        updateNotifyLayoutWhenAppRunningForeground(context);
    }

    @Override
    protected void onDeletedMessages(Context context, int total) {
        Log.e(TAG, "Received deleted messages notification");

    }

    @Override
    public void onError(Context context, String errorId) {
        Log.e(TAG, "Received error: " + errorId);
//        displayMessage(context, getString(R.string.gcm_error, errorId));
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
        // log message
        Log.e(TAG, "Received recoverable error: " + errorId);
        return super.onRecoverableError(context, errorId);
    }
    private void updateNotifyLayoutWhenAppRunningForeground(Context context){
        String foregroundActivityName = AppFnUtils.getForegroundActivityName(context);
        if(foregroundActivityName != null && foregroundActivityName.equals(HomeActivity.class.getName())){
            Intent intent = new Intent();
            intent.setAction(context.getPackageName());
            intent.putExtra(AppConstants.APPOINTMENT_UPDATE_NOTIFICATION_COUNT_KEY, true);
            sendBroadcast(intent);
        }else{
            Log.d(TAG, "Device context =" + context.getPackageName());
        }

    }
    /**
     * Issues a notification to inform the user that server has sent a message.
     */
    private void generateNotification(Context context, String message) {
    	 int icon = R.drawable.ic_launcher;
         long when = 0;//System.currentTimeMillis();
         NotificationManager notificationManager = (NotificationManager)
                 context.getSystemService(Context.NOTIFICATION_SERVICE);
         Notification notification = new Notification(icon, message, when);
         String title = context.getString(R.string.app_name);
         Intent notificationIntent = new Intent(context, NotificationReceivingActivity.class);
         
         // set intent so it does not start a new activity
//         notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
         notificationIntent.addFlags( Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP );
         
         PendingIntent intent =
                 PendingIntent.getActivity(context, (int) when, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
         notification.setLatestEventInfo(context, title, message, intent);
         notification.defaults |= Notification.DEFAULT_SOUND;
         notification.defaults |= Notification.DEFAULT_VIBRATE;
         notification.flags |= Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL;
         notificationManager.notify((int) when, notification);

    }
    private ILoginView mLoginView = new ILoginView() {
        @Override
        public void LoginOK(String message) {

        }

        @Override
        public void LoginFail(String message) {

        }

        @Override
        public void RegisterGCMIdOK(String message) {
            Log.d("ThuNguyen", "Register GCM for Device to server successfully");
            if(mContext != null) {
                GCMRegistrar.setRegisteredOnServer(mContext, true);
            }else{
                GCMRegistrar.setRegisteredOnServer(getApplicationContext(), true);
            }
        }

        @Override
        public void DisplayMessageIncaseError(String message, String funcTitle) {

        }

        @Override
        public void UnauthorizedProcessing() {

        }
    };
}