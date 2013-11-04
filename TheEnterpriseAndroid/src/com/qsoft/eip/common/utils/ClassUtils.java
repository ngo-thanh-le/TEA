package com.qsoft.eip.common.utils;

import com.qsoft.eip.common.annotation.ViewMapping;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: Le
 * Date: 10/16/13
 */
public class ClassUtils
{
    public static List<Field> getAllFields(Class clazz)
    {
        List<Field> results = new ArrayList<Field>();

        Class superClass = clazz;
        while (superClass != Object.class)
        {
            results.addAll(Arrays.asList(superClass.getFields()));
            results.addAll(Arrays.asList(superClass.getDeclaredFields()));
            superClass = superClass.getSuperclass();
        }

        return results;
    }

    public static int getViewMappingId(Class<?> activityClass)
    {
        if (activityClass.isAnnotationPresent(ViewMapping.class))
        {
            return activityClass.getAnnotation(ViewMapping.class).value();
        }
        else
        {
            return -1;
        }
    }
}
