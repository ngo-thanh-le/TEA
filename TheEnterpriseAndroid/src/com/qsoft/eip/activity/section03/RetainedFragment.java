package com.qsoft.eip.activity.section03;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * User: Le
 * Date: 10/29/13
 */
public class RetainedFragment extends Fragment
{
    private ProgressBar mProgressBar;
    private int mProgressBarId;
    int mPosition;
    boolean mReady = false;
    boolean mQuiting = false;
    private Runnable mRunnable;
    Thread mThread;

    /**
     * Fragment initialization.  We way we want to be retained and
     * start our thread.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Tell the framework to try to keep this fragment around
        // during a configuration change.
        setRetainInstance(true);
    }

    public void start()
    {
        // Start up the worker thread.
        mThread = new Thread(getRunnable());
        mThread.start();
    }

    /**
     * This is called when the Fragment's Activity is ready to go, after
     * its content view has been installed; it is called both after
     * the initial fragment creation and after the fragment is re-attached
     * to a new activity.
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        // Retrieve the progress bar from the target's view hierarchy.
        mProgressBar = ((ProgressBar) getTargetFragment().getView().findViewById(
                mProgressBarId));

        // We are ready for our thread to go.
        synchronized (mThread)
        {
            mReady = true;
            mThread.notify();
        }
    }

    /**
     * This is called when the fragment is going away.  It is NOT called
     * when the fragment is being propagated between activity instances.
     */
    @Override
    public void onDestroy()
    {
        // Make the thread go away.
        synchronized (mThread)
        {
            mReady = false;
            mQuiting = true;
            mThread.notify();
        }

        super.onDestroy();
    }

    /**
     * This is called right before the fragment is detached from its
     * current activity instance.
     */
    @Override
    public void onDetach()
    {
        // This fragment is being detached from its activity.  We need
        // to make sure its thread is not going to touch any activity
        // state after returning from this function.
        synchronized (mThread)
        {
            mProgressBar = null;
            mReady = false;
            mThread.notify();
        }

        super.onDetach();
    }

    /**
     * API for our UI to restart the progress thread.
     */
    public void restart()
    {
        synchronized (mThread)
        {
            mPosition = 0;
            mThread.notify();
        }
    }

    public Runnable getRunnable()
    {
        return mRunnable;
    }

    public void setRunnable(Runnable mRunnable)
    {
        this.mRunnable = mRunnable;
    }

    public void setProgressBarId(int progressBarId)
    {
        this.mProgressBarId = progressBarId;
    }

    public void setPossition(int mPossition)
    {
        this.mPosition = mPossition;
        if (mProgressBar != null)
            mProgressBar.setProgress(mPossition);
    }
}