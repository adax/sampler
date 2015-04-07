package com.haulmont.sampler.gui.components.fieldgroup.generate;

import com.haulmont.cuba.core.global.GlobalConfig;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.sampler.entity.Customer;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GenerateFieldGroupFrame extends AbstractFrame {

    @Inject
    private FieldGroup customerFields;
    @Inject
    private Datasource<Customer> customerDs;
    @Inject
    private ComponentsFactory componentsFactory;

    @Override
    public void init(Map<String, Object> params) {
        // Datasource initialization. It is usually done automatically if the screen is
        // inherited from AbstractEditor and is used as an entity editor.
        Customer customer = new Customer();
        customer.setName("John");
        customer.setLastName("Doe");
        customerDs.setItem(customer);

        customerFields.addCustomField("active", new FieldGroup.CustomFieldGenerator() {
            @Override
            public Component generateField(Datasource datasource, String propertyId) {
                LookupField lookupField = componentsFactory.createComponent(LookupField.NAME);
                lookupField.setDatasource(datasource, propertyId);

                Map<String, Object> options = new LinkedHashMap<>();
                options.put("Yes", Boolean.TRUE);
                options.put("No", Boolean.FALSE);
                lookupField.setOptionsMap(options);

                lookupField.setWidth("100%");
                return lookupField;
            }
        });
    }
}