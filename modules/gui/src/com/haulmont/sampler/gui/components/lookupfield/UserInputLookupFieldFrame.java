package com.haulmont.sampler.gui.components.lookupfield;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.LookupField;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UserInputLookupFieldFrame extends AbstractFrame {
    @Inject
    private LookupField lookup;

    @Override
    public void init(Map<String, Object> params) {
        final List<String> list = new ArrayList<>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
        new ArrayList<String>();
        lookup.setOptionsList(list);
        lookup.setNewOptionAllowed(true);
        lookup.setNewOptionHandler(new LookupField.NewOptionHandler() {
            @Override
            public void addNewOption(String caption) {
                showNotification(caption + " added", NotificationType.HUMANIZED);
                list.add(caption);
            }
        });
    }
}