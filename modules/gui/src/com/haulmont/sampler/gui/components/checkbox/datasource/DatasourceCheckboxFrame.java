package com.haulmont.sampler.gui.components.checkbox.datasource;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.sampler.entity.Customer;

import javax.inject.Inject;
import java.util.Map;

public class DatasourceCheckboxFrame extends AbstractFrame {

    @Inject
    private Datasource<Customer> customersDs;

    @Override
    public void init(Map<String, Object> params) {
        Customer customer = new Customer();
        customer.setActive(true);
        customersDs.setItem(customer);
    }
}