package com.qsoft.eip.tutorials.simple;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.qsoft.eip.R;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.ViewMapping;
import com.qsoft.eip.tutorials.simple.model.SimpleModel;

/**
 * User: Le
 * Date: 10/21/13
 */
@ViewMapping(R.layout.activity_second)
public class SecondActivity extends SuperActivity
{
    public final static int REQUEST_CODE = 10001;

    @GUIMapping(R.id.textedit_content)
    private EditText contextTextEdit;

    @GUIMapping(R.id.button_ok)
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        model = getIntent().getSerializableExtra("model");
        requestCode = getIntent().getIntExtra("requestCode", 0);
        okButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SimpleModel simpleModel = (SimpleModel) model;
                simpleModel.setContent(contextTextEdit.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("model", simpleModel);
                setResult(requestCode, intent);
                finish();
            }
        });
    }
}
