package com.haulmont.sampler.web.ui.map.actions;

import com.haulmont.charts.gui.components.map.MapViewer;
import com.haulmont.charts.gui.map.model.GeoPoint;
import com.haulmont.charts.gui.map.model.listeners.MapInitListener;
import com.haulmont.charts.gui.map.model.listeners.MapMoveListener;
import com.haulmont.charts.gui.map.model.listeners.click.MapClickListener;
import com.haulmont.cuba.gui.components.AbstractFrame;

import javax.inject.Inject;
import java.util.Map;

public class ActionsMapFrame extends AbstractFrame {

    @Inject
    private MapViewer map;

    @Override
    public void init(Map<String, Object> params) {
        initMap();
        addMapListeners();
    }

    private void initMap() {
        map.addMapInitListener(new MapInitListener() {
            @Override
            public void init(GeoPoint center, int zoom, GeoPoint boundNE, GeoPoint boundSW) {
                String content = String.format("Map init. center: %s, zoom: %d, boundNE: %s, boundSW: %s",
                        string(center), zoom, string(boundNE), string(boundSW));
                showNotification(content, NotificationType.HUMANIZED);
            }
        });

        map.setCenter(map.createGeoPoint(53.490905, -2.249558));
        map.setZoom(8);
    }

    private String string(GeoPoint p) {
        return p != null ? String.format("(%.2f, %.2f)", p.getLatitude(), p.getLongitude()) : "null";
    }

    private void addMapListeners() {
        map.addMapClickListener(new MapClickListener() {
            @Override
            public void onClick(MapClickListener.MapClickEvent event) {
                String caption = String.format("Map clicked: %.2f, %.2f", event.getPosition().getLatitude(),
                        event.getPosition().getLongitude());
                showNotification(caption, NotificationType.HUMANIZED);
            }
        });

        map.addMapMoveListener(new MapMoveListener() {
            @Override
            public void onMove(MapMoveListener.MapMoveEvent event) {
                String content = "North-east bound: " + map.getBoundNorthEast().getLatitude() +
                        ", " + map.getBoundNorthEast().getLongitude() + "\n"
                        + "South-west bound: " + map.getBoundSouthWest().getLatitude() +
                        ", " + map.getBoundSouthWest().getLongitude() + "\n";
                showNotification("Map have been moved",  content, NotificationType.HUMANIZED);
            }
        });
    }
}