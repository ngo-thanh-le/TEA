package com.qsoft.eip.tutorials.section03;

import android.os.Bundle;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.common.SuperFragment;

/**
 * User: Le
 * Date: 10/29/13
 */
public abstract class HeadlessContainerActivity extends SuperActivity
{
    public abstract SuperFragment getFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // First time init, create the UI.
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(android.R.id.content,
                    getFragment()).commit();
        }
    }
}
