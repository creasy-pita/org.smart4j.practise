package org.smart4j.chapter3.service;

import org.smart4j.chapter3.model.Customer;
import org.smart4j.framework.annotation.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by creasypita on 8/22/2019.
 */
@Service
public class CustomerService {
    public List<Customer> getCustomerList()
    {
        //TODO
        //模拟数据
        List<Customer> customerList = new ArrayList<Customer>(2);
        Customer c1 = new Customer();
        c1.setId(1);
        c1.setName("jane");
        c1.setEmail("jjj.com");
        c1.setTelephone("123");

        Customer c2 = new Customer();
        c2.setId(2);
        c2.setName("jack");
        c2.setEmail("aaa.com");
        c2.setTelephone("222");
        customerList.add(c1);
        customerList.add(c2);
        return customerList;
    }
}
