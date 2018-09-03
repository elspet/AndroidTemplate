package com.c4po.template.mvp.model.entity;

import com.c4po.template.base.BaseModel;

/**
 * 接口地址数据对象
 * @author Lisa
 * @date 2018/08/14
 */
public class ApiModel extends BaseModel {
    private String apiUrl;
    private String imageUrl;


    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}