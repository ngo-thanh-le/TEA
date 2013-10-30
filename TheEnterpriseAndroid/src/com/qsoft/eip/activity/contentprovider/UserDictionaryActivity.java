package com.qsoft.eip.activity.contentprovider;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.qsoft.eip.R;
import com.qsoft.eip.activity.SuperActivity;
import com.qsoft.eip.activity.command.contentprovider.AddNewEntry;
import com.qsoft.eip.activity.command.contentprovider.QueryUserDictionary;
import com.qsoft.eip.common.annotation.Command;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.ViewMapping;

/**
 * User: Le
 * Date: 10/28/13
 */
@ViewMapping(R.layout.activity_user_dictionary)
public class UserDictionaryActivity extends SuperActivity
{
    @GUIMapping(R.id.button_query_user_dictionary)
    @Command(QueryUserDictionary.class)
    private Button buttonQueryAll;

    @GUIMapping(R.id.button_add_entry)
    @Command(AddNewEntry.class)
    private Button buttonAddNewEntry;

    @GUIMapping(R.id.editText_new_word)
    private EditText editTextNewEntry;

    @GUIMapping(R.id.textview_user_dictionary_entries)
    private TextView textViewEntries;
}
