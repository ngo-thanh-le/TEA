package com.qsoft.eip.tutorials.section04;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.qsoft.eip.R;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.SaveActivityState;
import com.qsoft.eip.common.annotation.ViewMapping;

/**
 * User: Le
 * Date: 10/31/13
 */
@ViewMapping(R.layout.section04_statemanagement_activity_being_destroy)
public class RetainedWithinActivity extends SuperActivity
{
    @GUIMapping(R.id.button_click_to_set)
    private Button buttonClickToSet;

    @GUIMapping(R.id.textView_is_state_retained)
    private TextView textViewIsStateRetained;

    @SaveActivityState
    private Boolean isStateIsRetained;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        buttonClickToSet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                isStateIsRetained = true;
                setTextValue();
            }
        });
        if (isStateIsRetained == null) {
            return;
        }
        setTextValue();

    }

    private void setTextValue()
    {
        textViewIsStateRetained.setText(isStateIsRetained ? "My state is stored/retained" : "My state is not retained");
    }
}