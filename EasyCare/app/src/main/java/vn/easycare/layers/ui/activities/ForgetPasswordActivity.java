package vn.easycare.layers.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import vn.easycare.R;
import vn.easycare.layers.ui.base.BaseActivity;
import vn.easycare.layers.ui.presenters.LoginPresenterImpl;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by phan on 12/15/2014.
 */
public class ForgetPasswordActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().setDisplayShowCustomEnabled(true);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
        setContentView(R.layout.activity_forget_password);
        //getActionBar().setTitle(R.string.forget_password_title);

        getActionBar().setCustomView(R.layout.actionbar_forgetpassword_screen);
        getActionBar().setDisplayShowHomeEnabled(true);
        Button sendBtn = (Button)findViewById(R.id.btnSend);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // Apply font
        View rootLayout = findViewById(R.id.forgetPassLayout);
        AppFnUtils.applyFontForTextViewChild(rootLayout, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
