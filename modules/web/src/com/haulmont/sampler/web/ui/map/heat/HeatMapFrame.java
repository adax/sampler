package com.haulmont.sampler.web.ui.map.heat;

import com.haulmont.charts.gui.components.map.MapViewer;
import com.haulmont.charts.gui.map.model.GeoPoint;
import com.haulmont.charts.gui.map.model.layer.HeatMapLayer;
import com.haulmont.cuba.gui.components.AbstractFrame;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HeatMapFrame extends AbstractFrame {

    @Inject
    private MapViewer map;

    @Override
    public void init(Map<String, Object> params) {

        map.setZoom(13);
        map.setCenter(map.createGeoPoint(53.452, -1.998));

        HeatMapLayer heatMapLayer = map.createHeatMapLayer();
        List<GeoPoint> data = new ArrayList<>();

        data.add(map.createGeoPoint(53.454, -1.997));
        data.add(map.createGeoPoint(53.454, -1.998));
        data.add(map.createGeoPoint(53.454, -1.999));
        data.add(map.createGeoPoint(53.453, -2.00));
        data.add(map.createGeoPoint(53.452, -2.00));
        data.add(map.createGeoPoint(53.451, -2.00));
        data.add(map.createGeoPoint(53.450, -1.997));
        data.add(map.createGeoPoint(53.450, -1.998));
        data.add(map.createGeoPoint(53.450, -1.999));

        heatMapLayer.setData(data);
        map.addHeatMapLayer(heatMapLayer);
    }
}