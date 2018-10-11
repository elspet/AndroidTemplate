package com.c4po.template.mvp.presenter;

import com.c4po.template.base.BaseActivity;
import com.c4po.template.base.BasePresenter;
import com.c4po.template.mvp.presenter.contract.IAdvertisementPresenter;
import com.c4po.template.mvp.ui.contract.IAdvertisementView;

import java.util.ArrayList;

/**
 * 广告Presenter
 * @author Lisa
 * @date 2018/8/21
 */
public class AdvertisementPresenter extends BasePresenter<IAdvertisementView> implements IAdvertisementPresenter {

    ArrayList<String> adImageUrls = new ArrayList<>();

    public AdvertisementPresenter(BaseActivity activity,
                               IAdvertisementView viewContract) {
        super(activity, viewContract);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {

        adImageUrls.add("http://oodq26n2u.bkt.clouddn.com/tea2.jpg");
        adImageUrls.add("http://oodq26n2u.bkt.clouddn.com/tea3.jpeg");
        adImageUrls.add("http://oodq26n2u.bkt.clouddn.com/tea_white.jpg");

//        HttpApi.api(getActivity()).getAdImage()
//                // 表示getAdImage执行在I/O线程
//                .subscribeOn(Schedulers.io())
//                .retryWhen(new RetryWithDelay(2,1))
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        getActivity().showToast("要准备开始网络请求咯！！！");
//                    }
//                })
//                // 表示上面的doOnSubscribe执行在主线程
//                .subscribeOn(AndroidSchedulers.mainThread())
//                // 表示下面的观察者执行在主线程
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<String>>() {
//                    @Override
//                    public void accept(List<String> images) throws Exception {
//                        adImageUrls.clear();
//                        adImageUrls.addAll(images);
//                        getView().setListData(images);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        getActivity().showToast(getActivity().getString(R.string.net_loading_failed));
//                    }
//                });


        getView().setListData(adImageUrls);

    }

    /**
     * 更新广告图片
     */
    @Override
    public void updateAdvertiseImage(String imageUrl) {
        adImageUrls.add(imageUrl);
        getView().setListData(adImageUrls);
    }

}
