package com.qsoft.eip.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.bindroid.BindingMode;
import com.bindroid.ui.EditTextTextProperty;
import com.bindroid.ui.UiBinder;
import com.qsoft.eip.common.IExchangeEvent;
import com.qsoft.eip.common.IModelContainer;
import com.qsoft.eip.common.annotation.*;
import com.qsoft.eip.common.utils.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Le
 * Date: 10/11/13
 */
public class SuperActivity extends Activity implements IModelContainer
{
// ------------------------------ FIELDS ------------------------------

    protected String TAG = this.getClass().getSimpleName();

    @SaveActivityState(TransferScope.WITHIN_ACTIVITY)
    protected Serializable model;

    private Integer originalRequestCode;

    private Map<Integer, IExchangeEvent> exchangeQueue = new HashMap<Integer, IExchangeEvent>();

    private Map<Integer, Object> mappedUIComponents = new HashMap<Integer, Object>();

// --------------------- GETTER / SETTER METHODS ---------------------

    @Override
    public Serializable getModel()
    {
        return model;
    }

    public int getOriginalRequestCode()
    {
        if (originalRequestCode == null)
        {
            originalRequestCode = !this.getClass().isAnnotationPresent(ViewMapping.class) ?
                    ((Double) Math.random()).intValue() : this.getClass().getAnnotation(ViewMapping.class).value();
        }
        return originalRequestCode;
    }

// ------------------------ CANONICAL METHODS ------------------------

    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(super.hashCode());
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface IModelContainer ---------------------

    @Override
    public void setModel(Serializable model)
    {
        this.model = model;
        onModelLoaded();
    }

// -------------------------- OTHER METHODS --------------------------

    public void addExchangeQueue(int requestCode, IExchangeEvent exchangeEvent) {
        exchangeQueue.put(requestCode, exchangeEvent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (exchangeQueue.containsKey(requestCode)) {
            exchangeQueue.get(requestCode).updateModel(model, data);
            exchangeQueue.remove(requestCode);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        LogUtils.debugLog(this, "onCreate() called");
        StateUtils.loadState(this, savedInstanceState);
        ViewMapping viewMappingPresent = isViewMappingPresent(this.getClass());
        if (viewMappingPresent != null)
        {
            setContentView(viewMappingPresent.value());
        }
        mappedUIComponents = GUIUtils.mapUIComponents(this);
        CommandUtils.mapCommands(this);
    }

    private ViewMapping isViewMappingPresent(Class clazz) {
        ViewMapping viewMapping = null;
        Class superClazz = clazz;
        while (superClazz != Object.class) {
            if (superClazz.isAnnotationPresent(ViewMapping.class))
            {
                viewMapping = (ViewMapping) superClazz.getAnnotation(ViewMapping.class);
                break;
            }
            superClazz = superClazz.getSuperclass();
        }
        return viewMapping;
    }

    protected void onModelLoaded() {
        for (Field member : ClassUtils.getAllFields(this.getClass()))
        {
            if (!member.isAccessible())
            {
                member.setAccessible(true);
            }
            try
            {
                if (member.isAnnotationPresent(ModelBinding.class))
                {
                    Object uiComponent = member.get(this);
                    if (uiComponent instanceof EditText) {
                        UiBinder.bind(new EditTextTextProperty((EditText) uiComponent), model,
                                member.getAnnotation(ModelBinding.class).value(), BindingMode.TWO_WAY);
                    } else if (uiComponent instanceof Button) {
                    }
                    // TODO: All common type
                }
            }
            catch (Exception e)
            {
                LogUtils.debugLog(this, e.getMessage(), e);
            }
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        LogUtils.debugLog(this, "onResume() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        LogUtils.debugLog(this, "onSaveInstanceState() called");
        StateUtils.onSaveInstanceState(this, outState);
    }

    public <GUI> GUI getGUIComponent(int id, Class<GUI> type) {
       return (GUI) mappedUIComponents.get(id);
    }
}
