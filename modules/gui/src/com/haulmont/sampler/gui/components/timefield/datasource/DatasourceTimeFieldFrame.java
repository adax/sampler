package com.haulmont.sampler.gui.components.timefield.datasource;

import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.sampler.entity.Order;

import javax.inject.Inject;
import java.util.Date;
import java.util.Map;

public class DatasourceTimeFieldFrame extends AbstractWindow {
    @Inject
    private Datasource<Order> orderDs;

    @Override
    public void init(Map<String, Object> params) {
        Order order = new Order();
        order.setCreateTs(new Date());
        orderDs.setItem(order);
    }
}