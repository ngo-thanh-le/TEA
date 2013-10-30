package com.qsoft.eip.activity.complex.fragment;

import android.widget.EditText;
import com.qsoft.eip.R;
import com.qsoft.eip.activity.SuperFragment;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.ViewMapping;

/**
 * User: Le
 * Date: 10/22/13
 */
@ViewMapping(R.layout.fragment_simple)
public class SimpleFragment extends SuperFragment
{
    @GUIMapping(R.id.editText_content_from_fragment_activity)
    private EditText contentEditText;
}
