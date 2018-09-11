package com.c4po.template.global.application;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.c4po.template.db.greendao.DaoMaster;
import com.c4po.template.db.greendao.DaoSession;
import com.c4po.template.global.AppConfigs;
import com.c4po.template.utils.MyLogger;
import com.facebook.stetho.Stetho;

import java.io.File;

/**
 * function
 * @author Lisa
 * @date 2018/9/3
 */
public class MyApplication extends Application {

    MyApplication myApplication ;

    public MyApplication getApplication(){
        return myApplication;
    }
    /**
     * 数据库GreenDao
     */
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    public static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        // 日志初始化
        MyLogger.init(true, AppConfigs.PACKAGE_NAME);
        MultiDex.install(this);
        // 调试工具
        Stetho.initializeWithDefaults(this);
    }


    /**
     * 设置greenDao 
     */
    public void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this.getApplicationContext(),"template.db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        Log.d("Application","setDatabase");
    }


    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }


    public Context getContext(){
        return this;
    }

}
