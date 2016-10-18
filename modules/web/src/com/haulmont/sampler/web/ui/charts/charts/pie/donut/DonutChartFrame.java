package com.haulmont.sampler.web.ui.charts.charts.pie.donut;

import com.haulmont.charts.gui.amcharts.model.charts.PieChart;
import com.haulmont.charts.gui.amcharts.model.data.ListDataProvider;
import com.haulmont.charts.gui.amcharts.model.data.MapDataItem;
import com.haulmont.charts.gui.components.charts.Chart;
import com.haulmont.cuba.gui.components.AbstractFrame;

import javax.inject.Inject;
import java.util.Map;

public class DonutChartFrame extends AbstractFrame {

    @Inject
    protected Chart donutChart;

    @Override
    public void init(Map<String, Object> params) {
        ListDataProvider dataProvider = new ListDataProvider();

        dataProvider.addItem(new MapDataItem().add("title", "New").add("value", 4852));
        dataProvider.addItem(new MapDataItem().add("title", "Returning").add("value", 9899));

        PieChart polarChartConfiguration = (PieChart) donutChart.getConfiguration();
        polarChartConfiguration.setDataProvider(dataProvider);
    }
}