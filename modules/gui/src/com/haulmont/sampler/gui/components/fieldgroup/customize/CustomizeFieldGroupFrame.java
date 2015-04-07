package com.haulmont.sampler.gui.components.fieldgroup.customize;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.sampler.entity.Order;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

public class CustomizeFieldGroupFrame extends AbstractFrame {

    @Named("orderFieldGroup.customer")
    private PickerField customerField;
    @Inject
    private Datasource<Order> orderDs;

    @Override
    public void init(Map<String, Object> params) {
        Order order = new Order();
        orderDs.setItem(order);

        customerField.removeAllActions();
        customerField.addLookupAction();
        customerField.addOpenAction();
    }
}