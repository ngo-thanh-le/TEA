package com.qsoft.eip.tutorials.section02.commands;

import android.widget.Toast;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.common.IActivityCommand;
import com.qsoft.eip.common.IModelContainer;

/**
 * User: Le
 * Date: 10/30/13
 */
public class SimpleCommand implements IActivityCommand
{
    @Override
    public void execute(SuperActivity activity, IModelContainer modelable, String... parameters)
    {
        Toast toast = Toast.makeText(activity, "Command Executed", Toast.LENGTH_SHORT);
        toast.show();
    }
}
