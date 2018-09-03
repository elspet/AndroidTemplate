package com.c4po.template.global;

import android.app.Activity;
import android.content.Context;


import com.c4po.template.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于对activity的管理
 * @author lisa
 * @date 2018/08/16
 */
public class ActivityManager {
    private static ActivityManager activityManager;

    private List<BaseActivity> activityStack;

    public ActivityManager() {
        activityStack = new ArrayList<>();
    }

    public static ActivityManager getInstance() {
        if (null == activityManager) {
            activityManager = new ActivityManager();
        }
        return activityManager;
    }

    /**
     * 添加activity
     *
     * @param activity
     */
    public void addActivity(BaseActivity activity) {
        if (null != activityStack) {
            activityStack.add(activity);
        }
    }

    /**
     * 移除一个activity
     *
     * @param activity
     */
    public void removeActivity(BaseActivity activity) {
        if (null != activityStack) {
            activityStack.remove(activity);
        }
    }

    /**
     * 关闭所有的activity
     */
    public void finishAllActivity() {
        if (null != activityStack) {
            for (BaseActivity activity : activityStack) {
                activity.finish();
            }
        }
    }

    /**
     * 获取目标actiivty
     *
     * @param c
     * @return
     */
    public <T extends BaseActivity> T getActivity(Class c) {
        if (null != activityStack) {
            return (T) activityStack.get(getActivityAt(c));
        }
        return null;
    }

    /**
     * 获取目标Activity所在位置
     *
     * @param c
     * @return
     */
    public int getActivityAt(Class c) {
        if (null != activityStack) {
            for (int i = 0; i < activityStack.size(); i++) {
                if (activityStack.get(i).getClass() == c) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 判断目标类是否正在运行
     *
     * @param c
     * @return
     */
    public boolean isRun(Class c) {
        return getActivityAt(c) != -1;
    }

    /**
     * 判断该类是不是在类顶部
     *
     * @param activity
     * @return
     */
    protected static boolean isTopActivity(Activity activity) {
        android.app.ActivityManager activityManager = (android.app.ActivityManager) activity
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<android.app.ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            // 应用程序位于堆栈的顶层
            if (AppConfigs.PACKAGE_NAME.equals(tasksInfo.get(0).topActivity
                    .getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public BaseActivity getCurrActivity() {
        if (activityStack == null || activityStack.size() == 0) {
            return null;
        } else {
            return activityStack.get(activityStack.size() - 1);
        }
    }
}