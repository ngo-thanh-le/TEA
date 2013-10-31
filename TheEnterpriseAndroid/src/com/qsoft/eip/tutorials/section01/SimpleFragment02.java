package com.qsoft.eip.tutorials.section01;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.qsoft.eip.R;
import com.qsoft.eip.common.SuperFragment;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.ViewMapping;

/**
 * User: Le
 * Date: 10/30/13
 */
@ViewMapping(R.layout.section01_guimapping_activity_simple_2)
public class SimpleFragment02 extends SuperFragment
{
    @GUIMapping(R.id.button_i_binded)
    private Button buttonIBinded;

    @Override
    public void onAfterCreateView()
    {
        buttonIBinded.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast toast = Toast.makeText(getActivity(), "Button clicked", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}