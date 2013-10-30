package com.qsoft.samples.FirstAndroid.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Le
 * Date: 10/14/13
 * Time: 9:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class Crime
{
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public String getTitle()
    {
        return mTitle;
    }

    public void setTitle(String mTitle)
    {
        this.mTitle = mTitle;
    }

    public UUID getId()
    {
        return mId;
    }

    public boolean isSolved()
    {
        return mSolved;
    }

    public void setSolved(boolean mSolved)
    {
        this.mSolved = mSolved;
    }

    public Date getDate()
    {
        return mDate;
    }

    public void setDate(Date mDate)
    {
        this.mDate = mDate;
    }

    @Override
    public String toString()
    {
        return mTitle;
    }
}
