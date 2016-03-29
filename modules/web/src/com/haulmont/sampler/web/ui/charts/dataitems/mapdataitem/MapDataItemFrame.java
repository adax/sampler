package com.haulmont.sampler.web.ui.charts.dataitems.mapdataitem;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.charts.gui.amcharts.model.data.ListDataProvider;
import com.haulmont.charts.gui.amcharts.model.data.MapDataItem;
import com.haulmont.charts.gui.components.charts.Chart;
import com.haulmont.cuba.gui.components.AbstractFrame;

import javax.inject.Inject;
import java.util.Map;

public class MapDataItemFrame extends AbstractFrame {
    @Inject
    private Chart chart;

    @Override
    public void init(Map<String, Object> params) {
        ListDataProvider dataProvider = new ListDataProvider();
        dataProvider.addItem(new MapDataItem(
                ParamsMap.of("value", 75, "description", "Sky", "color", "#446493")));
        dataProvider.addItem(new MapDataItem(
                ParamsMap.of("value", 7, "description", "Shady side of pyramid", "color", "#5E3D2C")));
        dataProvider.addItem(new MapDataItem(
                ParamsMap.of("value", 18, "description", "Sunny side of pyramid", "color", "#D0A557")));

        chart.getConfiguration().setDataProvider(dataProvider);
    }
}