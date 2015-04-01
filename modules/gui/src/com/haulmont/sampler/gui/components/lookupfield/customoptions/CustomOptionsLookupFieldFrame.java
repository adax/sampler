package com.haulmont.sampler.gui.components.lookupfield.customoptions;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.sampler.entity.Customer;
import com.haulmont.sampler.entity.Order;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;

public class CustomOptionsLookupFieldFrame extends AbstractFrame {

    @Inject
    private LookupField agesLookup;
    @Inject
    private LookupField amountLookup;
    @Inject
    private Datasource<Customer> customerDs;
    @Inject
    private Datasource<Order> orderDs;

    @Override
    public void init(Map<String, Object> params) {
        Order order = new Order();
        orderDs.setItem(order);

        List<BigDecimal> list = new ArrayList<>();
        list.add(BigDecimal.valueOf(1000));
        list.add(BigDecimal.valueOf(2000));
        list.add(BigDecimal.valueOf(3000));
        list.add(BigDecimal.valueOf(4000));
        amountLookup.setOptionsList(list);

        Customer customer = new Customer();
        customerDs.setItem(customer);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("twenty", 20);
        map.put("thirty", 30);
        map.put("forty", 40);
        map.put("fifty", 50);
        agesLookup.setOptionsMap(map);
    }
}