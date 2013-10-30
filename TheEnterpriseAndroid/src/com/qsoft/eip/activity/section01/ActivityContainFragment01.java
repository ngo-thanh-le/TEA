package com.qsoft.eip.activity.section01;

import android.app.Fragment;
/**
 * User: Le
 * Date: 10/30/13

 */
public class ActivityContainFragment01 extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        return new SimpleFragment01();
    }
}
