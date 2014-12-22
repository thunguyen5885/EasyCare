package vn.easycare.layers.ui.components.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import vn.easycare.R;

/**
 * Created by ThuNguyen on 12/22/2014.
 */
public class LoadMoreLayout extends FrameLayout{
    public interface ILoadMoreClickListener{
        public void onLoadMoreClicked();
    }
    private View mLoadMore;
    private ProgressBar mProgressBar;
    private LayoutInflater mInflater;
    private ILoadMoreClickListener mILoadMoreClickListener;
    public LoadMoreLayout(Context context) {
        super(context);
    }

    public LoadMoreLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    private void init(Context context){
        mInflater = LayoutInflater.from(context);
        View v = mInflater.inflate(R.layout.load_more_ctrl, null);
        mLoadMore = v.findViewById(R.id.loadMoreLayout);
        mLoadMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mILoadMoreClickListener != null){
                    mILoadMoreClickListener.onLoadMoreClicked();
                }
            }
        });
        mProgressBar = (ProgressBar) v.findViewById(R.id.pbLoadingForLoadMore);
        addView(v);
    }
    public void setOnLoadMoreClickListener(ILoadMoreClickListener loadMoreClickListener){
        mILoadMoreClickListener = loadMoreClickListener;
    }
    public void beginLoading(){
        setVisibility(View.VISIBLE);
        mLoadMore.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }
    public void loadMoreComplete(){
        setVisibility(View.VISIBLE);
        mLoadMore.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }
    public void closeView(){
        setVisibility(View.GONE);
    }
}
