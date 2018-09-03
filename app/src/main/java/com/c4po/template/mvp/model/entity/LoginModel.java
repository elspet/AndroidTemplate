package com.c4po.template.mvp.model.entity;


import com.c4po.template.base.BaseModel;

import java.util.List;


/**
 * 登录模型
 * @author Lisa
 * @date 2017/4/24.
 */
public class LoginModel extends BaseModel {


    private List<String> platforms;

    /**
     *  用户信息
     */
    private UserInfoModel user;


    /**
     *  登录Token
     */
    private String loginToken;

    /**
     * 融云的im token
     */
    private String imToken;
    /**
     * 服务器的时间
     */
    private String serverTime;

    public UserInfoModel getUser() {
        return user;
    }

    public void setUser(UserInfoModel user) {
        this.user = user;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }


    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

}
