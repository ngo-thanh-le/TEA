package com.qsoft.eip.activity.section05;

import android.content.Context;
import android.os.Bundle;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.qsoft.eip.activity.SuperActivity;
import com.qsoft.eip.dao.helper.GenericDatabaseHelper;

/**
 * User: Le
 * Date: 10/26/13
 */
public class OrmLiteSupportSuperActivity extends SuperActivity
{
// ------------------------------ FIELDS ------------------------------

    private static Logger logger = LoggerFactory.getLogger(OrmLiteBaseActivity.class);
    // Ormlite Database Support
    private volatile GenericDatabaseHelper helper;
    private volatile boolean created = false;
    private volatile boolean destroyed = false;

// -------------------------- OTHER METHODS --------------------------

    /**
     * Get a connection source for this action.
     */
    public ConnectionSource getConnectionSource()
    {
        return getHelper().getConnectionSource();
    }

    /**
     * Get a helper for this action.
     */
    public GenericDatabaseHelper getHelper()
    {
        if (helper == null)
        {
            if (!created)
            {
                throw new IllegalStateException("A call has not been made to onCreate() yet so the helper is null");
            }
            else if (destroyed)
            {
                throw new IllegalStateException(
                        "A call to onDestroy has already been made and the helper cannot be used after that point");
            }
            else
            {
                throw new IllegalStateException("Helper is null for some unknown reason");
            }
        }
        else
        {
            return helper;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if (helper == null)
        {
            helper = getHelperInternal(this);
            created = true;
        }
        super.onCreate(savedInstanceState);
    }

    /**
     * This is called internally by the class to populate the helper object instance. This should not be called directly
     * by client code unless you know what you are doing. Use {@link #getHelper()} to get a helper instance. If you are
     * managing your own helper creation, override this method to supply this activity with a helper instance.
     * <p/>
     * <p>
     * <b> NOTE: </b> If you override this method, you most likely will need to override the
     * {@link #releaseHelper(GenericDatabaseHelper)} method as well.
     * </p>
     */
    protected GenericDatabaseHelper getHelperInternal(Context context)
    {
        GenericDatabaseHelper newHelper = OpenHelperManager.getHelper(context, GenericDatabaseHelper.class);
        logger.trace("{}: got new helper {} from OpenHelperManager", this, newHelper);
        return newHelper;
    }

    // Start Ormlite Section          
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        releaseHelper(helper);
        destroyed = true;
    }

    /**
     * Release the helper instance created in {@link #getHelperInternal(Context)}. You most likely will not need to call
     * this directly since {@link #onDestroy()} does it for you.
     * <p/>
     * <p>
     * <b> NOTE: </b> If you override this method, you most likely will need to override the
     * {@link #getHelperInternal(Context)} method as well.
     * </p>
     */
    protected void releaseHelper(GenericDatabaseHelper helper)
    {
        OpenHelperManager.releaseHelper();
        logger.trace("{}: helper {} was released, set to null", this, helper);
        this.helper = null;
    }

    // End Ormlite Section
}
