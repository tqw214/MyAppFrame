package com.viger.myappframe.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    //ButterKnife
    private Unbinder mUnbinder;

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUnbinder = ButterKnife.bind(this);
        setContentView(getLayout());
        if(mPresenter != null) {
            mPresenter.attachView(this);
        }
        onViewCreated();
        initToolBar();
        initData();
    }

    protected abstract void initData();

    protected abstract void initToolBar();

    protected abstract void onViewCreated();

    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        if(mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
