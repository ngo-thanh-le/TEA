package com.qsoft.eip.activity.section03;

import android.widget.Button;
import android.widget.ProgressBar;
import com.qsoft.eip.R;
import com.qsoft.eip.activity.SuperFragment;
import com.qsoft.eip.activity.section03.commands.*;
import com.qsoft.eip.common.annotation.Command;
import com.qsoft.eip.common.annotation.Commands;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.ViewMapping;

/**
 * User: Le
 * Date: 10/29/13
 */
@ViewMapping(R.layout.fragment_multiple_progress)
public class MultipleProgressFragment02 extends SuperFragment
{
    @GUIMapping(R.id.button_run_one)
    @Commands({
            @Command(SimpleCommand.class),
            @Command(CommandStartProgress1.class)
    })
    private Button buttonRunOne;

    @GUIMapping(R.id.button_run_two)
    @Command(CommandStartProgress2.class)
    private Button buttonRunTwo;

    @GUIMapping(R.id.button_run_three)
    @Command(CommandStartProgress3.class)
    private Button buttonRunThree;

    @GUIMapping(R.id.progressBar_run_one)
    private ProgressBar progressBarRunOne;

    @GUIMapping(R.id.progressBar_run_two)
    private ProgressBar progressBarRunTwo;

    @GUIMapping(R.id.progressBar_run_three)
    private ProgressBar progressBarRunThree;
}
