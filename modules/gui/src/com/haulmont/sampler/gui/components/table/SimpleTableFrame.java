package com.haulmont.sampler.gui.components.table;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.CheckBox;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.ValueListener;

import javax.inject.Inject;
import java.util.Map;

public class SimpleTableFrame extends AbstractFrame {
    @Inject
    private CheckBox multiselect;
    @Inject
    private CheckBox columnControlVisible;
    @Inject
    private CheckBox sortable;
    @Inject
    private CheckBox reorderingAllowed;
    @Inject
    private Table ordersTable;

    @Override
    public void init(Map<String, Object> params) {
        multiselect.setValue(ordersTable.isMultiSelect());
        sortable.setValue(ordersTable.isSortable());
        columnControlVisible.setValue(ordersTable.getColumnControlVisible());
        reorderingAllowed.setValue(ordersTable.getColumnReorderingAllowed());

        multiselect.addListener(new ValueListener() {
            @Override
            public void valueChanged(Object source, String property, Object prevValue, Object value) {
                ordersTable.setMultiSelect((boolean) value);
            }
        });
        sortable.addListener(new ValueListener() {
            @Override
            public void valueChanged(Object source, String property, Object prevValue, Object value) {
                ordersTable.setSortable((boolean) value);
            }
        });
        columnControlVisible.addListener(new ValueListener() {
            @Override
            public void valueChanged(Object source, String property, Object prevValue, Object value) {
                ordersTable.setColumnControlVisible((boolean) value);
            }
        });
        reorderingAllowed.addListener(new ValueListener() {
            @Override
            public void valueChanged(Object source, String property, Object prevValue, Object value) {
                ordersTable.setColumnReorderingAllowed((boolean) value);
            }
        });
    }
}