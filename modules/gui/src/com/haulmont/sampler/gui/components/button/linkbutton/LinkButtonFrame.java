package com.haulmont.sampler.gui.components.button.linkbutton;

import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Component;

public class LinkButtonFrame extends AbstractWindow {

    public void sayHello() {
        showNotification("Hello, world!", NotificationType.HUMANIZED);
    }

    public void save(Component source) {
        showNotification("Save called from " + source.getId(), NotificationType.HUMANIZED);
    }
}