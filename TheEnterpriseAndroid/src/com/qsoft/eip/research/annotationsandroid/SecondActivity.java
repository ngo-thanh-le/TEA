package com.qsoft.eip.research.annotationsandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.qsoft.eip.R;
import com.qsoft.eip.common.Constants;
import com.qsoft.eip.common.SuperAnnotationActivity;
import com.qsoft.eip.tutorials.simple.model.SimpleModel;

/**
 * User: Le
 * Date: 10/21/13
 */
@EActivity(R.layout.activity_second)
public class SecondActivity extends SuperAnnotationActivity
{
    @ViewById(R.id.textedit_content)
    EditText contextTextEdit;

    @ViewById(R.id.button_ok)
    Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void afterViews()
    {
        model = getIntent().getSerializableExtra(Constants.SOURCE_MODEL);
        requestCode = getIntent().getIntExtra(Constants.REQUEST_CODE, 0);
        okButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SimpleModel simpleModel = (SimpleModel) model;
                simpleModel.setContent(contextTextEdit.getText().toString());
                Intent intent = new Intent();
                intent.putExtra(Constants.RETURN_MODEL, simpleModel);
                setResult(requestCode, intent);
                finish();
            }
        });
    }
}
