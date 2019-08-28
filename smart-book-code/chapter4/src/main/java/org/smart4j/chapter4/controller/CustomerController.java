package org.smart4j.chapter4.controller;

import org.smart4j.chapter4.model.Customer;
import org.smart4j.chapter4.service.CustomerService;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;

import java.util.List;
import java.util.Map;

/**
 * 处理客户管理相关请求
 */
@Controller
public class CustomerController {

    @Inject
    private CustomerService customerService;

    /**
     * 进入 客户列表 界面
     */
    @Action("get:/customer")
    public View index(Param param) {
        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer.jsp").addModel("customerList", customerList);
    }

    @Action("get:/customer_create")
    public View create(Param param)
    {
        return new View("customer_create.jsp");
    }

    @Action("post:/customer_create")
    public Data createsubmit(Param param)
    {
        boolean result = customerService.createCustomer(param.getFieldMap());
        return new Data(result);
    }

    @Action("get:/customer_edit")
    public View edit(Param param)
    {
        Customer c = customerService.getCustomer(param.getLong("id"));
        return new View("customer_edit.jsp").addModel("customer", c);
    }

    @Action("put:/customer_edit")
    public Data editSubmit(Param param)
    {
        Map<String, Object> fieldMap = param.getFieldMap();
        long id = param.getLong("id");
        boolean result = customerService.updateCustomer(id, fieldMap);
        return new Data(result);
    }

    @Action("get:/customer_delete")
    public Data delete(Param param)
    {
        long id = param.getLong("id");
        boolean result = customerService.deleteCustomer(id);
        return new Data(result);
    }
}