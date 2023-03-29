package com.example.demo.component.view;

import com.example.demo.ui.OSMMapView;
import com.example.demo.utils.MapJSUtil;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import software.xdev.vaadin.maps.leaflet.flow.data.LComponent;
import software.xdev.vaadin.maps.leaflet.flow.data.LMarker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Route("/map")
@RolesAllowed("ROLE_USER")
public class MapView extends VerticalLayout {

    private boolean viewLunch = false;
    private Button btnLunch = new Button("Показать офис");
    private Button btnCenter = new Button("Показать офис");
    private Button btnBuildLocation;
    //private LMap map;
    private LMarker markerZob;
    private LMarker markerGreek;

    private OSMMapView mapView;


    public MapView() {
        this.setPadding(false);
        this.addClassNames("body");
        //this.initMapComponents();

        final HorizontalLayout headLayout = new HorizontalLayout();
        headLayout.addClassNames("header");

        this.btnLunch.addClickListener(this::btnLunchClick);


        final HorizontalLayout hlButtonContainer = new HorizontalLayout();
        hlButtonContainer.addClassNames("footer");
        hlButtonContainer.setJustifyContentMode(JustifyContentMode.BETWEEN);
        hlButtonContainer.setPadding(false);
        hlButtonContainer.add(this.btnLunch, this.btnCenter,
                this.btnBuildLocation = new Button("Построить маршрут", ev ->
                {
                    final Icon icoClose = VaadinIcon.CLOSE.create();
                    icoClose.addClassNames("close-icon");

                    final Button btnBuild = new Button("Построить");
                    btnBuild.addClassNames("btnBuild");

                    final Dialog dialog = new Dialog(icoClose, btnBuild);
                    dialog.addClassNames("dialog");

                    dialog.setWidth("50vw");
                    dialog.setHeight("50vh");
                    dialog.open();
                    icoClose.addClickListener(iev -> dialog.close());

                }));
        this.btnBuildLocation.addClassNames("btnBuildLocation");
        this.btnCenter.addClassNames("btShowOffice");
        this.btnLunch.addClassNames("btnZoom");
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
        this.add(hlButtonContainer);
        mapView = new OSMMapView();
        mapView.addClassNames("map");
        this.add(mapView);
        this.setSizeFull();
    }

    private void btnLunchClick(final ClickEvent<Button> event) {
        this.viewLunch = !this.viewLunch;

        final List<LComponent> normalComponents = Arrays.asList(this.markerZob);
        final List<LComponent> lunchComponents = Arrays.asList(
                this.markerGreek);

        this.btnLunch.setText(this.viewLunch ? "Показать офис" : "Вернуться");
    }
}