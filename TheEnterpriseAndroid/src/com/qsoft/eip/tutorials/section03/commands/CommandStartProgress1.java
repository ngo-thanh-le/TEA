package com.qsoft.eip.tutorials.section03.commands;

import com.qsoft.eip.R;
import com.qsoft.eip.common.AbstractRunnableFragmentCommand;

/**
 * User: Le
 * Date: 10/29/13
 */
public class CommandStartProgress1 extends AbstractRunnableFragmentCommand
{
    @Override
    public int getProgressBarId()
    {
        return R.id.progressBar_run_one;
    }

    @Override
    public void run()
    {
        RunIt.countTo100(retainedFragmentRef);
    }
}
