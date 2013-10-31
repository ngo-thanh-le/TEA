package com.qsoft.eip.tutorials.simple.command;

import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.common.IActivityCommand;
import com.qsoft.eip.common.IModelContainer;
import com.qsoft.eip.common.utils.LogUtils;

/**
 * User: Le
 * Date: 10/23/13
 */
public class CommandTwo implements IActivityCommand
{
    @Override
    public void execute(SuperActivity activity, IModelContainer modelable, String... parameters)
    {
        LogUtils.debugLog(this, "Command Two()");
    }
}
