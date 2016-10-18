package com.haulmont.sampler.web.ui.charts.map.polygon;

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

        map.setCenter(map.createGeoPoint(53.590905, -1.24955));

        List<GeoPoint> coordinates = new ArrayList<>();
        coordinates.add(map.createGeoPoint(53.49, -2.54));
        coordinates.add(map.createGeoPoint(53.49, -2.22));
        coordinates.add(map.createGeoPoint(53.89, -2.22));
        coordinates.add(map.createGeoPoint(53.99, -2.94));
        Polygon polygon = map.createPolygon(coordinates, "#9CFBA9", 0.6, "#2CA860", 1.0, 2);
        map.addPolygonOverlay(polygon);

        coordinates = new ArrayList<>();
        coordinates.add(map.createGeoPoint(53.74, -1.35));
        coordinates.add(map.createGeoPoint(53.69, -0.59));
        coordinates.add(map.createGeoPoint(53.31, -0.64));
        coordinates.add(map.createGeoPoint(53.34, -1.41));
        polygon = map.createPolygon(coordinates, "#0068A3", 0.6, "#081B42", 1.0, 2);
        polygon.setEditable(true);
        map.addPolygonOverlay(polygon);
    }
}