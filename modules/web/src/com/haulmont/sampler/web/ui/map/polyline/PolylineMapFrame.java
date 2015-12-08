package com.haulmont.sampler.web.ui.map.polyline;

import com.haulmont.charts.gui.components.map.MapViewer;
import com.haulmont.charts.gui.map.model.GeoPoint;
import com.haulmont.charts.gui.map.model.Polyline;
import com.haulmont.charts.gui.map.model.directions.*;
import com.haulmont.cuba.gui.components.AbstractFrame;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PolylineMapFrame extends AbstractFrame {

    @Inject
    private MapViewer map;

    @Override
    public void init(Map<String, Object> params) {
        map.setCenter(map.createGeoPoint(53.476475, -2.246761));

        List<GeoPoint> coordinates = new ArrayList<>();
        coordinates.add(map.createGeoPoint(53.49, -2.24));
        coordinates.add(map.createGeoPoint(53.49, -2.48));
        coordinates.add(map.createGeoPoint(53.53, -2.54));
        coordinates.add(map.createGeoPoint(53.89, -2.54));
        coordinates.add(map.createGeoPoint(53.89, -2.24));
        Polyline polyline = map.createPolyline(coordinates);
        polyline.setStrokeWeight(2);
        polyline.setStrokeOpacity(0.8);
        map.addPolyline(polyline);
    }
}