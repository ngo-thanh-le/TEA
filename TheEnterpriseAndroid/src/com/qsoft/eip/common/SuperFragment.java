package com.qsoft.eip.common;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.bindroid.BindingMode;
import com.bindroid.ui.EditTextTextProperty;
import com.bindroid.ui.UiBinder;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.qsoft.eip.tutorials.section03.RetainedFragment;
import com.qsoft.eip.common.annotation.ModelBinding;
import com.qsoft.eip.common.annotation.SaveActivityState;
import com.qsoft.eip.common.annotation.TransferScope;
import com.qsoft.eip.common.annotation.ViewMapping;
import com.qsoft.eip.common.utils.ClassUtils;
import com.qsoft.eip.common.utils.CommandUtils;
import com.qsoft.eip.common.utils.GUIUtils;
import com.qsoft.eip.common.utils.LogUtils;
import com.qsoft.eip.common.dao.helper.GenericDatabaseHelper;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Le
 * Date: 10/11/13
 */
public class SuperFragment extends Fragment implements IModelContainer
{
// ------------------------------ FIELDS ------------------------------

    protected View mCurrentView;

    private GenericDatabaseHelper databaseHelper = null;

    @SaveActivityState(TransferScope.WITHIN_ACTIVITY)
    private ArrayList<String> retainedFragmentStringTags;

    @SaveActivityState(TransferScope.WITHIN_ACTIVITY)
    protected Serializable model;

    private Map<Integer, Object> mappedUIComponents = new HashMap<Integer, Object>();

// -------------------------- OTHER METHODS --------------------------

    protected GenericDatabaseHelper getHelper()
    {
        if (databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(getActivity(), GenericDatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mCurrentView = inflater.inflate(this.getClass().getAnnotation(ViewMapping.class).value(), container, false);
        mappedUIComponents = GUIUtils.mapUIComponents(mCurrentView, this);
        CommandUtils.mapCommands(this);
        onAfterCreateView();
        return mCurrentView;
    }

    public void onAfterCreateView()
    {

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (databaseHelper != null)
        {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

//    Retain Fragment Section

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        if (retainedFragmentStringTags == null) {
            retainedFragmentStringTags = new ArrayList<String>();
        }
        for (String retainedFragmentStringTag : retainedFragmentStringTags) {
            RetainedFragment workFragment = (RetainedFragment) getFragmentManager().findFragmentByTag(retainedFragmentStringTag);
            if (workFragment != null)
                getFragmentManager().beginTransaction().add(workFragment, retainedFragmentStringTag).commit();
        }
    }

    @Override
    public Serializable getModel()
    {
        return model;
    }

    @Override
    public void setModel(Serializable model)
    {
        this.model = model;
        onModelLoaded();
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

    public <GUI> GUI getGUIComponent(int id, Class<GUI> type) {
        return (GUI) mappedUIComponents.get(id);
    }
}
