package com.qsoft.eip.activity.section02;

import android.widget.Button;
import com.qsoft.eip.R;
import com.qsoft.eip.activity.SuperActivity;
import com.qsoft.eip.activity.section02.commands.SimpleCommand;
import com.qsoft.eip.activity.section02.commands.SimpleCommandWithParameters;
import com.qsoft.eip.common.annotation.Command;
import com.qsoft.eip.common.annotation.Commands;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.ViewMapping;

/**
 * User: Le
 * Date: 10/30/13
 */
@ViewMapping(R.layout.section02_commandframwork_activity_reusable)
public class ReusableCommandsActivity extends SuperActivity
{
    @GUIMapping(R.id.button_command_simple)
    @Commands({
            @Command(value = SimpleCommandWithParameters.class, parameters = {"Simple Command One Executed"}),
            @Command(value = SimpleCommandWithParameters.class, parameters = {"Simple Command Two Executed"}),
            @Command(SimpleCommand.class)
    })
    private Button buttonCommandSimple;
}