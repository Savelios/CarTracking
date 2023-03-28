package com.example.demo.component.view;

import com.example.demo.utils.MapJSUtil;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import software.xdev.vaadin.maps.leaflet.flow.LMap;
import software.xdev.vaadin.maps.leaflet.flow.data.LCenter;
import software.xdev.vaadin.maps.leaflet.flow.data.LComponent;
import software.xdev.vaadin.maps.leaflet.flow.data.LDivIcon;
import software.xdev.vaadin.maps.leaflet.flow.data.LMarker;
import software.xdev.vaadin.maps.leaflet.flow.data.LPoint;
import software.xdev.vaadin.maps.leaflet.flow.data.LTileLayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Route("/map")
@AnonymousAllowed
public class MapView extends VerticalLayout {
    private boolean viewLunch = false;
    private final Button btnLunch = new Button("Показать офис");
    private final Button btnCenter = new Button("Показать офис");
    private Button btnBuildLocation;
    private LMap map;
    private LMarker markerZob;
    private LMarker markerGreek;
    public MapView() {

        this.setPadding(false);
        this.addClassNames("body");
        this.initMapComponents();

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
        this.btnCenter.addClickListener(e-> map.centerAndZoom(new LPoint(44.044150, 42.854870), new LPoint(44.044150, 42.854870)));
        this.add(this.map, hlButtonContainer);
        this.setSizeFull();
    }

    private void btnLunchClick(final ClickEvent<Button> event)
    {
        this.viewLunch = !this.viewLunch;

        final List<LComponent> normalComponents = Arrays.asList(this.markerZob);
        final List<LComponent> lunchComponents = Arrays.asList(
                this.markerGreek);

        this.map.setViewPoint(new LCenter(44.044150, 42.854870, this.viewLunch ? 16 : 17));
        this.map.removeLComponents(this.viewLunch ? normalComponents : lunchComponents);
        this.map.addLComponents(this.viewLunch ? lunchComponents : normalComponents);

        this.btnLunch.setText(this.viewLunch ? "Показать офис" : "Вернуться");
    }
    private void initMapComponents()
    {
        this.markerZob = new LMarker(44.044150, 42.854870, "ZoB");
        this.markerZob.setPopup("ЦИГАНЛАР office station");

        final LMarker markerInfo = new LMarker(44.044150, 42.854870);
        final LDivIcon div = new LDivIcon(
                "<p><center><b>Добро пожаловать в систему отслеживания автомобилей!</b></center></p>");

        markerInfo.setDivIcon(div);

        this.markerGreek = new LMarker(49.675126, 12.161899);
        this.markerGreek.setPopup("Greek Food");

        this.map = new LMap(49.675126, 12.160733, 17);
        this.map.setTileLayer(LTileLayer.DEFAULT_OPENSTREETMAP_TILE);
        this.map.setId("main_map");

        //this.map.setSizeFull();
        // add some logic here for called Markers (token)
        this.map.addMarkerClickListener(ev -> System.out.println(ev.getTag()));
        this.map.addClassNames("map");
        this.map.addLComponents(
                markerInfo,
                this.markerZob);

        List<MapJSUtil.Coordinate> coordinates = new ArrayList<>();
        for(double i = 0; i < 10; i++) {
            MapJSUtil.Coordinate coordinate = new MapJSUtil.Coordinate();
            coordinate.latitude = 44.0 + (i/10);
            coordinate.longitude = 42.8;
            coordinates.add(coordinate);
        }
        String js = MapJSUtil.waypoints("main_map", coordinates);
        Page currentPage = UI.getCurrent().getPage();
        var result = currentPage.executeJs(js);
    }
}