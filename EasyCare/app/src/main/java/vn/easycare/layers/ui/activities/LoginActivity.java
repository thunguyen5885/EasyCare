package vn.easycare.layers.ui.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gcm.GCMRegistrar;

import vn.easycare.R;
import vn.easycare.layers.services.WSDataSingleton;
import vn.easycare.layers.ui.base.BaseActivity;
import vn.easycare.layers.ui.components.singleton.MySharePreference;
import vn.easycare.layers.ui.presenters.base.ILoginPresenter;
import vn.easycare.layers.ui.presenters.LoginPresenterImpl;
import vn.easycare.layers.ui.views.ILoginView;
import vn.easycare.utils.AppConstants;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.DialogUtil;


public class LoginActivity extends BaseActivity implements ILoginView{

    private ILoginPresenter mLoginPresenter;
    EditText edtUsername;
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginPresenter = new LoginPresenterImpl(this, this.getApplicationContext());
        View loginLayout = findViewById(R.id.loginLayout);
        edtUsername = (EditText)findViewById(R.id.etxUsername);
        edtPassword = (EditText)findViewById(R.id.etxPassword);
        loginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hide keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edtPassword.getWindowToken(), 0);

                mLoginPresenter.DoAuthenticateUser(edtUsername.getText().toString(),edtPassword.getText().toString());

            }
        });
        TextView linkForgetPass = (TextView) findViewById(R.id.linkForgetPass);
        linkForgetPass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

        // Apply font
        View rootLayout = findViewById(R.id.loginRootLayout);
        AppFnUtils.applyFontForTextViewChild(rootLayout);

        if(MySharePreference.isLogin(this)){
            WSDataSingleton.getInstance(this).setDoctorInfo(MySharePreference.getDoctorInfo(this));
            finish();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(edtUsername.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void LoginOK(String message) {
        //move to home screen
        finish();
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);


    }

    @Override
    public void LoginFail(String message) {
        //show message for login fail
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        DialogUtil.createInformDialog(this, this.getResources().getString(R.string.title_login), message,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    @Override
    public void RegisterGCMIdOK(String message) {
        //do nothing
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
