package com.haulmont.sampler.gui.components.popupbutton;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Component;

public class SimplePopupButtonFrame extends AbstractFrame {
    public void save1() {
        showNotification("Save called!", NotificationType.HUMANIZED);
    }

    public void save2(Component source) {
        showNotification("Save called from " + source.getId(), NotificationType.HUMANIZED);
    }
}