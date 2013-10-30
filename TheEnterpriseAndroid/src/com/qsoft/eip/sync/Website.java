package com.qsoft.eip.sync;

import android.database.Cursor;
import com.qsoft.eip.sync.dao.WebsiteDbHelper;

import java.io.Serializable;

/**
 * User: Le
 * Date: 10/25/13
 */
public class Website implements Serializable
{
    private int id;
    private String title;
    private String description;
    private String url;

    public Website(int id, String title, String description, String url)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    // Create a TvShow object from a cursor
    public static Website fromCursor(Cursor cursorWebsites) {
        int id = cursorWebsites.getInt(cursorWebsites.getColumnIndex(WebsiteDbHelper.WEBSITES_COL_ID));
        String title = cursorWebsites.getString(cursorWebsites.getColumnIndex(WebsiteDbHelper.WEBSITES_COL_TITLE));
        String description = cursorWebsites.getString(cursorWebsites.getColumnIndex(WebsiteDbHelper.WEBSITES_COL_DESC));
        String url = cursorWebsites.getString(cursorWebsites.getColumnIndex(WebsiteDbHelper.WEBSITES_COL_URL));

        return new Website(id, title, description, url);
    }
}
