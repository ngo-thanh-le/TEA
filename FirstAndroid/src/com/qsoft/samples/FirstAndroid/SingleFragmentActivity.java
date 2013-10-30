package com.qsoft.samples.FirstAndroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * User: Le
 * Date: 10/14/13
 */
public abstract class SingleFragmentActivity extends SuperActivity
{
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}
