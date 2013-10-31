package com.qsoft.eip.tutorials.section05.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * User: Le
 * Date: 10/25/13
 */
@DatabaseTable(tableName = "accounts")
public class Account {
// ------------------------------ FIELDS ------------------------------

    @DatabaseField(id = true)
    private String name;
    @DatabaseField
    private String password;       
    @DatabaseField
    private String description;

// --------------------------- CONSTRUCTORS ---------------------------

    public Account() {
        // ORMLite needs a no-arg constructor
    }

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}