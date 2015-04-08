package com.haulmont.sampler.web;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.entity.Role;
import com.haulmont.cuba.security.entity.UserRole;
import com.haulmont.cuba.web.AppUI;
import com.haulmont.cuba.web.AppWindow;
import com.haulmont.cuba.web.app.folders.FoldersPane;
import com.haulmont.cuba.web.gui.components.WebComponentsHelper;
import com.vaadin.ui.*;

import javax.annotation.Nullable;
import java.util.List;

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

    @Override
    protected HorizontalLayout createMenuBarLayout() {
        HorizontalLayout layout = super.createMenuBarLayout();

        Label appTitle = new Label(messages.getMessage(getClass(), "menuBar.appTitle"));
        appTitle.setStyleName("sampler-app-tittle-label");
        appTitle.setSizeUndefined();
        layout.addComponent(appTitle, 1);
        layout.setComponentAlignment(appTitle, Alignment.MIDDLE_LEFT);

        UserSessionSource userSessionSource = AppBeans.get(UserSessionSource.NAME);
        List<UserRole> roles = userSessionSource.getUserSession().getUser().getUserRoles();
        for (UserRole userRole : roles) {
            Role role = userRole.getRole();
            if ("Demo users".equals(role.getName())) {
                layout.removeComponent(userNameLabel);
            }
        }

        return layout;
    }
}
