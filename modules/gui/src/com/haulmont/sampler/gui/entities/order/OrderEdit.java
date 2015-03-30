package com.haulmont.sampler.gui.entities.order;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.CollectionDatasourceListener;
import com.haulmont.cuba.gui.data.impl.CollectionDsListenerAdapter;
import com.haulmont.sampler.entity.InvoiceItem;
import com.haulmont.sampler.entity.Order;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderEdit extends AbstractEditor<Order> {
    @Inject
    private CollectionDatasource<InvoiceItem, UUID> invoiceDs;

    @Override
    public void init(Map<String, Object> params) {
        invoiceDs.addListener(new CollectionDsListenerAdapter<InvoiceItem>() {
            @Override
            public void collectionChanged(CollectionDatasource ds, Operation operation, List<InvoiceItem> items) {
                calculateAmount();
            }

            @Override
            public void valueChanged(InvoiceItem source, String property, Object prevValue, Object value) {
                calculateAmount();
            }
        });
    }

    private void calculateAmount() {
        BigDecimal amount = BigDecimal.ZERO;
        for (InvoiceItem invoice : invoiceDs.getItems()) {
            amount = amount.add(invoice.getProduct().getPrice().multiply(invoice.getQuantity()));
        }
        getItem().setAmount(amount);
    }
}