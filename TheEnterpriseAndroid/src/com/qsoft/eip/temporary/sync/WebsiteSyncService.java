package com.qsoft.eip.temporary.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * User: Le
 * Date: 10/24/13
 */
public class WebsiteSyncService extends Service
{
    private static final Object sSyncAdapterLock = new Object();
    private static WebsiteSyncAdapter sSyncAdapter = null;

    @Override
    public void onCreate() {
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null)
                sSyncAdapter = new WebsiteSyncAdapter(getApplicationContext(), true);
        }
    }

    /**
     * Return an object that allows the system to invoke
     * the sync adapter.
     *
     */
    @Override
    public IBinder onBind(Intent intent) {
        /*
         * Get the object that allows external processes
         * to call onPerformSync(). The object is created
         * in the base class code when the SyncAdapter
         * constructors call super()
         */
        return sSyncAdapter.getSyncAdapterBinder();
    }
}
