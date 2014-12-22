package vn.easycare.layers.ui.components.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import vn.easycare.R;
import vn.easycare.layers.ui.components.adapters.PatientListAdapter;

/**
 * Created by ThuNguyen on 12/16/2014.
 */
public class PatientListLayout extends ListView{
    private LayoutInflater mLayoutInflater;
    private PatientListAdapter mAdapter;
    private View mLoadMoreView;
    public PatientListLayout(Context context) {
        super(context);
        init(context);
    }

    public PatientListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PatientListLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    private void init(Context context){
        setDivider(null);
        setCacheColorHint(Color.TRANSPARENT);
        mLayoutInflater = LayoutInflater.from(context);
        mLoadMoreView = mLayoutInflater.inflate(R.layout.load_more_ctrl, null);
        addFooterView(mLoadMoreView);

    }
    public void renderData(boolean isBlackList){
        mAdapter = new PatientListAdapter(getContext());
        mAdapter.setBlackList(isBlackList);
        setAdapter(mAdapter);
    }
}
