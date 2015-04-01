package com.haulmont.sampler.gui.components.fieldgroup.generate;

import com.haulmont.cuba.core.global.GlobalConfig;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.sampler.entity.Customer;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenerateFieldGroupFrame extends AbstractFrame {

    @Inject
    private FieldGroup customerField;
    @Inject
    private Datasource<Customer> customerDs;
    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private GlobalConfig globalConfig;

    @Override
    public void init(Map<String, Object> params) {
        Customer customer = new Customer();
        customer.setName("John");
        customer.setLastName("Doe");

        customerDs.setItem(customer);

        customerField.addCustomField("language", new FieldGroup.CustomFieldGenerator() {
            @Override
            public Component generateField(Datasource datasource, String propertyId) {
                LookupField lookupField = componentsFactory.createComponent(LookupField.NAME);
                List<String> locales = new ArrayList<>(globalConfig.getAvailableLocales().keySet());
                lookupField.setOptionsList(locales);
                lookupField.setWidth("100%");
                lookupField.setCaption("Language");
                return lookupField;
            }
        });
    }
}