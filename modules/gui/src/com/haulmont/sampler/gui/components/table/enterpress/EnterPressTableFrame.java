package com.haulmont.sampler.gui.components.table.enterpress;

import com.haulmont.cuba.gui.components.AbstractAction;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.sampler.entity.Customer;

import javax.inject.Inject;
import java.util.Map;

public class EnterPressTableFrame extends AbstractFrame {
    @Inject
    private Table customerTable;

    @Override
    public void init(Map<String, Object> params) {
        customerTable.setEnterPressAction(new AbstractAction("enter press") {
            @Override
            public void actionPerform(Component component) {
                Customer customer = customerTable.getSingleSelected();
                if (customer != null)
                    showNotification(customer.getName(), NotificationType.HUMANIZED);
            }
        });
    }
}