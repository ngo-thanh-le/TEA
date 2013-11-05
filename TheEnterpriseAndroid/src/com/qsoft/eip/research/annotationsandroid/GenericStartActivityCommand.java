package com.qsoft.eip.research.annotationsandroid;

import android.content.Intent;
import com.qsoft.eip.common.Constants;
import com.qsoft.eip.common.ICommandExecutable;
import com.qsoft.eip.common.IExchangeEvent;
import com.qsoft.eip.common.SuperAnnotationActivity;
import com.qsoft.eip.common.utils.LogUtils;
import com.qsoft.eip.tutorials.simple.model.SimpleModel;

import java.io.Serializable;

/**
 * User: Le
 * Date: 10/21/13
 */
public class GenericStartActivityCommand implements ICommandExecutable, IExchangeEvent
{
// ------------------------------ FIELDS ------------------------------

    private Class openingActivityClass;
    private SuperAnnotationActivity activity;
    private int requestCode;
    private Serializable model;

// --------------------- GETTER / SETTER METHODS ---------------------

    public void setActivity(SuperAnnotationActivity activity)
    {
        this.activity = activity;
    }

    public void setModel(Serializable model)
    {
        this.model = model;
    }

    public void setOpeningActivityClass(Class openingActivityClass)
    {
        this.openingActivityClass = openingActivityClass;
    }

    public void setRequestCode(int requestCode)
    {
        this.requestCode = requestCode;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface ICommandExecutable ---------------------

    @Override
    public void execute() throws Exception
    {
        LogUtils.debugLog(this, "GenericStartActivityCommand execute()");
        Intent intent = new Intent(activity, openingActivityClass);
        intent.putExtra(Constants.SOURCE_MODEL, model);
        requestCode = UniqueRequestCodeGenerator.getNext();
        intent.putExtra(Constants.REQUEST_CODE, requestCode);
        activity.addExchangeQueue(requestCode, this);
        activity.startActivityForResult(intent, requestCode);
    }

// --------------------- Interface IExchangeEvent ---------------------


    @Override
    public void updateModel(Object model, Intent returnData, int resultCode)
    {
        ((SimpleModel) model).setContent(((SimpleModel) returnData.getSerializableExtra(Constants.RETURN_MODEL)).getContent());
    }
}
