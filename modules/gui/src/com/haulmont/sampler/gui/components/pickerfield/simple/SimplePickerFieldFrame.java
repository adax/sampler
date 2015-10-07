package com.haulmont.sampler.gui.components.pickerfield.simple;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.data.Datasource;
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

        orderDs.addItemPropertyChangeListener(e -> {
            Object str = e.getValue() instanceof Entity ? ((Entity) e.getValue()).getInstanceName() : e.getValue();
            showNotification(e.getProperty() + " = " + str, NotificationType.HUMANIZED);
        });
    }
}