package com.qsoft.eip.temporary.sync;

import android.accounts.Account;
import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Le
 * Date: 10/24/13
 */
public class WebsiteSyncAdapter extends AbstractThreadedSyncAdapter
{


    // Global variables
    // Define a variable to contain a content resolver instance
    ContentResolver mContentResolver;

    /**
     * Set up the sync adapter
     */
    public WebsiteSyncAdapter(Context context, boolean autoInitialize)
    {
        super(context, autoInitialize);
    }

    /**
     * Set up the sync adapter. This form of the
     * constructor maintains compatibility with Android 3.0
     * and later platform versions
     */
    public WebsiteSyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs)
    {
        super(context, autoInitialize, allowParallelSyncs);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult)
    {
        try {
            List<Website> websites = new ServerAccessor().getWebsite("");

            // Get shows from local
            ArrayList<Website> localTvShows = new ArrayList<Website>();
            Cursor curTvShows = provider.query(Uri.parse("content://com.qsoft.eip.temporary.sync.provider/websites"), null, null, null, null);
            if (curTvShows != null) {
                while (curTvShows.moveToNext()) {
                    localTvShows.add(Website.fromCursor(curTvShows));
                }
                curTvShows.close();
            }
        } catch (Exception e) {

        }
    }
}
