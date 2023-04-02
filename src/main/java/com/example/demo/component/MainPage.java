package com.example.demo.component;

import com.example.demo.MainLayout;
import com.example.demo.backend.views.SideBarView;
import com.example.demo.component.view.MapView;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.stereotype.Component;

@Route(value = "/main", layout = MainLayout.class)
@AnonymousAllowed
@Component
public class MainPage extends SplitLayout {
    private final MapView mapView;
    private final SideBarView sideBarView;
    public MainPage(MapView mapView, SideBarView sideBarView) {
        this.mapView = mapView;
        this.sideBarView = sideBarView;
        this.setWidth("100%");
        this.setHeight("100%");

        this.addClassNames("main-page");
        mapView.setMinWidth("60%");

        this.sideBarView.setMinWidth("1%");
        this.sideBarView.setMaxWidth("32%");

        this.addToPrimary(this.sideBarView);
        this.addToSecondary(mapView);
    }
}
