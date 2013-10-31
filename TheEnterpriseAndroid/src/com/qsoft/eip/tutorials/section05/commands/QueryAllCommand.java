package com.qsoft.eip.tutorials.section05.commands;

import com.j256.ormlite.dao.Dao;
import com.qsoft.eip.tutorials.section05.OrmLiteSupportSuperActivity;
import com.qsoft.eip.common.SuperActivity;
import com.qsoft.eip.common.IActivityCommand;
import com.qsoft.eip.common.IModelContainer;
import com.qsoft.eip.tutorials.section05.model.Account;
import com.qsoft.eip.tutorials.section05.model.Customer;
import com.qsoft.eip.tutorials.section05.model.Person;

import java.sql.SQLException;

/**
 * User: Le
 * Date: 10/25/13
 */
public class QueryAllCommand implements IActivityCommand
{
    @Override
    public void execute(SuperActivity activity, IModelContainer modelable, String... parameters) throws Exception
    {
        OrmLiteSupportSuperActivity ormLiteSupportSuperActivity;
        if (activity instanceof OrmLiteSupportSuperActivity)
        {
            ormLiteSupportSuperActivity = (OrmLiteSupportSuperActivity) activity;
        }
        else
        {
            return;
        }
        Dao<Account, Integer> accountDAO = ormLiteSupportSuperActivity.getHelper().getDao(Account.class, Integer.class);
        accountDAO.queryForAll();
        accountDAO.create(new Account("Dung", "123456"));
        Dao<Customer, Integer> customerDAO = ormLiteSupportSuperActivity.getHelper().getDao(Customer.class, Integer.class);
        customerDAO.queryForAll();
        customerDAO.create(new Customer("Le", "Ngoc Ha", "lent@qsoftvietnam.com"));
        Dao<Person, Integer> personDAO = ormLiteSupportSuperActivity.getHelper().getDao(Person.class, Integer.class);
        personDAO.queryForAll();
        personDAO.create(new Person("Le", "Ngoc Ha"));
    }
}
