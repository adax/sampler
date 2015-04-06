package com.haulmont.sampler.web;

import com.haulmont.cuba.web.AppUI;
import com.haulmont.cuba.web.AppWindow;
import com.haulmont.cuba.web.app.folders.FoldersPane;
import com.haulmont.cuba.web.gui.components.WebComponentsHelper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

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

    @Override
    protected void initStartupScreen() {
        super.initStartupScreen();

        mainLayout.setMargin(true);
        mainLayout.setStyleName("sampler-startup-screen");

        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);

        Image logo = new Image(null, WebComponentsHelper.getResource("images/platform-logo.png"));
        layout.addComponent(logo);
        layout.setComponentAlignment(logo, Alignment.TOP_CENTER);

        Label welcomeLabel = new Label(messages.getMessage(getClass(), "StartupScreen.welcome"));
        welcomeLabel.setStyleName("sampler-startup-screen-welcome");
        layout.addComponent(welcomeLabel);

//        Label descriptionLabel = new Label(messages.getMessage(getClass(), "StartupScreen.description"));
//        descriptionLabel.setStyleName("sampler-startup-screen-description");
//        layout.addComponent(descriptionLabel);

        mainLayout.addComponent(layout);
    }
}
