package com.haulmont.sampler.gui.entities.invoiceitem;

import com.haulmont.cuba.gui.WindowParams;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Table;

import javax.inject.Inject;
import java.util.Map;

public class InvoiceItemBrowse extends AbstractLookup {
    @Inject
    private Table invoiceItemsTable;

    @Override
    public void init(Map<String, Object> params) {
        if (WindowParams.MULTI_SELECT.getBool(getContext())) {
            invoiceItemsTable.setMultiSelect(true);
        }
    }
}