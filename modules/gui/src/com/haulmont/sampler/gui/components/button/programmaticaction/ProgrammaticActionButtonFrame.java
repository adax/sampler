package com.haulmont.sampler.gui.components.button.programmaticaction;

import com.haulmont.cuba.gui.components.*;

import javax.inject.Inject;
import java.util.Map;

public class ProgrammaticActionButtonFrame extends AbstractFrame {

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