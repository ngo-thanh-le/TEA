package com.qsoft.eip.tutorials.section04;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Le
 * Date: 11/1/13
 */
public class SingletonThreadedMapping
{
    private Map<Integer, Runnable> maintainedThread = new HashMap<Integer, Runnable>();

    public Map<Integer, Runnable> getMaintainedThread()
    {
        return maintainedThread;
    }

    private static SingletonThreadedMapping instance;

    public static SingletonThreadedMapping getInstance()
    {
        if (instance == null)
        {
            instance = new SingletonThreadedMapping();
        }
        return instance;
    }
}
