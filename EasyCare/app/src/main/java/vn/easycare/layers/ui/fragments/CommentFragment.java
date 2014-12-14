package vn.easycare.layers.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.components.adapters.CommentAdapter;

/**
 * Created by ThuNguyen on 12/13/2014.
 */
public class CommentFragment extends Fragment implements View.OnClickListener{
    public static final int COMMENT_NUM_PER_PAGE = 3;
    private int mTotalItemCount = 0;

    // For control, layout
    private ListView mCommentListView;
    private CommentAdapter mCommentAdapter;
    private View mLoadMoreView;
    // For data, object


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comment, container, false);
        mCommentListView = (ListView) v.findViewById(R.id.commentListView);
        mLoadMoreView = inflater.inflate(R.layout.load_more_ctrl, null);
        mCommentListView.addFooterView(mLoadMoreView);
        View loadMoreEventLayout = mLoadMoreView.findViewById(R.id.loadMoreLayout);
        loadMoreEventLayout.setOnClickListener(this);
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
            case R.id.loadMoreLayout:
                Toast.makeText(getActivity(), "Load more clicked", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
