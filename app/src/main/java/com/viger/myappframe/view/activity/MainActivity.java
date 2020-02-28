package com.viger.myappframe.view.activity;

import com.viger.myappframe.R;
import com.viger.myappframe.base.BaseActivity;
import com.viger.myappframe.presenter.MainPresenter;
import com.viger.myappframe.view.contract.MainContract;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {

    @Override
    protected void initData() {

    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
