package com.qsoft.samples.FirstAndroid;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.qsoft.samples.FirstAndroid.utils.GUIUtils;

/**
 * User: Le
 * Date: 10/11/13
 */
public class SuperFragment extends Fragment
{
    protected View mCurrentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mCurrentView = inflater.inflate(this.getClass().getAnnotation(ViewMapping.class).value(), container, false);
        GUIUtils.mapUIComponents(mCurrentView, this);
        onAfterCreateView();
        return mCurrentView;
    }

    public void onAfterCreateView() {

    }
}
