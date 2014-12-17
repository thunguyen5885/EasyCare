package vn.easycare.layers.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.easycare.R;
import vn.easycare.layers.ui.base.BaseActivity;

/**
 * Created by phan on 12/16/2014.
 */
public class ChangePasswordActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Button sendBtn = (Button)findViewById(R.id.btnSend);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
