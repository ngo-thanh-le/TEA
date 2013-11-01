package com.qsoft.eip.tutorials.section04;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Le
 * Date: 11/1/13
 */
public class StaticThreadedMapping
{
    private static Map<Integer, Runnable> maintainedThread = new HashMap<Integer, Runnable>();

    public static Map<Integer, Runnable> getMaintainedThread()
    {
        return maintainedThread;
    }
}
