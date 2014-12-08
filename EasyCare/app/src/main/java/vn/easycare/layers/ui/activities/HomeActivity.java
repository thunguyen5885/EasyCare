package vn.easycare.layers.ui.activities;

import android.os.Bundle;
import android.view.Window;

import vn.easycare.R;
import vn.easycare.layers.ui.base.BaseActivity;
import vn.easycare.layers.ui.components.CommonHeader;
import vn.easycare.layers.ui.fragments.MenuFragment;

/**
 * Created by ThuNguyen on 12/8/2014.
 */
public class HomeActivity extends BaseActivity{
    private MenuFragment mMenuFragment;
    private CommonHeader mCommonHeader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
    }
}
