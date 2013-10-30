package com.qsoft.samples.FirstAndroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import com.qsoft.samples.FirstAndroid.fragment.CrimeFragment;
import com.qsoft.samples.FirstAndroid.model.Crime;
import com.qsoft.samples.FirstAndroid.model.CrimeLab;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Le
 * Date: 10/14/13
 * Time: 9:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class CrimePagerActivity extends SuperActivity
{
    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);
        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fm = getFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm)
        {
            @Override
            public int getCount()
            {
                return mCrimes.size();
            }

            @Override
            public Fragment getItem(int pos)
            {
                Crime crime = mCrimes.get(pos);
                return CrimeFragment.newInstance(crime.getId());
            }
        });

        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for (int i = 0; i < mCrimes.size(); i++)
        {
            if (mCrimes.get(i).getId().equals(crimeId))
            {
                setTitle(mCrimes.get(i).getTitle());
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            public void onPageScrollStateChanged(int state)
            {
            }

            public void onPageScrolled(int pos, float posOffset, int position)
            {
            }

            public void onPageSelected(int pos)
            {
                Crime crime = mCrimes.get(pos);
                if (crime.getTitle() != null)
                {
                    setTitle(crime.getTitle());
                }
            }
        });
    }
}
