package com.haulmont.sampler.gui.dialogsandnotifications.dialog.option;

import com.haulmont.cuba.gui.DialogParams;
import com.haulmont.cuba.gui.components.*;

public class OptionDialogSamplerFrame extends AbstractFrame {

    public void allActions() {
        DialogParams params = getDialogParams();
        params.setWidth(500);

        showOptionDialog(
                "All actions",
                "Select any action",
                MessageType.CONFIRMATION,
                new Action[] {
                        new DialogAction(DialogAction.Type.OK) {
                            @Override
                            public void actionPerform(Component component) {
                                showNotification(DialogAction.Type.OK.name(), NotificationType.HUMANIZED);
                            }
                        },
                        new DialogAction(DialogAction.Type.CANCEL),
                        new DialogAction(DialogAction.Type.YES),
                        new DialogAction(DialogAction.Type.NO),
                        new DialogAction(DialogAction.Type.CLOSE)
                }
        );
    }
}