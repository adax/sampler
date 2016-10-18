package com.haulmont.sampler.web.ui.components.timefield.datasource;

import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.sampler.entity.Order;

import javax.inject.Inject;
import java.util.Date;
import java.util.Map;

public class DatasourceTimeFieldFrame extends AbstractWindow {

    @Inject
    private Datasource<Order> orderDs;
    @Inject
    private Metadata metadata;

    @Override
    public void init(Map<String, Object> params) {
        // Datasource initialization. It is usually done automatically if the screen is
        // inherited from AbstractEditor and is used as an entity editor.
        Order order = metadata.create(Order.class);
        order.setCreateTs(new Date());
        orderDs.setItem(order);
    }
}