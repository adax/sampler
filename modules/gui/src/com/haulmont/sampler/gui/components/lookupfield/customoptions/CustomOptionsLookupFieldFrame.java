package com.haulmont.sampler.gui.components.lookupfield.customoptions;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.IFrame;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.data.impl.DsListenerAdapter;
import com.haulmont.sampler.entity.Customer;
import com.haulmont.sampler.entity.Order;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;

public class CustomOptionsLookupFieldFrame extends AbstractFrame {

    @Inject
    private LookupField ageLookup;
    @Inject
    private LookupField amountLookup;
    @Inject
    private Datasource<Customer> customerDs;
    @Inject
    private Datasource<Order> orderDs;

    @Override
    public void init(Map<String, Object> params) {
        // Datasource initialization. It is usually done automatically if the screen is
        // inherited from AbstractEditor and is used as an entity editor.
        Order order = new Order();
        orderDs.setItem(order);
        Customer customer = new Customer();
        customerDs.setItem(customer);

        List<BigDecimal> list = new ArrayList<>();
        list.add(BigDecimal.valueOf(1000));
        list.add(BigDecimal.valueOf(2000));
        list.add(BigDecimal.valueOf(3000));
        list.add(BigDecimal.valueOf(4000));
        amountLookup.setOptionsList(list);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("twenty", 20);
        map.put("thirty", 30);
        map.put("forty", 40);
        map.put("fifty", 50);
        ageLookup.setOptionsMap(map);

        orderDs.addListener(new DsListener());
        customerDs.addListener(new DsListener());
    }

    private class DsListener extends DsListenerAdapter {
        @Override
        public void valueChanged(Entity source, String property, Object prevValue, Object value) {
            showNotification(source.getClass().getSimpleName() + "." + property + " = " + value, NotificationType.HUMANIZED);
        }
    }
}