package com.qsoft.eip.tutorials.section02;

import android.widget.Button;
import com.qsoft.eip.R;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.tutorials.section02.commands.SimpleCommand;
import com.qsoft.eip.common.annotation.Command;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.ViewMapping;

/**
 * User: Le
 * Date: 10/30/13
 */
@ViewMapping(R.layout.section02_commandframwork_activity_simple_command)
public class SingleCommandActivity extends SuperActivity
{
    @GUIMapping(R.id.button_command_simple)
    @Command(SimpleCommand.class)
    private Button buttonCommandSimple;
}