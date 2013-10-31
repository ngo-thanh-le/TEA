package com.qsoft.eip.tutorials.section02.commands;

import android.widget.Toast;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.common.IActivityCommand;
import com.qsoft.eip.common.IModelContainer;
import com.qsoft.eip.common.utils.LogUtils;

import java.sql.SQLException;

/**
 * User: Le
 * Date: 10/30/13
 */
public class SimpleCommandWithParameters implements IActivityCommand
{
    @Override
    public void execute(SuperActivity activity, IModelContainer modelable, String... parameters) throws SQLException
    {
        for (String parameter : parameters)
        {
            Toast toastFromParameter = Toast.makeText(activity, parameter, Toast.LENGTH_SHORT);
            toastFromParameter.show();
            LogUtils.debugLog(this, parameter);
        }
    }
}
