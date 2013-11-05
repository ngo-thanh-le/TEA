package com.qsoft.eip.tutorials.simple.command;

import android.content.Intent;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.tutorials.simple.model.SimpleModel;
import com.qsoft.eip.tutorials.simple.SecondActivity;
import com.qsoft.eip.common.IActivityCommand;
import com.qsoft.eip.common.IExchangeEvent;
import com.qsoft.eip.common.IModelContainer;
import com.qsoft.eip.common.utils.ClassUtils;
import com.qsoft.eip.common.utils.LogUtils;

/**
 * User: Le
 * Date: 10/21/13
 */
public class StartActivity implements IActivityCommand, IExchangeEvent
{
    @Override
    public void execute(SuperActivity activity, IModelContainer modelContainer, String... parameters)
    {
        LogUtils.debugLog(this, "StartActivity execute()");
        Intent intent = new Intent(activity, SecondActivity.class);
        intent.putExtra("model", modelContainer.getModel());
        int requestCode = ClassUtils.getViewMappingId(SecondActivity.class);
        activity.addExchangeQueue(requestCode, this);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    public void updateModel(Object model, Intent returnData, int resultCode)
    {
        ((SimpleModel) model).setContent(((SimpleModel) returnData.getSerializableExtra("model")).getContent());
    }
}
