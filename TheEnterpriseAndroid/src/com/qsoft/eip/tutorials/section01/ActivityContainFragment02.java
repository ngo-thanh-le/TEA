package com.qsoft.eip.tutorials.section01;

import android.app.Fragment;

/**
 * User: Le
 * Date: 10/30/13
 */
public class ActivityContainFragment02 extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        return new SimpleFragment02();
    }
}
