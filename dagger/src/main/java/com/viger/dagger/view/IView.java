package com.viger.dagger.view;

import io.reactivex.disposables.Disposable;

/**
 * 存放所有的View Interface
 */
public interface IView {

    /**
     * 登录
     */
    interface ILoginView {
        String getUserName();
        String getPassword();
        void showToast(String content);
        void closeDispose(Disposable disposable);
        void showProgress();
        void hideProgress();
        void toOtherActivity();
    }

    /**
     * View--Home
     */
    interface IHomeView {
        void setData(List<HomeBean.DataBean> mDataList);  // 设置主体数据
        void onRefresh(List<HomeBean.DataBean> mDataList);  // refresh
        void closeDispose(Disposable disposable);    //  关闭流
    }

}
