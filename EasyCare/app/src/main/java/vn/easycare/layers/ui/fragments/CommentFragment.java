package vn.easycare.layers.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.adapters.CommentAdapter;

/**
 * Created by ThuNguyen on 12/13/2014.
 */
public class CommentFragment extends Fragment implements View.OnClickListener{
    public static final int COMMENT_NUM_PER_PAGE = 3;

    // For control, layout
    private View mCommentLoadMoreLayout;
    private ListView mCommentListView;
    private CommentAdapter mCommentAdapter;

    // For data, object


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comment, container, false);
        mCommentLoadMoreLayout = v.findViewById(R.id.commentLoadMoreLayout);
        mCommentLoadMoreLayout.setOnClickListener(this);
        mCommentListView = (ListView) v.findViewById(R.id.commentListView);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCommentAdapter = new CommentAdapter(getActivity());
        mCommentListView.setAdapter(mCommentAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            ((HomeActivity) getActivity()).showFooterSeparator();
            ((HomeActivity) getActivity()).showHeaderBackButton();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commentLoadMoreLayout:
                break;
        }
    }
}
