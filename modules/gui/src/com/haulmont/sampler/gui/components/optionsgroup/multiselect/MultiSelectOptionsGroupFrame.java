package com.haulmont.sampler.gui.components.optionsgroup.multiselect;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.OptionsGroup;
import com.haulmont.cuba.gui.data.ValueListener;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;

public class MultiSelectOptionsGroupFrame extends AbstractFrame {

    @Inject
    private OptionsGroup optionsGroup;

    @Override
    public void init(Map<String, Object> params) {
        optionsGroup.addListener(new ValueListener() {
            @Override
            public void valueChanged(Object source, String property, Object prevValue, Object value) {
                String str = value == null ? "0" : String.valueOf(((Collection) value).size());
                showNotification("selected: " + str, NotificationType.HUMANIZED);
            }
        });
    }
}
