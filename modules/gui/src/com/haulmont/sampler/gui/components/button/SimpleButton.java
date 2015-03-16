package com.haulmont.sampler.gui.components.button;

import com.haulmont.cuba.gui.components.AbstractFrame;

public class SimpleButton extends AbstractFrame {

    public void sayHello() {
        showNotification("Hello, CUBA!", NotificationType.HUMANIZED);
    }
}