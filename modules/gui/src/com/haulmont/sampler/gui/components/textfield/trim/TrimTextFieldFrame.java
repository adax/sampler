package com.haulmont.sampler.gui.components.textfield.trim;

import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.ValueListener;

import javax.inject.Inject;
import java.util.Map;

public class TrimTextFieldFrame extends AbstractFrame {

    @Inject
    private CheckBox trim;
    @Inject
    private TextField textField;
    @Inject
    private Label value;

    @Override
    public void init(Map<String, Object> params) {
        trim.setValue(textField.isTrimming());
        trim.addListener(new ValueListener() {
            @Override
            public void valueChanged(Object source, String property, Object prevValue, Object value) {
                textField.setTrimming((boolean) trim.getValue());
            }
        });
    }

    public void show() {
        value.setValue("Value: '" + textField.getValue() + "'");
    }
}