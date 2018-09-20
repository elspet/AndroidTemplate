package com.c4po.template.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.c4po.template.R;
import com.c4po.template.base.BaseActivity;
import com.c4po.template.mvp.presenter.AdvertisementPresenter;
import com.c4po.template.mvp.ui.contract.IAdvertisementView;
import com.c4po.template.mvp.ui.message.ReadmeMsgEvent;
import com.c4po.template.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.transformer.AccordionTransformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 广告页布局
 * @author Lisa
 * @date 2018/8/21
 */

public class AdvertisementActivity extends BaseActivity implements AdapterView.OnItemClickListener,OnBannerListener, IAdvertisementView {

    @BindView(R.id.banner)
    Banner mAdBanner;

    @BindView(R.id.tv_readed_readme)
    TextView tvReaded;

    AdvertisementPresenter advertisementPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_advertisement);
        advertisementPresenter = new AdvertisementPresenter(getActivity(),
                this);
        initView();
    }

    @OnClick(R.id.btn_show_readme)
    public void onClickShowReadme() {
        Intent toShowReadme = new Intent(this,ShowReadmeActivity.class);
        startActivity(toShowReadme);
    }

    public AdvertisementPresenter getPresenter(){
        if(advertisementPresenter!=null){
            return advertisementPresenter;
        }else{
            return null;
        }
    }

    private void initView(){
        // 设置页面切换动画
        mAdBanner.setBannerAnimation(AccordionTransformer.class);
        advertisementPresenter.initData();

    }


    @Override
    public void setListData(List<String> iamgeDatas) {
        mAdBanner.setImages(iamgeDatas)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void OnBannerClick(int i) {

    }


    @Override
    protected void onStart() {
        super.onStart();
        // 组件间通信
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 组件间通信
        if(isFinishing()&&EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMsg(ReadmeMsgEvent msgEvent){

        tvReaded.setText(msgEvent.readmeMsg);
    }

}
