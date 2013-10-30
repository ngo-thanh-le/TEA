package com.qsoft.eip.common.utils;

import android.os.Bundle;
import com.qsoft.eip.common.annotation.SaveActivityState;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Le
 * Date: 10/16/13
 */
public class StateUtils
{
    public static final Map<String, Object> applicationScopeStore = new HashMap<String, Object>();

    public static void onSaveInstanceState(Object target, Bundle outState)
    {
        for (Field member : ClassUtils.getAllFields(target.getClass()))
        {
            if (!member.isAccessible())
            {
                member.setAccessible(true);
            }
            try
            {
                if (member.isAnnotationPresent(SaveActivityState.class))
                {
                    SaveActivityState saveActivityState = member.getAnnotation(SaveActivityState.class);
                    switch (saveActivityState.value())
                    {
                        case WITHIN_ACTIVITY:
                            saveToBundle(target, outState, member);
                            break;
                        case APPLICATION:
                            saveToApplicationStore(target, member);
                            break;
                    }
                }
            }
            catch (Exception e)
            {
                LogUtils.debugLog(target, e.getMessage(), e);
            }
        }
    }

    private static void saveToApplicationStore(Object target, Field member) throws IllegalAccessException
    {
        if (!member.isAccessible())
        {
            member.setAccessible(true);
        }
        applicationScopeStore.put(member.getName(), member.get(target));
    }

    private static void saveToBundle(Object target, Bundle outState, Field member) throws IllegalAccessException
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
        else if (Serializable.class.isAssignableFrom(member.getType()))
        {
            outState.putSerializable(member.getName(), (Serializable) member.get(target));
        }
        LogUtils.debugLog(StateUtils.class, "Unable to save state for " + member);
    }

    public static void loadState(Object target, Bundle savedInstanceState)
    {
        if (savedInstanceState == null)
        {
            return;
        }
        for (Field member : ClassUtils.getAllFields(target.getClass()))
        {
            if (!member.isAccessible())
            {
                member.setAccessible(true);
            }
            try
            {
                if (member.isAnnotationPresent(SaveActivityState.class))
                {
                    SaveActivityState saveActivityState = member.getAnnotation(SaveActivityState.class);
                    switch (saveActivityState.value())
                    {
                        case WITHIN_ACTIVITY:
                            loadFromBundle(target, savedInstanceState, member);
                            break;
                        case APPLICATION:
                            loadFromApplicationStore(target, member);
                            break;
                    }
                }
            }
            catch (Exception e)
            {
                LogUtils.debugLog(target, e.getMessage(), e);
            }
        }
    }

    private static void loadFromApplicationStore(Object target, Field member) throws IllegalAccessException
    {
        if (!member.isAccessible())
        {
            member.setAccessible(true);
        }
        member.set(target, applicationScopeStore.get(member.getName()));
    }

    private static void loadFromBundle(Object target, Bundle savedInstanceState, Field member) throws IllegalAccessException
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
        else if (Serializable.class.isAssignableFrom(member.getType()))
        {
            member.set(target, savedInstanceState.getSerializable(member.getName()));
        }
        LogUtils.debugLog(StateUtils.class, "Unable to load state for " + member);
    }
}
