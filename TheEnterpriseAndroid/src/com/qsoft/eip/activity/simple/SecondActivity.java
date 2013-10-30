package com.qsoft.eip.activity.simple;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.qsoft.eip.R;
import com.qsoft.eip.activity.SuperActivity;
import com.qsoft.eip.activity.model.SimpleModel;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.ViewMapping;
import com.qsoft.eip.common.utils.ClassUtils;

/**
 * User: Le
 * Date: 10/21/13
 */
@ViewMapping(R.layout.activity_second)
public class SecondActivity extends SuperActivity
{
    @GUIMapping(R.id.textedit_content)
    private EditText contextTextEdit;

    @GUIMapping(R.id.button_ok)
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        model = getIntent().getSerializableExtra("model");
        okButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SimpleModel simpleModel = (SimpleModel) model;
                simpleModel.setContent(contextTextEdit.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("model", simpleModel);
                setResult(ClassUtils.getViewMappingId(this.getClass()), intent);
                finish();
            }
        });
    }
}
