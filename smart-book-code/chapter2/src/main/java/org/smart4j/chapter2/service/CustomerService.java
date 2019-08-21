package org.smart4j.chapter2.service;

import org.smart4j.chapter2.helper.DatabaseHelper;
import org.smart4j.chapter2.model.Customer;

import java.util.List;

/**
 * Created by creasypita on 8/19/2019.
 */
public class CustomerService {
    public List<Customer> GetCustomerList()
    {
        String sql = "SELECT * FROM CUSTOMER";
        return DatabaseHelper.queryEntityList(Customer.class, sql,null );
    }
}
