package com.haulmont.sampler.gui.components.searchpickerfield.notifications;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.SearchField;
import com.haulmont.cuba.gui.components.SearchPickerField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.data.ValueListener;
import com.haulmont.sampler.entity.Order;

import javax.inject.Inject;
import java.util.Map;

public class NotificationsSearchPickerFieldFrame extends AbstractFrame {
    @Inject
    private TextField minLength;
    @Inject
    private SearchPickerField searchPickerField;
    @Inject
    private Datasource<Order> orderDs;

    @Override
    public void init(Map<String, Object> params) {
        Order order = new Order();
        orderDs.setItem(order);

        minLength.setValue(searchPickerField.getMinSearchStringLength());
        minLength.addListener(new ValueListener() {
            @Override
            public void valueChanged(Object source, String property, Object prevValue, Object value) {
                searchPickerField.setMinSearchStringLength((int) minLength.getValue());
            }
        });

        searchPickerField.setSearchNotifications(new SearchField.SearchNotifications() {
            @Override
            public void notFoundSuggestions(String filterString) {
                showNotification("No customers found for search string: " + filterString,
                        NotificationType.TRAY);
            }

            @Override
            public void needMinSearchStringLength(String filterString, int minSearchStringLength) {
                showNotification("Minimum length of search string is " + minSearchStringLength,
                        NotificationType.TRAY);
            }
        });
    }
}