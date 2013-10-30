package com.qsoft.samples.FirstAndroid.utils;

import android.util.Log;

/**
 * User: Le
 * Date: 10/16/13
 */
public class LogUtils
{
    public static void debugLog(Object target, String content, Throwable throwable)
    {
        Log.d(target.getClass().getSimpleName(), content, throwable);
    }

    public static void debugLog(Object target, String content)
    {
        Log.d(target.getClass().getSimpleName(), content);
    }
}
