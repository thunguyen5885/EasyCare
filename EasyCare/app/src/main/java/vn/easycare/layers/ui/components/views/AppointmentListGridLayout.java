package vn.easycare.layers.ui.components.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import vn.easycare.R;
import vn.easycare.layers.ui.components.adapters.CalendarGridAdapter;
import vn.easycare.layers.ui.components.views.base.BaseLinearLayout;
/**
 * Created by ThuNguyen on 12/17/2014.
 */
public class AppointmentListGridLayout extends BaseLinearLayout {
    // For layout, view, control
    private View mFlPrevious;
    private View mFlNext;
    private TextView mTvDate;
    private GridView mGvCalendar;
    private View mEmptyHintColor;
    private View mWaitingForConfirmedHintColor;
    private View mConfirmedHintColor;
    private View mTreatedHintColor;

    private CalendarGridAdapter mGridAdapter;
    public AppointmentListGridLayout(Context context) {
        super(context);
        init(context);
    }

    public AppointmentListGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AppointmentListGridLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.appointment_list_grid_ctrl, this);
        mFlPrevious = view.findViewById(R.id.flPrevious);
        mFlNext = view.findViewById(R.id.flNext);
        mTvDate = (TextView)view.findViewById(R.id.tvDate);
        mGvCalendar = (GridView)view.findViewById(R.id.gvCalendar);
        mEmptyHintColor = view.findViewById(R.id.emptyHintColor);
        mWaitingForConfirmedHintColor = view.findViewById(R.id.waitingConfirmedHintColor);
        mConfirmedHintColor = view.findViewById(R.id.confirmedHintColor);
        mTreatedHintColor = view.findViewById(R.id.treatedHintColor);

        // Begin set background
    }
    private void setBackgroundForHintControl(View root, int resColorId){
        View backgroundHintColor = root.findViewById(R.id.vHintColorBackground);
        backgroundHintColor.setBackgroundResource(resColorId);
    }
    private void setHintNumberVal(View root, int numVal){
        TextView tvHintNumberVal = (TextView)root.findViewById(R.id.tvHintColorNumVal);
        tvHintNumberVal.setText(String.valueOf(numVal));
    }
}
