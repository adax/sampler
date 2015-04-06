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

    private VerticalLayout welcomeLayout;

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

        if (welcomeLayout == null) {
            welcomeLayout = new VerticalLayout();
            welcomeLayout.setSpacing(true);

            Image logo = new Image(null, WebComponentsHelper.getResource("images/platform-logo.png"));
            welcomeLayout.addComponent(logo);
            welcomeLayout.setComponentAlignment(logo, Alignment.TOP_CENTER);

            Label welcomeLabel = new Label(messages.getMessage(getClass(), "StartupScreen.welcome"));
            welcomeLabel.setStyleName("sampler-startup-screen-welcome");
            welcomeLayout.addComponent(welcomeLabel);

            Label descriptionLabel = new Label(messages.getMessage(getClass(), "StartupScreen.description"));
            descriptionLabel.setStyleName("sampler-startup-screen-description");
            welcomeLayout.addComponent(descriptionLabel);
        }
        mainLayout.addComponent(welcomeLayout);
    }

    @Override
    public void closeStartupScreen() {
        super.closeStartupScreen();
        mainLayout.removeComponent(welcomeLayout);
        mainLayout.removeStyleName("sampler-startup-screen");
    }
}
