package com.haulmont.sampler.gui.components.table;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.sampler.entity.Customer;
import org.apache.commons.lang.BooleanUtils;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Map;

public class StyleTableFrame extends AbstractFrame {
    @Inject
    private Table customerTable;

    @Override
    public void init(Map<String, Object> params) {
        customerTable.setStyleProvider(new Table.StyleProvider() {
            @Nullable
            @Override
            public String getStyleName(Entity entity, String property) {
                Customer customer = (Customer) entity;
                if (property == null) {
                    if (customer.getAge() > 30) {
                        return "adult-customer";
                    }
                } else if (property.equals("active")) {
                    if (BooleanUtils.isTrue(customer.getActive()))
                        return "customer-active";
                    else
                        return "customer-inactive";
                }
                return null;
            }
        });
    }
}