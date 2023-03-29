package com.example.demo.component.view;

import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.example.demo.backend.views.HeaderView;
import com.example.demo.ui.OSMMapView;
import com.example.demo.utils.MapJSUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Route(value = "/map")
@AnonymousAllowed
public class MapView extends VerticalLayout {
    private boolean viewLunch = false;
    private Button btnCenter = new Button("Показать");
    //private LMap map;
    private OSMMapView mapView;
    AuthenticatedUser authenticatedUser;
    public MapView() {

        HeaderView headerView = new HeaderView(authenticatedUser);

        this.setPadding(false);
        this.addClassNames("body");

        this.btnCenter.addClickListener(e -> {
            final Random rnd = new Random();
            final List<MapJSUtil.Coordinate> coordinates = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                final double lat = rnd.nextDouble(-12.0, 54.0);
                final double lon = rnd.nextDouble(22.0, 44.0);
                MapJSUtil.Coordinate coordinate = new MapJSUtil.Coordinate();
                coordinate.latitude = lat;
                coordinate.longitude = lon;
                coordinates.add(coordinate);
            }
            mapView.addPolyline(coordinates);
        });
        mapView = new OSMMapView();
        mapView.addClassNames("map");
        this.add(headerView, mapView);
        this.setSizeFull();
    }
}