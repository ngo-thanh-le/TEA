package com.qsoft.eip.common;

//import com.qsoft.eip.tutorials.section03.RetainedFragment;

import java.sql.SQLException;

/**
 * User: Le
 * Date: 11/1/13
 */
public abstract class AbstractRunnableFragmentCommand implements IFragmentCommand, Runnable
{
//    private static int count = 0;
//
//    protected SuperFragment fragmentRef;
//
//    protected RetainedFragment retainedFragmentRef;
//
//    protected IModelContainer modelRef;
//
//    protected String[] parametersRef;
//
//    @Override
//    public void execute(final SuperFragment fragment, IModelContainer modelable, String... parameters) throws SQLException
//    {
//        final RetainedFragment newRetainedFragment = new RetainedFragment();
//        AbstractRunnableFragmentCommand.this.fragmentRef = fragment;
//        AbstractRunnableFragmentCommand.this.retainedFragmentRef = newRetainedFragment;
//        AbstractRunnableFragmentCommand.this.modelRef = modelable;
//        AbstractRunnableFragmentCommand.this.parametersRef = parameters;
//        newRetainedFragment.setTargetFragment(fragment, ((Double) Math.random()).intValue());
//        newRetainedFragment.setProgressBarId(getProgressBarId());
//        newRetainedFragment.setRunnable(this);
//        fragment.getFragmentManager().beginTransaction().add(newRetainedFragment, "run_" + count++).commit();
//        newRetainedFragment.start();
//    }
//
//    public abstract int getProgressBarId();
}
