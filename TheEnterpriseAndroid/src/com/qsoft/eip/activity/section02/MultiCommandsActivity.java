package com.qsoft.eip.activity.section02;

import android.widget.Button;
import com.qsoft.eip.R;
import com.qsoft.eip.activity.SuperActivity;
import com.qsoft.eip.activity.section02.commands.SimpleCommandWithParameters;
import com.qsoft.eip.common.annotation.Command;
import com.qsoft.eip.common.annotation.Commands;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.ViewMapping;

/**
 * User: Le
 * Date: 10/30/13
 */
@ViewMapping(R.layout.section02_commandframwork_activity_multi_commands)
public class MultiCommandsActivity extends SuperActivity
{
    @GUIMapping(R.id.button_command_simple)
    @Commands({
            @Command(value = SimpleCommandWithParameters.class, parameters = {"Simple Command Two Executed"}) ,
            @Command(value = SimpleCommandWithParameters.class, parameters = {"Simple Command One Executed"})

    })
    private Button buttonCommandSimple;
}