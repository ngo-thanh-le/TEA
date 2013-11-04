package com.qsoft.eip.research.annotationsandroid;

import android.widget.EditText;
import com.googlecode.androidannotations.annotations.*;
import com.qsoft.eip.R;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.tutorials.simple.SecondActivity;

/**
 * User: Le
 * Date: 11/4/13
 */
@EBean
public class CustomCommand
{
    @ViewById(R.id.editText_content_from_activity)
    EditText contentFromActivity;

    @Bean
    CommandExecutor commandExecutor;

    // Only injected if the root context is an activity
    @RootContext
    SuperActivity activity;

    @Click(R.id.button_start_activity)
    void handleButtonClick()
    {
        GenericStartActivityCommand command = new GenericStartActivityCommand();
        command.setActivityClass(SecondActivity.class);
        command.setActivity(activity);
//        command.setRequestCode(SecondActivity.REQUEST_CODE);
        command.setModel(activity.getModel());
        commandExecutor.execute(activity, command, false);
    }


}
