package com.qsoft.samples.FirstAndroid;

import android.app.Fragment;
import com.qsoft.samples.FirstAndroid.fragment.CrimeFragment;

import java.util.UUID;

/**
 * User: Le
 * Date: 10/14/13
 */
public class CrimeActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}
