package com.c4po.template.mvp.presenter.contract;


import com.c4po.template.base.BaseContract;

/**
 * 广告页Presenter接口定义
 *
 * @author Lisa
 * @date 2018/8/21
 */
public interface IAdvertisementPresenter extends BaseContract.Presenter {


    /**
     * 初始化数据，从云端获取广告图片
     * 由于还没有，所以先默认添加
     */
    void initData();

    /**
     * 添加广告图片
     * 推送图片，调用该方法
     */
    void updateAdvertiseImage(String imageUrl);

}
