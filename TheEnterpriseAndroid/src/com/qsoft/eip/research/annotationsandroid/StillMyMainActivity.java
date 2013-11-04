package com.qsoft.eip.research.annotationsandroid;

import android.widget.Button;
import android.widget.EditText;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.qsoft.eip.R;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.common.annotation.ModelBinding;
import com.qsoft.eip.tutorials.simple.model.SimpleModel;

/**
 * User: Le
 * Date: 11/4/13
 */
@EActivity(R.layout.activity_still_my_main)
public class StillMyMainActivity extends SuperActivity
{
    @ViewById(R.id.button_start_activity)
    Button buttonStartActivity;

    @ViewById(R.id.editText_content_from_activity)
    @ModelBinding("Content")
    EditText contentFromActivity;

    @Bean
    CustomCommand commandExternal;

    @AfterViews
    public void afterViews()
    {
        SimpleModel modelForThis = new SimpleModel();
        modelForThis.setContent("Content content to be");
        setModel(modelForThis);
    }
}
