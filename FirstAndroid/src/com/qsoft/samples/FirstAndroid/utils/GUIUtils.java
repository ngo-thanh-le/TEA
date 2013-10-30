package com.qsoft.samples.FirstAndroid.utils;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import com.qsoft.samples.FirstAndroid.ComponentMapping;

import java.lang.reflect.Field;

/**
 * User: Le
 * Date: 10/16/13
 */
public class GUIUtils
{
    public static void mapUIComponents(View view, Object originalController)
    {
        if (view == null)
        {
            view = originalController instanceof Activity ?
                    ((Activity) originalController).getWindow().getDecorView().findViewById(android.R.id.content)
                    : ((Fragment) originalController).getView();
        }
        for (Field member : ClassUtils.getAllFields(originalController.getClass()))
        {
            if (!member.isAccessible())
            {
                member.setAccessible(true);
            }
            try
            {
                if (member.isAnnotationPresent(ComponentMapping.class))
                {
                    Object uiComponent = view.findViewById(member.getAnnotation(ComponentMapping.class).value());
                    if (uiComponent != null && member.getType().isAssignableFrom(uiComponent.getClass()))
                    {
                        member.set(originalController, uiComponent);
                    }
                    else
                    {
                        LogUtils.debugLog(originalController, String.format("Unable to assign %s to %s", uiComponent, member));
                    }
                }
            }
            catch (Exception e)
            {
                LogUtils.debugLog(originalController, e.getMessage(), e);
            }
        }
    }
}
