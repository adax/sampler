package com.haulmont.sampler.gui.components.table.action;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.sampler.entity.Customer;

import javax.inject.Inject;

public class ActionTableFrame extends AbstractFrame {

    @Inject
    private Table customerTable;

    public void greeting() {
        Customer customer = customerTable.getSingleSelected();
        if (customer != null)
            showNotification("Hello, " + customer.getName(), NotificationType.HUMANIZED);
        else
            showNotification("No selection", NotificationType.HUMANIZED);
    }
}