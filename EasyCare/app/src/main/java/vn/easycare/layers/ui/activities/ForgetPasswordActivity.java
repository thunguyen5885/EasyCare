package vn.easycare.layers.ui.activities;

import android.os.Bundle;

import vn.easycare.R;
import vn.easycare.layers.ui.base.BaseActivity;
import vn.easycare.layers.ui.presenters.LoginPresenterImpl;

/**
 * Created by phan on 12/15/2014.
 */
public class ForgetPasswordActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
    }
}
