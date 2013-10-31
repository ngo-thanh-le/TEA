package com.qsoft.eip.temporary.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.qsoft.eip.R;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.tutorials.simple.command.CommandOne;
import com.qsoft.eip.tutorials.simple.command.CommandTwo;
import com.qsoft.eip.tutorials.simple.command.StartActivity;
import com.qsoft.eip.tutorials.simple.model.SimpleModel;
import com.qsoft.eip.common.annotation.*;

@ViewMapping(R.layout.activity_main)
public class MainActivityBak extends SuperActivity
{
    @GUIMapping(R.id.button_start_activity)
    @Command(value = StartActivity.class, event = View.OnClickListener.class)
    private Button buttonStartActivity;

    @GUIMapping(R.id.editText_content_from_activity)
    @ModelBinding("Content")
    private EditText contentFromActivity;

    @GUIMapping(R.id.button_start_activity_fragment)
    @Commands({
            @Command(value = CommandOne.class, event = View.OnClickListener.class),
            @Command(value = CommandTwo.class, event = View.OnClickListener.class)
    })
    private Button buttonStartActivityFragment;

    @GUIMapping(R.id.button_start_fragment)
    private Button buttonStartFragment;

    @GUIMapping(R.id.button_start_dialog)
    private Button buttonStartDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        // Simple model
        SimpleModel modelForThis = new SimpleModel();
        modelForThis.setContent("Content content to be");
        setModel(modelForThis);

//        buttonStartActivity.setOnClickListener();

//        Sync implementation
        mAccount = CreateSyncAccount(this);
        mResolver = getContentResolver();

        ContentResolver.addPeriodicSync(mAccount,
                AUTHORITY,
                null,
                SYNC_INTERVAL);
    }

    // Sync interval constants
    public static final long MILLISECONDS_PER_SECOND = 1000L;
    public static final long SECONDS_PER_MINUTE = 60L;
    public static final long SYNC_INTERVAL_IN_MINUTES = 60L;
    public static final long SYNC_INTERVAL =
            SYNC_INTERVAL_IN_MINUTES *
                    SECONDS_PER_MINUTE *
                    MILLISECONDS_PER_SECOND;

    // The authority for the sync adapter's content provider
    public static final String AUTHORITY = "com.qsoft.eip.temporary.sync.provider";
    // An account type, in the form of a domain name
    public static final String ACCOUNT_TYPE = "qsoft.eip.com";
    // The account name
    public static final String ACCOUNT = "dummyaccount";
    // Instance fields
    Account mAccount;

    ContentResolver mResolver;

    /**
     * Create a new dummy account for the sync adapter
     *
     * @param context The application context
     */
    public static Account CreateSyncAccount(Context context)
    {
        // Create the account type and default account
        Account newAccount = new Account(ACCOUNT, ACCOUNT_TYPE);
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(newAccount, null, null))
        {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
        }
        else
        {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
        }
        return newAccount;
    }
}
