package com.haulmont.sampler.gui.containers.buttonspanel.alwaysvisible;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Window;

import java.util.Collection;

public class AlwaysVisibleButtonsPanelFrame extends AbstractFrame {

    private Window.Lookup.Handler dummyLookupHandler = new Window.Lookup.Handler() {
        @Override
        public void handleLookup(Collection items) {
        }
    };

    public void showVisible() {
        getDialogParams().setWidth(480).setHeight(320);
        openLookup("visible-buttonspanel", dummyLookupHandler, WindowManager.OpenType.DIALOG);
    }

    public void showInvisible() {
        getDialogParams().setWidth(480).setHeight(320);
        openLookup("invisible-buttonspanel", dummyLookupHandler, WindowManager.OpenType.DIALOG);
    }
}