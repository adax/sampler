package com.haulmont.sampler.web;

import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.web.DefaultApp;
import com.haulmont.cuba.web.gui.WebComponentsFactory;
import com.haulmont.sampler.web.gui.components.mainwindow.SamplerWebFoldersPane;
import org.apache.commons.lang.BooleanUtils;

public class App extends DefaultApp {

    static {
        WebComponentsFactory.registerComponent(SamplerWebFoldersPane.NAME, SamplerWebFoldersPane.class);
    }

    @Override
    protected String routeTopLevelWindowId() {
        if (BooleanUtils.toBoolean(AppContext.getProperty("sampler.developerMode"))) {
            return super.routeTopLevelWindowId();
        }
        return "mainWindow";
    }
}