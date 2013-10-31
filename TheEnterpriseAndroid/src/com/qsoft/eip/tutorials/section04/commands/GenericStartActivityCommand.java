package com.qsoft.eip.tutorials.section04.commands;

import android.content.Intent;
import com.qsoft.eip.common.IActivityCommand;
import com.qsoft.eip.common.IExchangeEvent;
import com.qsoft.eip.common.IModelContainer;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.common.utils.ClassUtils;
import com.qsoft.eip.common.utils.LogUtils;
import com.qsoft.eip.tutorials.simple.SecondActivity;
import com.qsoft.eip.tutorials.simple.model.SimpleModel;

/**
 * User: Le
 * Date: 10/21/13
 */
public class GenericStartActivityCommand implements IActivityCommand, IExchangeEvent
{
    @Override
    public void execute(SuperActivity activity, IModelContainer modelContainer, String... parameters) throws Exception
    {
        LogUtils.debugLog(this, "StartActivity execute()");
        Class activityClass = Class.forName(parameters[0]);
        Intent intent = new Intent(activity, activityClass);
        intent.putExtra("model", modelContainer.getModel());
        int requestCode = ClassUtils.getViewMappingId(activityClass);
        activity.addExchangeQueue(requestCode, this);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    public void updateModel(Object model, Intent returnData)
    {
    }
}
