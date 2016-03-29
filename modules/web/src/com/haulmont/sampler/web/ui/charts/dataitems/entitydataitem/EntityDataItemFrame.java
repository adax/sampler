package com.haulmont.sampler.web.ui.charts.dataitems.entitydataitem;

import com.haulmont.charts.gui.amcharts.model.data.EntityDataItem;
import com.haulmont.charts.gui.amcharts.model.data.ListDataProvider;
import com.haulmont.charts.gui.components.charts.Chart;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.sampler.entity.ValueDescription;

import javax.inject.Inject;
import java.util.Map;

public class EntityDataItemFrame extends AbstractFrame {
    @Inject
    private Chart chart;
    @Inject
    private Metadata metadata;

    @Override
    public void init(Map<String, Object> params) {
        ListDataProvider dataProvider = new ListDataProvider();
        dataProvider.addItem(new EntityDataItem(valueDescription(75, "Sky", "#446493")));
        dataProvider.addItem(new EntityDataItem(valueDescription(7, "Shady side of pyramid", "#5E3D2C")));
        dataProvider.addItem(new EntityDataItem(valueDescription(18, "Sunny side of pyramid", "#D0A557")));

        chart.getConfiguration().setDataProvider(dataProvider);
    }

    private ValueDescription valueDescription(Integer value, String description, String color) {
        ValueDescription entity = metadata.create(ValueDescription.class);
        entity.setValue(value);
        entity.setDescription(description);
        entity.setColor(color);
        return entity;
    }
}