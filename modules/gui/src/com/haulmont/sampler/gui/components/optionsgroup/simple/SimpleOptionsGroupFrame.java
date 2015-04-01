package com.haulmont.sampler.gui.components.optionsgroup.simple;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.sampler.entity.Customer;

import javax.inject.Inject;
import java.util.Map;

public class SimpleOptionsGroupFrame extends AbstractFrame {

    @Inject
    private Datasource<Customer> customerDs;

    @Override
    public void init(Map<String, Object> params) {
        Customer customer = new Customer();
        customerDs.setItem(customer);
    }
}