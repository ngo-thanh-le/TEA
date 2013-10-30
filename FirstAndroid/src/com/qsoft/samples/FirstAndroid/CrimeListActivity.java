package com.qsoft.samples.FirstAndroid;

import android.app.Fragment;
import com.qsoft.samples.FirstAndroid.fragment.CrimeListFragment;

/**
 * User: Le
 * Date: 10/14/13
 */
public class CrimeListActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        return new CrimeListFragment();
    }
}
