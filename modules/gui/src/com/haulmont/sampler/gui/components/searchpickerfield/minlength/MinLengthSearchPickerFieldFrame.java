package com.haulmont.sampler.gui.components.searchpickerfield.minlength;

import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.data.ValueListener;
import com.haulmont.sampler.entity.Order;

import javax.inject.Inject;
import java.util.Map;

public class MinLengthSearchPickerFieldFrame extends AbstractFrame {

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
    }
}