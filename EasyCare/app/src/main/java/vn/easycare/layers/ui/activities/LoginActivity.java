package vn.easycare.layers.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import vn.easycare.R;
import vn.easycare.layers.ui.base.BaseActivity;
import vn.easycare.layers.ui.presenters.ILoginPresenter;
import vn.easycare.layers.ui.presenters.LoginPresenterImpl;
import vn.easycare.layers.ui.views.ILoginView;


public class LoginActivity extends BaseActivity implements ILoginView{

    private ILoginPresenter mLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginPresenter = new LoginPresenterImpl(this, this.getApplicationContext());
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
