package vn.easycare.layers.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import vn.easycare.R;
import vn.easycare.layers.services.WSDataSingleton;
import vn.easycare.layers.ui.base.BaseActivity;
import vn.easycare.layers.ui.presenters.LoginPresenterImpl;
import vn.easycare.utils.AppFnUtils;

/**
 * Created by phan on 12/15/2014.
 */
public class ForgetPasswordActivity extends BaseActivity {
    final String REQUEST_NEW_PASS_URL = "http://edev.easycare.vn/api/v1/users/login?email=gdgfdgd&password=ldgfd";
    EditText edtUserEmail;
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
        edtUserEmail = (EditText)findViewById(R.id.etxUsername);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // sendRequestNewPassword(edtUserEmail.getText().toString());
                volleyTest();
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

    private void sendRequestNewPassword(final String email){
        StringRequest sr = new StringRequest(Request.Method.GET, REQUEST_NEW_PASS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ForgetPasswordActivity.this, ForgetPasswordActivity.this.getResources().getString(R.string.forget_password_message_ok), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ForgetPasswordActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams(){

              /*  Map<String,String> params = new HashMap<String, String>();
                params.put("email",email);
                return params;*/
                return null;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        WSDataSingleton.getInstance(this).getRequestQueue().add(sr);
    }

    private void volleyTest(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://edev.easycare1.vn/api/v1/users/login?email=bacsihieugmail.com&password=laohac";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(ForgetPasswordActivity.this, response.substring(0,500), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String json = null;

                NetworkResponse response = error.networkResponse;
                if(response != null && response.data != null){
                    switch(response.statusCode){
                        case 400:
                            json = new String(response.data);
                            Toast.makeText(ForgetPasswordActivity.this, json, Toast.LENGTH_SHORT).show();
                            break;
                    }
                    //Additional cases
                }

            }

        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
