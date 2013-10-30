package com.qsoft.eip.activity.command.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.provider.UserDictionary;
import android.widget.TextView;
import com.qsoft.eip.R;
import com.qsoft.eip.activity.SuperActivity;
import com.qsoft.eip.common.IActivityCommand;
import com.qsoft.eip.common.IModelContainer;

import java.sql.SQLException;

/**
 * User: Le
 * Date: 10/28/13
 */
public class QueryUserDictionary implements IActivityCommand
{
    @Override
    public void execute(SuperActivity activity, IModelContainer modelable, String... parameters) throws SQLException
    {
        ContentResolver resolver = activity.getContentResolver();

        String[] projection = new String[]{BaseColumns._ID,
                UserDictionary.Words.WORD};
        Cursor cursor =
                resolver.query(UserDictionary.Words.CONTENT_URI,
                        projection,
                        null,
                        null,
                        null);
        if (cursor.moveToFirst())
        {
            TextView textView = activity.getGUIComponent(R.id.textview_user_dictionary_entries, TextView.class);
            textView.setText("");
            do
            {
                long id = Long.parseLong(cursor.getString(0));
                String word = cursor.getString(1);
                // do something meaningful
                textView.append("ID: " + id + ", Word: " + word + "\n");
            } while (cursor.moveToNext());
        }
    }
}
