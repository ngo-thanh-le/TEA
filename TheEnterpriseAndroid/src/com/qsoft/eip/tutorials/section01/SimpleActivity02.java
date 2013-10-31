package com.qsoft.eip.tutorials.section01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.qsoft.eip.R;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.ViewMapping;

/**
 * User: Le
 * Date: 10/30/13
 */
@ViewMapping(R.layout.section01_guimapping_activity_simple_2)
public class SimpleActivity02 extends SuperActivity
{
    @GUIMapping(R.id.button_i_binded)
    private Button buttonIBinded;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        buttonIBinded.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast toast = Toast.makeText(SimpleActivity02.this, "Button clicked", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}