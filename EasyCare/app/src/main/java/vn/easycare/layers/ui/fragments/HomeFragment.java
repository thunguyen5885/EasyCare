package vn.easycare.layers.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.easycare.R;
import vn.easycare.layers.ui.components.views.foreground.ForegroundRelativeLayout;

/**
 * Created by ThuNguyen on 12/11/2014.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    public interface IHomeItemOnClickListner{
        public void onHomeItemDatingManagementClicked();
        public void onHomeItemCalendarCreatingClicked();
        public void onHomeItemPatientListClicked();
        public void onHomeItemStatisticClicked();
    }
    private View mDatingManagementLayout;
    private View mCalendarCreatingLayout;
    private View mPatientListLayout;
    private View mStatisticLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        mDatingManagementLayout = v.findViewById(R.id.homeItemDatingManagementLayout);
        mCalendarCreatingLayout = v.findViewById(R.id.homeItemCalendarCreatingLayout);
        mPatientListLayout = v.findViewById(R.id.homeItemPatientListLayout);
        mStatisticLayout = v.findViewById(R.id.homeItemStatisticLayout);

        // Initialize the home item layout
        initHomeItemLayout(mDatingManagementLayout, R.string.home_dating_management, R.drawable.ic_dating_management);
        initHomeItemLayout(mCalendarCreatingLayout, R.string.home_calendar_creating, R.drawable.ic_calendar_creating);
        initHomeItemLayout(mPatientListLayout, R.string.home_patient_list, R.drawable.ic_patient_list);
        initHomeItemLayout(mStatisticLayout, R.string.home_statistic, R.drawable.ic_statistic);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onClick(View v) {
        int parentViewId = (Integer) v.getTag();
        switch(parentViewId){
            case R.id.homeItemDatingManagementLayout:
                break;
            case R.id.homeItemCalendarCreatingLayout:
                break;
            case R.id.homeItemPatientListLayout:
                break;
            case R.id.homeItemStatisticLayout:
                break;
        }
    }
    private void initHomeItemLayout(View parentView, int titleId, int posterId){
        View view = parentView.findViewById(R.id.homeItemLayout);
        view.setTag(parentView.getId());
        view.setOnClickListener(this);

        // Title
        TextView tvTitle = (TextView)parentView.findViewById(R.id.tvHomeItemTitle);
        tvTitle.setText(titleId);
        // Poster
        ImageView ivPoster = (ImageView)parentView.findViewById(R.id.ivHomeItemPoster);
        ivPoster.setImageResource(posterId);
    }

    private IHomeItemOnClickListner mHomeItemOnClickListener = new IHomeItemOnClickListner() {
        @Override
        public void onHomeItemDatingManagementClicked() {

        }

        @Override
        public void onHomeItemCalendarCreatingClicked() {

        }

        @Override
        public void onHomeItemPatientListClicked() {

        }

        @Override
        public void onHomeItemStatisticClicked() {

        }
    };
}
