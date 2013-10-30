package com.qsoft.samples.FirstAndroid.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Le
 * Date: 10/14/13
 * Time: 6:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class CrimeLab
{
    private ArrayList<Crime> mCrimes;

    private static CrimeLab sCrimeLab;
    private Context mAppContext;
    private CrimeLab(Context appContext) {
        mAppContext = appContext;
        mCrimes = new ArrayList<Crime>();

        for (int i = 0; i < 100; i++) {
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            c.setSolved(i % 2 == 0); // Every other one
            mCrimes.add(c);
        }
    }
    public static CrimeLab get(Context c) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }
    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }
    public Crime getCrime(UUID id) {
        for (Crime c : mCrimes) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }
}
