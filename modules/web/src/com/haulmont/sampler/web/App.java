package com.haulmont.sampler.web;

import com.haulmont.cuba.web.DefaultApp;
import com.haulmont.cuba.web.gui.WebComponentsFactory;
import com.haulmont.sampler.web.gui.components.mainwindow.SamplerWebFoldersPane;
import com.haulmont.charts.web.gui.ChartComponentPalette;
import com.haulmont.cuba.web.gui.WebUIPaletteManager;

public class App extends DefaultApp {

    static {
        WebComponentsFactory.registerComponent(SamplerWebFoldersPane.NAME, SamplerWebFoldersPane.class);
        WebUIPaletteManager.registerPalettes(new ChartComponentPalette());
    }
}