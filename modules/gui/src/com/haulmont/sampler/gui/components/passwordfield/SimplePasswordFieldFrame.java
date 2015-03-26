package com.haulmont.sampler.gui.components.passwordfield;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.PasswordField;

import javax.inject.Inject;

public class SimplePasswordFieldFrame extends AbstractFrame {
    @Inject
    private PasswordField passwordField;

    public void showPassword() {
        showNotification((String) passwordField.getValue(), NotificationType.HUMANIZED);
    }
}