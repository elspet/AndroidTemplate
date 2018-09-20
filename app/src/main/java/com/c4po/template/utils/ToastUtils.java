package com.c4po.template.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast要全局定义统一的一个对象，避免出现Toast无法销毁的情况
 * @author Lisa
 * @date 2018/9/18
 */

public class ToastUtils {

    private Toast mToast;
    private static ToastUtils mToastUtils;

    private ToastUtils(Context context) {
        mToast = Toast.makeText(context.getApplicationContext(), null, Toast.LENGTH_SHORT);
    }

    public static synchronized ToastUtils getInstanc(Context context) {
        if (null == mToastUtils) {
            mToastUtils = new ToastUtils(context);
        }
        return mToastUtils;
    }
    /**
     * 显示toast
     *
     * @param toastMsg
     */
    public void showToast(String toastMsg) {
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setText(toastMsg);
        mToast.show();
    }

    /**
     * 显示toast
     *
     * @param toastMsg
     */
    public void showShortToast(String toastMsg) {
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setText(toastMsg);
        mToast.show();
    }

    /**
     * 显示toast
     *
     * @param toastMsg
     */
    public void showLongToast(String toastMsg) {
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setText(toastMsg);
        mToast.show();
    }

    /**
     * 取消toast，在activity的destory方法中调用
     */
    public void destory() {
        if (null != mToast) {
            mToast.cancel();
            mToast = null;
        }
        mToastUtils = null;
    }

}
