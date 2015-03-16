package com.haulmont.sampler.gui.components.button;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Component;

public class InvocationButton extends AbstractFrame {

    public void sayHello() {
        showNotification("Hello, CUBA!", NotificationType.HUMANIZED);
    }
}