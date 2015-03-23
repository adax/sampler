package com.haulmont.sampler.gui.components.label;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.data.ValueListener;

import javax.inject.Inject;
import java.util.Map;

public class DynamicLabelFrame extends AbstractFrame {
    @Inject
    private Label dynamicLabel;
    @Inject
    private Label arrows;
    @Inject
    private TextField editor;

    @Override
    public void init(Map<String, Object> params) {
        dynamicLabel.setValue("Edit this");

        editor.setValue(dynamicLabel.getValue());
        editor.addListener(new ValueListener() {
            @Override
            public void valueChanged(Object source, String property, Object prevValue, Object value) {
                dynamicLabel.setValue(editor.getValue());
            }
        });

        arrows.setHtmlEnabled(true);
        arrows.setValue("<span class=\"v-icon\" style=\"font-family: FontAwesome;\">&#xf0ec;</span>");
    }
}