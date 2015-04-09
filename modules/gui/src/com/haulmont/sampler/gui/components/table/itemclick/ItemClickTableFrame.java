package com.haulmont.sampler.gui.components.table.itemclick;

import com.haulmont.cuba.gui.components.*;
import com.haulmont.sampler.entity.Customer;

import javax.inject.Inject;
import java.util.Map;

public class ItemClickTableFrame extends AbstractFrame {

    @Inject
    private Table customerTable;

    @Override
    public void init(Map<String, Object> params) {
        customerTable.setItemClickAction(new AbstractAction("tableClickAction") {
            @Override
            public void actionPerform(Component component) {
                Customer customer = customerTable.getSingleSelected();
                if (customer != null)
                    showNotification("Item clicked for: " + customer.getName(), NotificationType.HUMANIZED);
            }
        });
        customerTable.setEnterPressAction(new AbstractAction("enterPressAction") {
            @Override
            public void actionPerform(Component component) {
                Customer customer = customerTable.getSingleSelected();
                if (customer != null)
                    showNotification("Enter pressed for: " + customer.getName(), NotificationType.HUMANIZED);
            }
        });
    }
}