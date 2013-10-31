package com.qsoft.eip.tutorials.simple;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.qsoft.eip.R;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.tutorials.simple.command.CommandOne;
import com.qsoft.eip.tutorials.simple.command.CommandTwo;
import com.qsoft.eip.tutorials.simple.command.StartActivity;
import com.qsoft.eip.tutorials.simple.model.SimpleModel;
import com.qsoft.eip.common.annotation.*;

@ViewMapping(R.layout.activity_main)
public class MainActivity extends SuperActivity
{
    @GUIMapping(R.id.button_start_activity)
    @Command(value = StartActivity.class, event = View.OnClickListener.class)
    private Button buttonStartActivity;

    @GUIMapping(R.id.editText_content_from_activity)
    @ModelBinding("Content")
    private EditText contentFromActivity;

    @GUIMapping(R.id.button_start_activity_fragment)
    @Commands({
            @Command(value = CommandOne.class, event = View.OnClickListener.class),
            @Command(value = CommandTwo.class, event = View.OnClickListener.class)
    })
    private Button buttonStartActivityFragment;

    @GUIMapping(R.id.button_start_fragment)
    private Button buttonStartFragment;

    @GUIMapping(R.id.button_start_dialog)
    private Button buttonStartDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Simple model
        SimpleModel modelForThis = new SimpleModel();
        modelForThis.setContent("Content content to be");
        setModel(modelForThis);
    }
}
