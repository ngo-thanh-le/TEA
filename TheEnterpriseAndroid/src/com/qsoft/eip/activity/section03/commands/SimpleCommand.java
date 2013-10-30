package com.qsoft.eip.activity.section03.commands;

import android.widget.Toast;
import com.qsoft.eip.activity.SuperFragment;
import com.qsoft.eip.common.IFragmentCommand;
import com.qsoft.eip.common.IModelContainer;

import java.sql.SQLException;

/**
 * User: Le
 * Date: 10/30/13
 */
public class SimpleCommand implements IFragmentCommand
{
    @Override
    public void execute(SuperFragment fragment, IModelContainer modelable, String... parameters) throws SQLException
    {
        Toast toast = Toast.makeText(fragment.getActivity(), "Command Executed", Toast.LENGTH_SHORT);
        toast.show();
    }
}
