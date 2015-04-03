package vn.easycare.layers.ui.base;

import android.app.Fragment;
import android.content.Intent;

import vn.easycare.layers.ui.activities.LoginActivity;

/**
 * Created by phannguyen on 12/7/14.
 */
public class BaseFragment extends Fragment{
    public void UnauthorizedProcessing() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        getActivity().finish();
    }
}
