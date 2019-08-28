package org.smart4j.chapter4.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.smart4j.chapter4.model.Customer;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.helper.DatabaseHelper;


/**
 * 提供客户数据服务
 */
@Service
public class CustomerService {

    /**
     * 获取客户列表
     */
    public List<Customer> getCustomerList() {
        String sql = "SELECT * FROM customer";
        return DatabaseHelper.queryEntityList(Customer.class, sql,null);

/*        //TODO
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
        return customerList;*/
    }

    /**
     * 获取客户
     */
    public Customer getCustomer(long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return DatabaseHelper.queryEntity(Customer.class, sql, id);
    }

    /**
     * 创建客户
     */
    @Transaction
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return DatabaseHelper.insertEntity(Customer.class, fieldMap);
    }

    /**
     * 更新客户
     */
    @Transaction
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
    }

    /**
     * 删除客户
     */
    @Transaction
    public boolean deleteCustomer(long id) {
        return DatabaseHelper.deleteEntity(Customer.class, id);
    }
}
