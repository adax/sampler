package com.haulmont.sampler.web.ui.map.marker;

import com.haulmont.charts.gui.components.map.MapViewer;
import com.haulmont.charts.gui.map.model.InfoWindow;
import com.haulmont.charts.gui.map.model.Marker;
import com.haulmont.charts.gui.map.model.listeners.MapClickListener;
import com.haulmont.charts.gui.map.model.listeners.MarkerClickListener;
import com.haulmont.cuba.gui.components.AbstractFrame;

import javax.inject.Inject;
import java.util.Map;

public class MarkerMapFrame extends AbstractFrame {

    @Inject
    private MapViewer map;

    @Override
    public void init(Map<String, Object> params) {

        map.setCenter(map.createGeoPoint(53.590905, -2.24955));
        addMarker(53.590905, -2.24955);

        map.addMapClickListener(new MapClickListener() {
            @Override
            public void onClick(MapClickEvent event) {
                addMarker(event.getPosition().getLatitude(), event.getPosition().getLongitude());
            }
        });

        map.addMarkerClickListener(new MarkerClickListener() {
            @Override
            public void onClick(MarkerClickEvent event) {
                Marker marker = event.getMarker();
                String caption = String.format("Marker clicked: %.2f, %.2f",
                        marker.getPosition().getLatitude(),
                        marker.getPosition().getLongitude());
                InfoWindow infoWindow = map.createInfoWindow(caption, marker);
                map.openInfoWindow(infoWindow);
            }
        });
    }

    private void addMarker(double latitude, double longitude) {
        Marker marker = map.createMarker("My place", map.createGeoPoint(latitude, longitude), true);
        marker.setClickable(true);
        map.addMarker(marker);
    }
}