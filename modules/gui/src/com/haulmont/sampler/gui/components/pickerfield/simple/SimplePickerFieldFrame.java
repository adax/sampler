package com.haulmont.sampler.gui.components.pickerfield.simple;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.data.impl.DsListenerAdapter;
import com.haulmont.sampler.entity.Order;

import javax.inject.Inject;
import java.util.Map;

public class SimplePickerFieldFrame extends AbstractFrame {

    @Inject
    private Datasource<Order> orderDs;

    @Override
    public void init(Map<String, Object> params) {
        // Datasource initialization. It is usually done automatically if the screen is
        // inherited from AbstractEditor and is used as an entity editor.
        Order order = new Order();
        orderDs.setItem(order);

        orderDs.addListener(new DsListenerAdapter<Order>() {
            @Override
            public void valueChanged(Order source, String property, Object prevValue, Object value) {
                Object str = value instanceof Entity ? ((Entity) value).getInstanceName() : value;
                showNotification(property + " = " + str, NotificationType.HUMANIZED);
            }
        });
    }
}