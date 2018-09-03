package com.c4po.template.mvp.model.entity;

import com.c4po.template.base.BaseModel;

/**
 * 用户信息
 * @author Lisa
 * @date 2018/08/14
 */

public class UserInfoModel extends BaseModel {


    private String userId;
    /**
     * 昵称
     */
    private String userName;

    /**
     * 用户图片
     */
    private String imageUrl;

    /**
     * 性别 男是1，女是2
     */
    private int gender;

    /**
     * IC卡号
     */
    private String icCard;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getIcCard() {
        return icCard;
    }

    public void setIcCard(String icCard) {
        this.icCard = icCard;
    }

}