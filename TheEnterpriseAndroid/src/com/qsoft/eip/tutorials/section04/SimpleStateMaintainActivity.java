package com.qsoft.eip.tutorials.section04;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.qsoft.eip.R;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.SaveActivityState;
import com.qsoft.eip.common.annotation.ViewMapping;

/**
 * User: Le
 * Date: 10/30/13
 */
@ViewMapping(R.layout.section04_statemanagement_activity_simple_state_maintain)
public class SimpleStateMaintainActivity extends SuperActivity
{
    @GUIMapping(R.id.button_click_or_not)
    private Button buttonClickOrNot;

    @SaveActivityState
    private boolean isClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        updateButton();
        buttonClickOrNot.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                isClicked = true;
                updateButton();
            }
        });
    }

    private void updateButton()
    {
        buttonClickOrNot.setText(!isClicked ? "You didn't click yet" : "You clicked");
    }
}