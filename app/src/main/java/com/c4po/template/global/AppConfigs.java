package com.c4po.template.global;

import android.os.Environment;


/**
 * @author Lisa
 */
public class AppConfigs {

    /**
     * Bugly热修复
     */
    public static String BuglyID = "390f74c997";

    /**
     * 讯飞语音
     */
    public static String IflytekID = "5a654ca9";




    /**
     * 测试服务器地址
     */
    public static final String DEFAULT_URL = "http://baidu.com/";

    /**
     * 崩溃日志文件名
     */
    public static final String CRASH_FILE_NAME = "/sdcard/IntelligentDoorCrash/";


    /**
     * APP 包名
     */
    public static final String PACKAGE_NAME = "com.listcloud.intelligentdoor";

    /**
     * 缓存文件名
     */
    public static final String CACHE_FILE_NAME = "intelligent_door";


    /**
     * 待录入人脸图片本地路径
     */
    public static final String FASS_PASS_LOCAL_FACE_IMAGE_BASE_DIR =  Environment.
            getExternalStorageDirectory().getAbsolutePath()
            +"/DCIM/face/face_folder/";//

    /**
     * 预计每个文件夹内有500张人脸
     */
    public static final String[] FASS_PASS_IMAGE_FOLDERS = {"face111","face222","face333","face444"};



    /**
     * pushType
     */
    public static final String PUSH_TYPE_TITLE = "pushType";
    public static final String PUSH_TYPE_AD = "ad";
    public static final String PUSH_TYPE_ADD_USER = "user";
    public static final String PUSH_TYPE_DEVICE_INFO = "device";


    /**
     * 奇梦者
     */
    public static final String QdreamerAppid = "f4b7c86a-ef57-11e6-8aa7-00e04c12c2c7";
    public static final String QdreamerKey = "0063909a-ef58-11e6-8a33-00e04c12c2c7";

}
