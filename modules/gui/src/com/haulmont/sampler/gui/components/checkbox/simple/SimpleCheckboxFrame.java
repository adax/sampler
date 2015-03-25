package com.haulmont.sampler.gui.components.checkbox.simple;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.CheckBox;
import com.haulmont.cuba.gui.data.ValueListener;

import javax.inject.Inject;
import java.util.Map;

public class SimpleCheckboxFrame extends AbstractFrame {
    @Inject
    private CheckBox checkBox;

    @Override
    public void init(Map<String, Object> params) {
        checkBox.addListener(new ValueListener() {
            @Override
            public void valueChanged(Object source, String property, Object prevValue, Object value) {
                if (Boolean.TRUE.equals(value)) {
                    showNotification("set", NotificationType.HUMANIZED);
                } else {
                    showNotification("not set", NotificationType.HUMANIZED);
                }
            }
        });
    }
}