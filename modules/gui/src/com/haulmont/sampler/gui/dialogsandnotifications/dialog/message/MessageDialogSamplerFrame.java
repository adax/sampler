package com.haulmont.sampler.gui.dialogsandnotifications.dialog.message;

import com.haulmont.cuba.gui.components.AbstractFrame;

public class MessageDialogSamplerFrame extends AbstractFrame {

    public void confirmation() {
        showMessageDialog("Confirmation", "You clicked the button", MessageType.CONFIRMATION);
    }

    public void warning() {
        showMessageDialog("Warning", "<em>Something</em> <ins>is</ins> <strong>wrong</strong>", MessageType.WARNING_HTML);
    }
}