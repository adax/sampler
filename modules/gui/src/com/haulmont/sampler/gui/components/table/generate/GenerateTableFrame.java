package com.haulmont.sampler.gui.components.table.generate;

import com.haulmont.cuba.core.global.GlobalConfig;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenerateTableFrame extends AbstractFrame {

    @Inject
    private Table customerTable;
    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private GlobalConfig globalConfig;

    @Override
    public void init(Map<String, Object> params) {
        customerTable.addGeneratedColumn("language", entity -> {
            LookupField lookupField = componentsFactory.createComponent(LookupField.class);
            List<String> locales = new ArrayList<>(globalConfig.getAvailableLocales().keySet());
            lookupField.setOptionsList(locales);
            lookupField.setWidth("100%");
            return lookupField;
        });

        customerTable.setColumnCaption("language", "Language");
    }
}