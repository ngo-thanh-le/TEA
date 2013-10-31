package com.qsoft.eip.tutorials.section05.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * User: Le
 * Date: 10/25/13
 */
@DatabaseTable(tableName = "customers")
public class Customer
{
// ------------------------------ FIELDS ------------------------------

    @DatabaseField(id = true)
    private String customerName;
    @DatabaseField
    private String address;
    @DatabaseField
    private String email;

// --------------------------- CONSTRUCTORS ---------------------------

    public Customer() {
        // ORMLite needs a no-arg constructor
    }

    public Customer(String customerName, String address, String email) {
        this.customerName = customerName;
        this.address = address;
        this.email = email;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}