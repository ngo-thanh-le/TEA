package com.qsoft.eip.tutorials.section03.commands;

import com.qsoft.eip.R;
import com.qsoft.eip.common.SuperFragment;
import com.qsoft.eip.tutorials.section03.RetainedFragment;
import com.qsoft.eip.common.IFragmentCommand;
import com.qsoft.eip.common.IModelContainer;

import java.sql.SQLException;

/**
 * User: Le
 * Date: 10/29/13
 */
public class CommandStartProgress1 implements IFragmentCommand
{
    @Override
    public void execute(final SuperFragment fragment, IModelContainer modelable, String... parameters) throws SQLException
    {
        final RetainedFragment newRetainedFragment = new RetainedFragment();
        newRetainedFragment.setTargetFragment(fragment, ((Double) Math.random()).intValue());
        newRetainedFragment.setProgressBarId(R.id.progressBar_run_one);
        newRetainedFragment.setRunnable(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 100; i++)
                {
                    newRetainedFragment.setPossition(i);
                    synchronized (this)
                    {
                        try
                        {
                            wait(1000);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        fragment.getFragmentManager().beginTransaction().add(newRetainedFragment, "run_one").commit();
        newRetainedFragment.start();
    }
}
