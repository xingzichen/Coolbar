package com.cmbchina.coolbar.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Scroller;

/**
 * Created by liang on 7/7/15.
 */
public class CartListView extends ListView implements AbsListView.OnScrollListener {

    private Scroller mScroller;
    private OnScrollListener mScrollListener;

    public CartListView(Context context) {
        super(context);
        initWithContext(context);
    }

    public CartListView(Context context, AttributeSet attrs){
        super(context, attrs);
        initWithContext(context);
    }

    public CartListView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        initWithContext(context);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    private void initWithContext(Context context){
        mScroller = new Scroller(context, new DecelerateInterpolator());
        super.setOnScrollListener(this);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
    }
}
