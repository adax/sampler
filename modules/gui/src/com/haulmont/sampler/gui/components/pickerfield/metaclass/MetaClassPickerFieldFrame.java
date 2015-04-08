package com.haulmont.sampler.gui.components.pickerfield.metaclass;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.data.ValueListener;

import javax.inject.Inject;
import java.util.Map;

public class MetaClassPickerFieldFrame extends AbstractFrame {

    @Inject
    private PickerField picker;

    @Override
    public void init(Map<String, Object> params) {
        picker.addListener(new ValueListener() {
            @Override
            public void valueChanged(Object source, String property, Object prevValue, Object value) {
                String str = value == null ? "null" : ((Entity) value).getInstanceName();
                showNotification("value = " + str, NotificationType.HUMANIZED);
            }
        });
    }
}
