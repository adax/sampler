package com.haulmont.sampler.gui.containers.buttonspanel.alwaysvisible;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.IFrame;
import com.haulmont.cuba.gui.components.Window;

public class AlwaysVisibleButtonsPanelFrame extends AbstractFrame {

    public void showVisible() {
        getDialogParams().setWidth(480).setHeight(320);
        openLookup("visible-buttonspanel", null, WindowManager.OpenType.DIALOG);
    }

    public void showInvisible() {
        getDialogParams().setWidth(480).setHeight(320);
        openLookup("invisible-buttonspanel", null, WindowManager.OpenType.DIALOG);
    }
}