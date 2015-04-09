package com.haulmont.sampler.gui.components.table.generate;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.GlobalConfig;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.sampler.entity.Customer;

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
        customerTable.addGeneratedColumn("language", new Table.ColumnGenerator<Customer>() {
            @Override
            public Component generateCell(Customer entity) {
                LookupField lookupField = componentsFactory.createComponent(LookupField.NAME);
                List<String> locales = new ArrayList<>(globalConfig.getAvailableLocales().keySet());
                lookupField.setOptionsList(locales);
                lookupField.setWidth("100%");
                return lookupField;
            }
        });

        customerTable.setColumnCaption("language", "Language");
    }
}