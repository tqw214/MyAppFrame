package com.viger.myappframe.view.activity;

import android.Manifest;
import android.widget.Toast;

import com.viger.myappframe.base.BaseActivity;
import com.viger.myappframe.presenter.MainPresenter;
import com.viger.myappframe.view.contract.MainContract;
import com.viger.myframe.R;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {

    @Override
    protected void initData() {
    performRequestPermissions("我们需要获取权限",
                                      new String[]{
        Manifest.permission.WRITE_EXTERNAL_STORAGE},
            100, new PermissionsResultListener() {
        @Override
        public void onPermissionSuccess() {
            Toast.makeText(MainActivity.this, "权限申请成功,开始写入文件", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onPermissionFailed() {
            Toast.makeText(MainActivity.this, "权限申请失败", Toast.LENGTH_SHORT).show();
        }
    });
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
