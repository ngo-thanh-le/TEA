package com.qsoft.eip.common.utils;

import android.R;
import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import com.qsoft.eip.common.annotation.GUIMapping;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Le
 * Date: 10/16/13
 */
public class GUIUtils
{
    public static Map<Integer, Object> mapUIComponents(Activity activity) {
        return mapUIComponents(activity.getWindow().getDecorView().findViewById(R.id.content), activity);
    }

    public static Map<Integer, Object> mapUIComponents(Fragment fragment) {
        return mapUIComponents(fragment.getView(), fragment);
    }

    public static Map<Integer, Object> mapUIComponents(View view, Object originalController)
    {
        Map<Integer, Object> mappedEntries = new HashMap<Integer, Object>();
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
                if (member.isAnnotationPresent(GUIMapping.class))
                {
                    int idValue = member.getAnnotation(GUIMapping.class).value();
                    Object uiComponent = view.findViewById(idValue);
                    if (uiComponent != null && member.getType().isAssignableFrom(uiComponent.getClass()))
                    {
                        member.set(originalController, uiComponent);
                        mappedEntries.put(idValue, uiComponent);
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
        return mappedEntries;
    }
}
