package com.haulmont.sampler.gui.components.button;

import com.haulmont.cuba.gui.components.AbstractWindow;

public class LinkButton extends AbstractWindow {

    public void sayHello() {
        showNotification("Hello, CUBA!", NotificationType.HUMANIZED);
    }
}