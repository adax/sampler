package com.haulmont.sampler.gui.components.table.simple;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.CheckBox;
import com.haulmont.cuba.gui.components.Table;

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

        multiselect.addValueChangeListener(e ->
                ordersTable.setMultiSelect((boolean) e.getValue()));
        sortable.addValueChangeListener(e ->
                ordersTable.setSortable((boolean) e.getValue()));
        columnControlVisible.addValueChangeListener(e ->
                ordersTable.setColumnControlVisible((boolean) e.getValue()));
        reorderingAllowed.addValueChangeListener(e ->
                ordersTable.setColumnReorderingAllowed((boolean) e.getValue()));
    }
}