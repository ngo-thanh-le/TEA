package com.qsoft.eip.sync.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WebsiteDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sync.db";
    private static final int DATABASE_VERSION = 1;

    // DB Table consts
    public static final String WEBSITES_TABLE_NAME = "websites";
    public static final String WEBSITES_COL_ID = "id";
    public static final String WEBSITES_COL_TITLE = "title";
    public static final String WEBSITES_COL_DESC = "desc";
    public static final String WEBSITES_COL_URL = "url";


    // Database creation sql statement
    public static final String DATABASE_CREATE = "create table "
            + WEBSITES_TABLE_NAME + "(" +
            WEBSITES_COL_ID + " integer primary key autoincrement, " +
            WEBSITES_COL_TITLE + " text not null, " +
            WEBSITES_COL_DESC + " text, " +
            WEBSITES_COL_URL + " text" +
            ");";


    public WebsiteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(WebsiteDbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + WEBSITES_TABLE_NAME);
        onCreate(db);
    }

}
