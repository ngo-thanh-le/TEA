package com.qsoft.samples.FirstAndroid.utils;

import android.os.Bundle;
import com.qsoft.samples.FirstAndroid.SaveActivityState;

import java.lang.reflect.Field;

/**
 * User: Le
 * Date: 10/16/13
 */
public class StateUtils
{
    public static void onSaveInstanceState(Object target, Bundle outState)
    {
        for (Field member : ClassUtils.getAllFields(target.getClass()))
        {
            if (!member.isAccessible()) member.setAccessible(true);
            try
            {
                if (member.isAnnotationPresent(SaveActivityState.class))
                {
                    if (member.getType() == Integer.class || member.getType() == int.class)
                    {
                        outState.putInt(member.getName(), (Integer) member.get(target));
                    }
                    else if (member.getType() == String.class)
                    {
                        outState.putString(member.getName(), (String) member.get(target));
                    }
                    else if (member.getType() == Boolean.class || member.getType() == boolean.class)
                    {
                        outState.putBoolean(member.getName(), (Boolean) member.get(target));
                    }
                }
            }
            catch (Exception e)
            {
                LogUtils.debugLog(target, e.getMessage(), e);
            }
        }
    }

    public static void loadState(Object target, Bundle savedInstanceState) {
        if (savedInstanceState == null) return;
        for (Field member : ClassUtils.getAllFields(target.getClass()))
        {
            if (!member.isAccessible()) member.setAccessible(true);
            try
            {
                if (member.isAnnotationPresent(SaveActivityState.class))
                {
                    if (member.getType() == Integer.class || member.getType() == int.class)
                    {
                        member.set(target, savedInstanceState.getInt(member.getName()));
                    }
                    else if (member.getType() == String.class)
                    {
                        member.set(target, savedInstanceState.getString(member.getName()));
                    }
                    else if (member.getType() == Boolean.class || member.getType() == boolean.class)
                    {
                        member.set(target, savedInstanceState.getBoolean(member.getName()));
                    }
                }
            }
            catch (Exception e)
            {
                LogUtils.debugLog(target, e.getMessage(), e);
            }
        }
    }
}
