package com.qsoft.eip.common.utils;

import android.util.Log;

/**
 * User: Le
 * Date: 10/16/13
 */
public class LogUtils
{
// -------------------------- STATIC METHODS --------------------------

    public static void debugLog(Object target, String content)
    {
        Log.d(target.getClass().getSimpleName(), content);
    }

    public static void debugLog(Class targetType, String content)
    {
        Log.d(targetType.getSimpleName(), content);
    }

    public static void debugLog(Object target, String content, Throwable throwable)
    {
        Log.d(target.getClass().getSimpleName(), content, throwable);
    }

    public static void debugLog(Class targetType, String content, Throwable throwable)
    {
        Log.d(targetType.getSimpleName(), content, throwable);
    }
}
