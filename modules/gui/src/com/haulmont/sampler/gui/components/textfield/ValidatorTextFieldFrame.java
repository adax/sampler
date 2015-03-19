package com.haulmont.sampler.gui.components.textfield;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.components.ValidationException;

import javax.inject.Inject;

public class ValidatorTextFieldFrame extends AbstractFrame {
    @Inject
    private TextField integerField;

    public void validate() {
        try {
            integerField.validate();
            showNotification("Validation successful", NotificationType.HUMANIZED);
        } catch (ValidationException e) {
            showNotification("Validation failed: " + e.getMessage(), NotificationType.ERROR);
        }
    }
}