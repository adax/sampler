package com.haulmont.sampler.gui.components.fieldgroup.column;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.sampler.entity.Customer;
import com.haulmont.sampler.entity.CustomerGrade;

import javax.inject.Inject;
import java.util.Map;

public class ColumnFieldGroupFrame extends AbstractFrame {
    @Inject
    private Datasource<Customer> customerDs;

    @Override
    public void init(Map<String, Object> params) {
        Customer customer = new Customer();
        customer.setName("John");
        customer.setLastName("Doe");
        customer.setAge(40);
        customer.setActive(true);
        customer.setGrade(CustomerGrade.STANDARD);

        customerDs.setItem(customer);
    }
}