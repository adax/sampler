package com.haulmont.sampler.web;

import com.haulmont.cuba.web.AppUI;
import com.haulmont.cuba.web.AppWindow;
import com.haulmont.cuba.web.app.folders.FoldersPane;

import javax.annotation.Nullable;

/**
 * @author gorelov
 * @version $Id$
 */
public class SamplerAppWindow extends AppWindow {
    public SamplerAppWindow(AppUI ui) {
        super(ui);
    }

    @Nullable
    @Override
    protected FoldersPane createFoldersPane() {
        return new LeftPanel(menuBar, this);
    }
}
