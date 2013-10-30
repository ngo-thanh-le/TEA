package com.qsoft.eip.common;

import com.qsoft.eip.activity.SuperActivity;

import java.sql.SQLException;

/**
 * User: Le
 * Date: 10/21/13
 */
public interface IActivityCommand extends ICommand
{
    void execute(SuperActivity activity, IModelContainer modelable, String... parameters) throws SQLException;
}
