package com.c4po.template.mvp.ui.contract;

import com.c4po.template.base.BaseContract;
import java.util.List;

/**
 * 广告页Demo协议
 * @author Lisa
 * @date 2018/8/21
 */
public interface IAdvertisementView extends BaseContract.View  {


    /**
     * 显示识别到的用户图片
     * @param iamgeDatas
     */
    void setListData(List<String> iamgeDatas);


}
