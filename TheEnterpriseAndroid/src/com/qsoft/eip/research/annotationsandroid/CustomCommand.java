package com.qsoft.eip.research.annotationsandroid;

import android.widget.EditText;
import com.googlecode.androidannotations.annotations.*;
import com.qsoft.eip.R;
import com.qsoft.eip.common.SuperAnnotationActivity;

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
    SuperAnnotationActivity activity;

    @Click(R.id.button_start_activity)
    void handleButtonClick()
    {
        GenericStartActivityCommand command = new GenericStartActivityCommand();
        command.setOpeningActivityClass(SecondActivity_.class);
        command.setActivity(activity);
        command.setModel(activity.getModel());
        commandExecutor.execute(activity, command, false);
    }


}
