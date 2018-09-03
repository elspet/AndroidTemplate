package com.c4po.template.global;

import android.Manifest;

/**
 * @author Lisa
 * @date 2018/6/13.
 */
public class Constants {

    /**
     * 程序所需权限 ：相机 文件存储 网络访问
     */
    public static final int PERMISSIONS_REQUEST = 1;
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final String PERMISSION_WRITE_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String PERMISSION_READ_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_INTERNET = Manifest.permission.INTERNET;
    public static final String PERMISSION_ACCESS_NETWORK_STATE = Manifest.permission.ACCESS_NETWORK_STATE;
    public static final String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static String[] Permission = new String[]{PERMISSION_READ_PHONE_STATE, PERMISSION_CAMERA, PERMISSION_WRITE_STORAGE, PERMISSION_READ_STORAGE, PERMISSION_INTERNET, PERMISSION_ACCESS_NETWORK_STATE};


    /**
     * 关锁行为
     */
    public static final String LOCK_ACTION = "com.listcloud.intelligentdoor.global.Lock";

    public static final String groupId = "sk";
    public static final String groupName = "失控科技";

}
