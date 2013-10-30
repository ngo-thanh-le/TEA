package com.qsoft.samples.FirstAndroid.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Le
 * Date: 10/16/13
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClassUtils
{
    public static List<Field> getAllFields(Class clazz)
    {
        List<Field> results = new ArrayList<Field>();
        results.addAll(Arrays.asList(clazz.getFields()));
        results.addAll(Arrays.asList(clazz.getDeclaredFields()));
        return results;
    }
}
