package vn.easycare.layers.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vn.easycare.R;
import vn.easycare.layers.ui.base.BaseActivity;
import vn.easycare.layers.ui.presenters.base.ILoginPresenter;
import vn.easycare.layers.ui.presenters.LoginPresenterImpl;
import vn.easycare.layers.ui.views.ILoginView;
import vn.easycare.utils.AppFnUtils;


public class LoginActivity extends BaseActivity implements ILoginView{

    private ILoginPresenter mLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginPresenter = new LoginPresenterImpl(this, this.getApplicationContext());
        View loginLayout = findViewById(R.id.loginLayout);
        loginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);

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
        AppFnUtils.applyFontForTextViewChild(rootLayout, null);
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
    }

    @Override
    public void LoginFail(String message) {
        //show message for login fail
    }

    @Override
    public void ShowIncorrectAccountInfoMessage(String errorMessage) {
        //show message for email or password format not correct
    }
}
