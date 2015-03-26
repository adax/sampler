package com.haulmont.sampler.gui.components.textfield.maskedfield;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.MaskedField;

import javax.inject.Inject;
import java.util.Map;

public class MaskedFieldFrame extends AbstractFrame {
    @Inject
    private MaskedField phoneNumberField;

    @Override
    public void init(Map<String, Object> params) {

    }

    public void showPhoneNumber() {
        showNotification((String) phoneNumberField.getValue(), NotificationType.HUMANIZED);
    }
}