package com.c4po.template.base;


/**
 * @author Lisa
 * P类基类
 */
public class BasePresenter<T extends BaseContract.View> {
    private T view;
    private BaseActivity activity;

    public BasePresenter(BaseActivity activity, T view) {
        this.view = view;
        this.activity = activity;
    }

    public BaseActivity getActivity() {
        return activity;
    }


    public T getView() {
        return view;
    }
}