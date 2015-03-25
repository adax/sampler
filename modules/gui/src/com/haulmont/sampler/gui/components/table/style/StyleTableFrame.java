package com.haulmont.sampler.gui.components.table.style;

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
                    if (BooleanUtils.isTrue(customer.getActive())) {
                        return "active-customer";
                    }
                } else if (property.equals("grade")) {
                    switch (customer.getGrade()) {
                        case PREMIUM:
                            return "premium-grade";
                        case HIGH:
                            return "high-grade";
                        case STANDARD:
                            return "standard-grade";
                        default:
                            return null;
                    }
                }
                return null;
            }
        });
    }
}