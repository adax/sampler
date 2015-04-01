package com.haulmont.sampler.gui.containers.tabsheet.lazy;

import com.haulmont.cuba.gui.components.*;

import javax.inject.Inject;
import java.util.Map;

public class LazyTabSheetFrame extends AbstractFrame {

    @Inject
    private TabSheet tabSheet;
    @Inject
    private Label checkMessage;

    @Override
    public void init(Map<String, Object> params) {
        tabSheet.addListener(new TabSheet.TabChangeListener() {
            @Override
            public void tabChanged(TabSheet.Tab newTab) {
                checkComponents();
            }
        });
        checkComponents();
    }

    private void checkComponents() {
        StringBuilder sb = new StringBuilder();

        Label label1 = getComponent("label1");
        sb.append("label1 = ");
        if (label1 != null)
            sb.append(label1.getValue());
        else
            sb.append("null");

        Label label2 = getComponent("label2");
        sb.append("\nlabel2 = ");
        if (label2 != null)
            sb.append(label2.getValue());
        else
            sb.append("null");

        checkMessage.setValue(sb.toString());
    }
}