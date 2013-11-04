package com.qsoft.eip.research.annotationsandroid;

import android.content.Intent;
import com.qsoft.eip.common.ICommandExecutable;
import com.qsoft.eip.common.IExchangeEvent;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.common.utils.LogUtils;
import com.qsoft.eip.tutorials.simple.model.SimpleModel;

import java.io.Serializable;

/**
 * User: Le
 * Date: 10/21/13
 */
public class GenericStartActivityCommand implements ICommandExecutable, IExchangeEvent
{
    private Class activityClass;
    private SuperActivity activity;
    private int requestCode;
    private Serializable model;

    @Override
    public void execute() throws Exception
    {
        LogUtils.debugLog(this, "StartActivity execute()");
        Intent intent = new Intent(activity, activityClass);
        intent.putExtra("model", model);
//        int requestCode = ClassUtils.getViewMappingId(activityClass);
//        requestCode = activity.hashCode();
        requestCode = UniqueRequestCodeGenerator.getNext();
        intent.putExtra("requestCode", requestCode);
        activity.addExchangeQueue(requestCode, this);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    public void updateModel(Object model, Intent returnData)
    {
        ((SimpleModel) model).setContent(((SimpleModel) returnData.getSerializableExtra("model")).getContent());
    }

    public void setActivityClass(Class activityClass)
    {
        this.activityClass = activityClass;
    }

    public void setActivity(SuperActivity activity)
    {
        this.activity = activity;
    }

    public void setModel(Serializable model)
    {
        this.model = model;
    }

    public void setRequestCode(int requestCode)
    {
        this.requestCode = requestCode;
    }
}
