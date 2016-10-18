package com.haulmont.sampler.web.ui.components.lookupfield.enumbased;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.sampler.entity.CustomerGrade;

import javax.inject.Inject;
import java.util.Map;

public class EnumBasedLookupFieldFrame extends AbstractFrame {
    @Inject
    private LookupField lookupField;

    @Override
    public void init(Map<String, Object> params) {
        lookupField.setOptionsEnum(CustomerGrade.class);
    }
}