package com.c4po.template.utils;

import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * @author Lisa
 * @date 2018/08/16
 */
public final class MyLogger {
    private static boolean sDebug = true;

    public static synchronized void init(boolean debug, String tag) {
        sDebug = debug;
        if (debug) {
            // 日志初始化
            Logger.addLogAdapter(new AndroidLogAdapter());
        }
    }

    public static void d(String tag, String msg) {
        if (sDebug) {
            Logger.t(tag).d(msg);
        }
    }

    public static void d(String msg) {
        if (sDebug) {
            Logger.d(msg);
        }
    }

    public static void e(String msg) {
        if (sDebug) {
            Logger.e(msg);
        }
    }

    public static void e(String tag, String msg) {
        if (sDebug) {
            Logger.t(tag).e(msg);
        }
    }

    public static void json(String msg) {
        if (sDebug) {
            Logger.json(msg);
        }
    }

    public static void json(String tag, String msg) {
        if (sDebug) {
            Logger.t(tag).json(msg);
        }
    }

    /**
     * What a Terrable Failure
     * @param msg
     */
    public static void wtf(String msg) {
        if (sDebug) {
            Logger.wtf(msg);

        }
    }
    /**
     * What a Terrable Failure
     * @param tag
     * @param msg
     */
    public static void wtf(String tag, String msg) {
        if (sDebug) {
            Logger.t(tag).wtf(msg);

        }
    }


    /**
     * 分段打印出较长log文本
     *
     * @param logContent 打印文本
     * @param showLength 规定每段显示的长度（AndroidStudio控制台打印log的最大信息量大小为4k）
     * @param tag        打印log的标记
     */
    public static void showLargeLog(String logContent, int showLength, String tag) {
        if (logContent.length() > showLength) {
            String show = logContent.substring(0, showLength);
            Log.v(tag, StringUtils.formatJson(show));
            /*剩余的字符串如果大于规定显示的长度，截取剩余字符串进行递归，否则打印结果*/
            if ((logContent.length() - showLength) > showLength) {
                String partLog = logContent.substring(showLength, logContent.length());
                showLargeLog(partLog, showLength, tag);
            } else {
                String printLog = logContent.substring(showLength, logContent.length());
                Log.v(tag, StringUtils.formatJson(printLog));
            }

        } else {
            Log.v(tag, StringUtils.formatJson(logContent));
        }
    }

    /**
     * 分段打印出较长log文本
     *
     * @param logContent 打印文本
     * @param tag        打印log的标记
     */
    public static void showLargeLog(String logContent, String tag) {
        if (logContent.length() > 4000) {
            for (int i = 0; i < logContent.length(); i += 4000) {
                if (i + 4000 < logContent.length()) {
                    Log.i(tag + i, StringUtils.formatJson(logContent.substring(i, i + 4000)));
                } else {
                    Log.i(tag + i, StringUtils.formatJson(logContent.substring(i, logContent.length())));
                }
            }
        } else {
            Log.i(tag, StringUtils.formatJson(logContent));
        }
    }
}
