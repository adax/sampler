package com.haulmont.sampler.gui.dialogsandnotifications.notification;

import com.haulmont.cuba.gui.components.AbstractFrame;

public class NotificationsSampleFrame extends AbstractFrame {

    public void tray() {
        showNotification("Tray notification", NotificationType.TRAY);
    }

    public void humanized() {
        showNotification("Humanized notification", "with description", NotificationType.HUMANIZED);
    }

    public void warning() {
        showNotification("<em>Warning notification</em>", NotificationType.WARNING_HTML);
    }

    public void error() {
        showNotification("<code>Error notification</code>", "<ins>with description</ins>", NotificationType.ERROR_HTML);
    }
}