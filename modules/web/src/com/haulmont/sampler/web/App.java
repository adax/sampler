package com.haulmont.sampler.web;

import com.haulmont.cuba.web.DefaultApp;
import com.haulmont.cuba.web.gui.WebComponentsFactory;
import com.haulmont.sampler.web.gui.components.mainwindow.SamplerWebFoldersPane;

public class App extends DefaultApp {

    static {
        WebComponentsFactory.registerComponent(SamplerWebFoldersPane.NAME, SamplerWebFoldersPane.class);
    }

    @Override
    protected String routeTopLevelWindowId() {
        return "mainWindow";
    }
}