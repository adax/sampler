package com.haulmont.sampler.web.ui.charts.pie.pie;

import com.haulmont.charts.gui.components.charts.Chart;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.sampler.entity.CountryLitres;

import javax.inject.Inject;
import java.util.Map;

public class PieChartFrame extends AbstractFrame {

    @Inject
    private Chart pieChart;

    @Override
    public void init(Map<String, Object> params) {
        pieChart.addSlicePullOutListener(event -> {
            CountryLitres countryLitres = (CountryLitres) event.getItem();
            String msg = countryLitres.getCountry() + ": " + countryLitres.getLitres() + " litres";
            showNotification(msg, NotificationType.HUMANIZED);
        });
    }
}