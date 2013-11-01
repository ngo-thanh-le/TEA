package com.qsoft.eip.tutorials.section03.commands;

import com.qsoft.eip.tutorials.section03.RetainedFragment;

/**
 * User: Le
 * Date: 11/1/13
 */
public class RunIt
{
    public static void countTo100(RetainedFragment retainedFragment)
    {
        for (int i = 0; i < 100; i++)
        {
            retainedFragment.setPossition(i);
            synchronized (retainedFragment)
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
