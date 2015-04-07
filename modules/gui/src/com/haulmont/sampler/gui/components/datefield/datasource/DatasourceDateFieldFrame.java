package com.haulmont.sampler.gui.components.datefield.datasource;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.sampler.entity.Order;

import javax.inject.Inject;
import java.util.Date;
import java.util.Map;

public class DatasourceDateFieldFrame extends AbstractFrame {

    @Inject
    private Datasource<Order> ordersDs;

    @Override
    public void init(Map<String, Object> params) {
        // Datasource initialization. It is usually done automatically if the screen is
        // inherited from AbstractEditor and is used as an entity editor.
        Order order = new Order();
        order.setDate(new Date());
        ordersDs.setItem(order);
    }
}