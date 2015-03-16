package com.haulmont.sampler.gui.components.button;

import com.haulmont.cuba.gui.components.AbstractAction;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Component;

import javax.inject.Inject;
import java.util.Map;

public class ProgrammaticActionButton extends AbstractFrame {
    @Inject
    private Button button;

    @Override
    public void init(Map<String, Object> params) {
        button.setAction(new AbstractAction("programmaticallyCreatedAction") {
            @Override
            public void actionPerform(Component component) {
                showNotification("Programmatically created action performed!", NotificationType.HUMANIZED);
            }
        });
    }
}