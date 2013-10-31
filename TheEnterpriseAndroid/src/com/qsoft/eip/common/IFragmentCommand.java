package com.qsoft.eip.common;

import java.sql.SQLException;

/**
 * User: Le
 * Date: 10/21/13
 */
public interface IFragmentCommand extends ICommand
{
    void execute(SuperFragment activity, IModelContainer modelable, String... parameters) throws Exception;
}
