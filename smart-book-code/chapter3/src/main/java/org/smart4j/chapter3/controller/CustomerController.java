package org.smart4j.chapter3.controller;

import org.smart4j.chapter3.model.Customer;
import org.smart4j.chapter3.service.CustomerService;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by creasypita on 8/22/2019.
 */
@Controller
public class CustomerController {

    @Inject
    private CustomerService customerService;

    @Action("get:/customer")
    public View Index(Param param)
    {
        List<Customer> list = customerService.getCustomerList();
        return new View("customer.jsp").addModel("customerList",list);
    }
}
