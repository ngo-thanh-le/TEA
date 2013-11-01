package com.qsoft.eip.tutorials.section04;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.qsoft.eip.R;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.ViewMapping;

/**
 * User: Le
 * Date: 11/1/13
 */
@ViewMapping(R.layout.section04_statemanagement_activity_threaded)
public class ThreadedActivity extends SuperActivity
{
    @GUIMapping(R.id.button_start_a_thread)
    private Button buttonStartAThread;

    @GUIMapping(R.id.button_start_a_thread_static)
    private Button buttonStartAThreadStatic;

    @GUIMapping(R.id.button_start_a_thread_singleton)
    private Button buttonStartAThreadSingleton;

    @GUIMapping(R.id.progressBar_start_a_thread)
    private ProgressBar progressBarStartAThread;

    public static final int THREAD_ID = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        buttonStartAThread.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                createRunningThread();
            }
        });
        buttonStartAThreadStatic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                RunningIt thread = (RunningIt) createRunningThread();
                StaticThreadedMapping.getMaintainedThread().put(THREAD_ID, thread);
            }
        });
        buttonStartAThreadSingleton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                RunningIt thread = (RunningIt) createRunningThread();
                SingletonThreadedMapping.getInstance().getMaintainedThread().put(THREAD_ID, thread);
            }
        });


        // Restore the reference for thread - static way
        if (StaticThreadedMapping.getMaintainedThread().get(THREAD_ID) != null)
        {
            RunningIt thread = (RunningIt) StaticThreadedMapping.getMaintainedThread().get(THREAD_ID);
            thread.setLocalProgressBar(progressBarStartAThread);
        }
        // Restore the reference for thread - singleton way
        if (SingletonThreadedMapping.getInstance().getMaintainedThread().get(THREAD_ID) != null)
        {
            RunningIt thread = (RunningIt) SingletonThreadedMapping.getInstance().getMaintainedThread().get(THREAD_ID);
            thread.setLocalProgressBar(progressBarStartAThread);
        }
    }

    private Runnable createRunningThread()
    {
        RunningIt runnable = new RunningIt();
        new Thread(runnable).start();
        return runnable;
    }

    private class RunningIt implements Runnable
    {
        private ProgressBar localProgressBar;

        @Override
        public void run()
        {
            localProgressBar = progressBarStartAThread;
            for (int i = 0; i < 100; i++)
            {
                try
                {
                    if (localProgressBar != null)
                    {
                        localProgressBar.setProgress(i);
                    }
                    // You will see trouble as change configuration
                    // make activity reload and the reference to
                    // progressBarStartAThread is no longer valid
                    // to update the GUI.
                    // We can NOT store a local/static variable/field local
                    // to maintain the ref in this case.
                    // Either use another Singleton/Static class or retain methods
                    // that we already learn in this section.
                    Thread.sleep(333);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }

        private ProgressBar getLocalProgressBar()
        {
            return localProgressBar;
        }

        private void setLocalProgressBar(ProgressBar localProgressBar)
        {
            this.localProgressBar = localProgressBar;
        }
    }
}