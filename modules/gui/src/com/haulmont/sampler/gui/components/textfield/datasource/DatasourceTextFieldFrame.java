package com.haulmont.sampler.gui.components.textfield.datasource;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.sampler.entity.Customer;

import javax.inject.Inject;
import java.util.Map;

public class DatasourceTextFieldFrame extends AbstractFrame {

    @Inject
    private Datasource<Customer> customersDs;

    @Override
    public void init(Map<String, Object> params) {
        // Datasource initialization. It is usually done automatically if the screen is
        // inherited from AbstractEditor and is used as an entity editor.
        Customer customer = new Customer();
        customer.setName("John");
        customersDs.setItem(customer);
    }
}