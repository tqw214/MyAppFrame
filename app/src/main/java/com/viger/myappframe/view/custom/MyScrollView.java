package com.viger.myappframe.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

    }

    private ScrollChangeListener mListener;

    public interface ScrollChangeListener {
        void onScroll(int l, int t, int oldl, int oldt);
    }

    public void setOnScrollChangeListener(ScrollChangeListener listener){
        mListener = listener;
    }

//    private MyScrollView scrollView;
//        scrollView.setOnScrollChangeListener(new MyScrollView.ScrollChangeListener() {
//        @Override
//        public void onScroll(int l, int t, int oldl, int oldt) {
//
//        }
//    });

}
