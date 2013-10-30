package com.qsoft.samples.FirstAndroid;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.qsoft.samples.FirstAndroid.utils.StateUtils;

/**
 * User: Le
 * Date: 10/11/13
 */
public class SuperActivity extends Activity
{
    protected String TAG = this.getClass().getSimpleName();

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "super.SuperActivity.onSaveInstanceState()");
        StateUtils.onSaveInstanceState(this, outState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getActionBar();
            actionBar.setSubtitle("Bodies of Water");
        }
    }
}
