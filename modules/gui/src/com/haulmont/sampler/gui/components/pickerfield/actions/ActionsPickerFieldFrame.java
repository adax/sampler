package com.haulmont.sampler.gui.components.pickerfield.actions;

import com.haulmont.cuba.gui.components.AbstractAction;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.sampler.entity.Customer;
import com.haulmont.sampler.entity.Order;

import javax.inject.Inject;
import java.util.Map;

public class ActionsPickerFieldFrame extends AbstractFrame {
    @Inject
    private PickerField pickerField;
    @Inject
    private Datasource<Order> orderDs;

    @Override
    public void init(Map<String, Object> params) {
        Order order = new Order();
        orderDs.setItem(order);


        pickerField.addAction(new AbstractAction("showGrade") {
            @Override
            public void actionPerform(Component component) {
                Customer customer = pickerField.getValue();
                if (customer != null)
                    showNotification(customer.getName() + "'s grade is " + customer.getGrade(), NotificationType.HUMANIZED);
                else
                    showNotification("Choose a customer", NotificationType.HUMANIZED);
            }

            @Override
            public String getIcon() {
                return "icons/user-group-ok.png";
            }
        });

        PickerField.ClearAction clearAction = pickerField.addClearAction();
        clearAction.setIcon("icons/cancel.png");
    }

    public void greeting() {
        Customer customer = pickerField.getValue();
        if (customer != null)
            showNotification("Hello, " + customer.getName(), NotificationType.HUMANIZED);
        else
            showNotification("Choose a customer", NotificationType.HUMANIZED);
    }
}