package com.qsoft.eip.activity.section05;

import android.widget.Button;
import com.qsoft.eip.R;
import com.qsoft.eip.activity.command.daocommand.QueryAllCommand;
import com.qsoft.eip.common.annotation.Command;
import com.qsoft.eip.common.annotation.GUIMapping;
import com.qsoft.eip.common.annotation.ViewMapping;

/**
 * This is a sample for DAO implementation with SQLite and ORMLite
 * User: Le
 * Date: 10/25/13
 */
@ViewMapping(R.layout.activity_dao)
public class DaoActivity extends OrmLiteSupportSuperActivity
{
    @GUIMapping(R.id.button_query_all)
    @Command(QueryAllCommand.class)
    private Button queryAllButton;
}
