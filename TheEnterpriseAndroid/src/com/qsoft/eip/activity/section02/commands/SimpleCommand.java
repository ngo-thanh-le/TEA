package com.qsoft.eip.activity.section02.commands;

import android.widget.Toast;
import com.qsoft.eip.activity.SuperActivity;
import com.qsoft.eip.common.IActivityCommand;
import com.qsoft.eip.common.IModelContainer;

import java.sql.SQLException;

/**
 * User: Le
 * Date: 10/30/13
 */
public class SimpleCommand implements IActivityCommand
{
    @Override
    public void execute(SuperActivity activity, IModelContainer modelable, String... parameters) throws SQLException
    {
        Toast toast = Toast.makeText(activity, "Command Executed", Toast.LENGTH_SHORT);
        toast.show();
    }
}
