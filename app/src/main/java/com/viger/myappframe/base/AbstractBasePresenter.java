package com.viger.myappframe.base;

public class AbstractBasePresenter<T extends BaseView> implements BasePresenter<T> {

    protected  T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
