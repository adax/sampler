package com.haulmont.sampler.web.ui.map.polygon;

import com.haulmont.charts.gui.components.map.MapViewer;
import com.haulmont.charts.gui.map.model.GeoPoint;
import com.haulmont.charts.gui.map.model.Polygon;
import com.haulmont.cuba.gui.components.AbstractFrame;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PolygonMapFrame extends AbstractFrame {

    @Inject
    private MapViewer map;

    @Override
    public void init(Map<String, Object> params) {

        map.setCenter(map.createGeoPoint(53.590905, -2.24955));

        List<GeoPoint> coordinates = new ArrayList<>();
        coordinates.add(map.createGeoPoint(53.49, -2.54));
        coordinates.add(map.createGeoPoint(53.49, -2.22));
        coordinates.add(map.createGeoPoint(53.89, -2.22));
        coordinates.add(map.createGeoPoint(53.99, -2.94));
        Polygon polygon = map.createPolygon(coordinates, "#9CFBA9", 0.6, "#2CA860", 1.0, 2);
        map.addPolygonOverlay(polygon);
    }
}