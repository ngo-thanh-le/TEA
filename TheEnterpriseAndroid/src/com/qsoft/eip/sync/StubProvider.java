package com.qsoft.eip.sync;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * User: Le
 * Date: 10/25/13
 */
public class StubProvider extends ContentProvider
{
// -------------------------- OTHER METHODS --------------------------

    /*
     * delete() always returns "no rows affected" (0)
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /*
     * Return an empty String for MIME type
     */
    @Override
    public String getType(Uri uri)
    {
        return new String();
    }

    /*
     * insert() always returns null (no URI)
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    /*
      * Always return true, indicating that the
      * provider loaded correctly.
      */
    @Override
    public boolean onCreate() {
        return true;
    }

    /*
     * query() always returns no results
     *
     */
    @Override
    public Cursor query(
            Uri uri,
            String[] projection,
            String selection,
            String[] selectionArgs,
            String sortOrder) {
        return null;
    }

    /*
     * update() always returns "no rows affected" (0)
     */
    public int update(
            Uri uri,
            ContentValues values,
            String selection,
            String[] selectionArgs) {
        return 0;
    }
}
