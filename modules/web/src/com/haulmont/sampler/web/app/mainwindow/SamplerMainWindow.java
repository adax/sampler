package com.haulmont.sampler.web.app.mainwindow;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.Embedded;
import com.haulmont.cuba.gui.components.mainwindow.UserIndicator;
import com.haulmont.cuba.security.entity.Role;
import com.haulmont.cuba.security.entity.UserRole;
import com.haulmont.cuba.web.app.mainwindow.AppMainWindow;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class SamplerMainWindow extends AppMainWindow {

    @Inject
    private UserIndicator userIndicator;
    @Inject
    private Embedded platformLogoImage;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        platformLogoImage.setSource("images/platform-logo.png");
        initUserIndicator();
    }

    private void initUserIndicator() {
        UserSessionSource userSessionSource = AppBeans.get(UserSessionSource.NAME);
        List<UserRole> roles = userSessionSource.getUserSession().getUser().getUserRoles();
        for (UserRole userRole : roles) {
            Role role = userRole.getRole();
            if ("Demo users".equals(role.getName())) {
                userIndicator.setVisible(false);
                return;
            }
        }
    }
}
