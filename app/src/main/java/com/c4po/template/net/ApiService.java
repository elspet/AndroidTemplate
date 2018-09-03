package com.c4po.template.net;


import com.c4po.template.mvp.model.entity.ApiModel;
import com.c4po.template.mvp.model.entity.LoginModel;
import com.c4po.template.mvp.model.entity.UserInfoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * @author Lisa
 * @date 2018/08/28
 */
public interface ApiService {

    /**
     * 基本接口地址请求方法
     *
     * @return
     */
    @GET("api.json")
    Observable<ApiModel> apiSetting();

    /**
     * 登录
     *
     * @param type  平台类型：1手机，2为微信，3为微博
     * @param token 平台的用户验证信息（手机登陆时为验证码）
     * @return
     */
    @FormUrlEncoded
    @POST("account/login")
    Observable<LoginModel> accountLogin(
            @Field("type") int type,
            @Field("token") String token);


    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    @GET("user")
    Observable<List<UserInfoModel>> getUserInfo(
            @Field("userId") String userId);

    @GET("ad")
    Observable<List<String>> getAdImage();


}