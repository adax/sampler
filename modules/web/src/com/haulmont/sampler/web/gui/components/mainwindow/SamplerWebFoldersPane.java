package com.haulmont.sampler.web.gui.components.mainwindow;

import com.haulmont.cuba.web.app.folders.CubaFoldersPane;
import com.haulmont.cuba.web.gui.components.mainwindow.WebFoldersPane;
import com.haulmont.sampler.web.app.folders.SamplerFoldersPane;

/**
 * @author gorelov
 * @version $Id$
 */
public class SamplerWebFoldersPane extends WebFoldersPane {

    @Override
    protected CubaFoldersPane createComponent() {
        return new SamplerFoldersPane();
    }
}
