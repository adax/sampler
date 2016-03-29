package com.haulmont.sampler.web.ui.charts.dataitems.simpledataitem;

import com.haulmont.charts.gui.amcharts.model.data.ListDataProvider;
import com.haulmont.charts.gui.amcharts.model.data.SimpleDataItem;
import com.haulmont.charts.gui.components.charts.Chart;
import com.haulmont.cuba.gui.components.AbstractFrame;

import javax.inject.Inject;
import java.util.Map;

public class SimpleDataItemFrame extends AbstractFrame {
    @Inject
    private Chart chart;

    @Override
    public void init(Map<String, Object> params) {
        ListDataProvider dataProvider = new ListDataProvider();
        dataProvider.addItem(new SimpleDataItem(new ValueDescription(75, "Sky", "#446493")));
        dataProvider.addItem(new SimpleDataItem(new ValueDescription(7, "Shady side of pyramid", "#5E3D2C")));
        dataProvider.addItem(new SimpleDataItem(new ValueDescription(18, "Sunny side of pyramid", "#D0A557")));

        chart.getConfiguration().setDataProvider(dataProvider);
    }

    public class ValueDescription {
        private Integer value;
        private String description;
        private String color;

        public ValueDescription(Integer value, String description, String color) {
            this.value = value;
            this.description = description;
            this.color = color;
        }

        public Integer getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        public String getColor() {
            return color;
        }
    }
}