package com.qsoft.eip.activity.section03;

import android.widget.Button;
import android.widget.ProgressBar;
import com.qsoft.eip.R;
import com.qsoft.eip.activity.SuperFragment;
import com.qsoft.eip.activity.section03.commands.*;
import com.qsoft.eip.common.annotation.Command;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.ViewMapping;

/**
 * User: Le
 * Date: 10/29/13
 */
@ViewMapping(R.layout.fragment_multiple_progress)
public class MultipleProgressFragment01 extends SuperFragment
{
    @GUIMapping(R.id.button_run_one)
    @Command(CommandStartProgress1.class)
    private Button buttonRunOne;

    @GUIMapping(R.id.button_run_two)
    @Command(CommandStartProgress2.class)
    private Button buttonRunTwo;

    @GUIMapping(R.id.button_run_three)
    @Command(CommandStartProgress3.class)
    private Button buttonRunThree;

    @GUIMapping(R.id.button_run_forth)
    @Command(CommandStartProgress4.class)
    private Button buttonRunForth;

    @GUIMapping(R.id.button_run_five)
    @Command(CommandStartProgress5.class)
    private Button buttonRunFive;

    @GUIMapping(R.id.button_run_six)
    @Command(CommandStartProgress6.class)
    private Button buttonRunSix;

    @GUIMapping(R.id.button_run_seven)
    @Command(CommandStartProgress7.class)
    private Button buttonRunSeven;

    @GUIMapping(R.id.button_run_eight)
    @Command(CommandStartProgress8.class)
    private Button buttonRunEight;

    @GUIMapping(R.id.progressBar_run_one)
    private ProgressBar progressBarRunOne;

    @GUIMapping(R.id.progressBar_run_two)
    private ProgressBar progressBarRunTwo;

    @GUIMapping(R.id.progressBar_run_three)
    private ProgressBar progressBarRunThree;

    @GUIMapping(R.id.progressBar_run_forth)
    private ProgressBar progressBarRunForth;

    @GUIMapping(R.id.progressBar_run_five)
    private ProgressBar progressBarRunFive;

    @GUIMapping(R.id.progressBar_run_six)
    private ProgressBar progressBarRunSix;

    @GUIMapping(R.id.progressBar_run_seven)
    private ProgressBar progressBarRunSeven;

    @GUIMapping(R.id.progressBar_run_eight)
    private ProgressBar progressBarRunEight;
}
