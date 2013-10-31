package com.qsoft.eip.tutorials.section04;

import android.widget.Button;
import com.qsoft.eip.R;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.common.annotation.Command;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.ViewMapping;
import com.qsoft.eip.tutorials.section04.commands.GenericStartActivityCommand;

/**
 * User: Le
 * Date: 10/31/13
 */
@ViewMapping(R.layout.section04_statemanagement_activity_original)
public class OriginalActivity extends SuperActivity
{
    @GUIMapping(R.id.button_go_to_being_destroy_activity)
    @Command(value = GenericStartActivityCommand.class, parameters = {"com.qsoft.eip.tutorials.section04.BeingDestroyActivity"})
    private Button buttonGoToBeingDestroyActivity;

    @GUIMapping(R.id.button_go_to_retained_within_activity)
    @Command(value = GenericStartActivityCommand.class, parameters = {"com.qsoft.eip.tutorials.section04.RetainedWithinActivity"})
    private Button buttonGoToBeingRetainedWithinActivity;

    @GUIMapping(R.id.button_go_being_retained_in_application)
    @Command(value = GenericStartActivityCommand.class, parameters = {"com.qsoft.eip.tutorials.section04.RetainedInApplicationActivity"})
    private Button buttonGoToRetainedWithinApplicationActivity;
}
