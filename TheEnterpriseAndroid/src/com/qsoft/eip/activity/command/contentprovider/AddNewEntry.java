package com.qsoft.eip.activity.command.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.provider.UserDictionary;
import android.widget.EditText;
import android.widget.Toast;
import com.qsoft.eip.R;
import com.qsoft.eip.activity.SuperActivity;
import com.qsoft.eip.common.IActivityCommand;
import com.qsoft.eip.common.IModelContainer;

import java.sql.SQLException;

/**
 * User: Le
 * Date: 10/28/13
 */
public class AddNewEntry implements IActivityCommand
{
    @Override
    public void execute(SuperActivity activity, IModelContainer modelable, String... parameters) throws SQLException
    {
        ContentResolver resolver = activity.getContentResolver();
        ContentValues values = new ContentValues();
        EditText editText = activity.getGUIComponent(R.id.editText_new_word, EditText.class);
        values.put(UserDictionary.Words.WORD, editText.getText().toString());
        resolver.insert(UserDictionary.Words.CONTENT_URI, values);
        Toast toast = Toast.makeText(activity.getApplicationContext(), "Word added", Toast.LENGTH_SHORT);
        toast.show();
        editText.setText("");
    }
}
