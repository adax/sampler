/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/license for details.
 */

package com.haulmont.sampler.web.ui.charts.events.zoom;

import com.haulmont.charts.gui.components.charts.Chart;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Label;

import javax.inject.Inject;
import java.util.Map;

public class ChartZoomFrame extends AbstractFrame {
    @Inject
    private Chart lineChart;
    @Inject
    private Label label;

    @Override
    public void init(Map<String, Object> params) {
        lineChart.addZoomListener(event ->
                label.setValue(String.format("Zoom: <strong>[%s, %s]</strong>",
                        event.getStartValue(), event.getEndValue())));

        lineChart.addCursorZoomListener(event ->
                showNotification("CursorZoomEvent", eventInfo(event), NotificationType.HUMANIZED_HTML));
    }

    private String eventInfo(Chart.CursorZoomEvent event) {
        return "<strong>Start: </strong>" + event.getStart() + "<br/>" +
                "<strong>End: </strong>" + event.getEnd();
    }
}