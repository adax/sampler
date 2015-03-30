package com.haulmont.sampler.gui.entities.orderline;

import com.haulmont.cuba.gui.WindowParams;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Table;

import javax.inject.Inject;
import java.util.Map;


public class OrderLineBrowse extends AbstractLookup {
    @Inject
    private Table orderLinesTable;

    @Override
    public void init(Map<String, Object> params) {
        if (WindowParams.MULTI_SELECT.getBool(getContext())) {
            orderLinesTable.setMultiSelect(true);
        }
    }
}