package com.qsoft.eip.activity.section01;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import com.qsoft.eip.R;
import com.qsoft.eip.activity.SuperActivity;
import com.qsoft.eip.common.annotation.ViewMapping;

/**
 * User: Le
 * Date: 10/14/13
 */
@ViewMapping(R.layout.activity_fragment)
public abstract class SingleFragmentActivity extends SuperActivity
{
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
