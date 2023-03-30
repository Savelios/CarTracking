package com.example.demo.component;

import com.example.demo.MainLayout;
import com.example.demo.backend.views.SideBarView;
import com.example.demo.component.view.MapView;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "/main", layout = MainLayout.class)
@AnonymousAllowed
public class MainPage extends SplitLayout {
    private final MapView mapView;
    private final SideBarView sideBarView;
    public MainPage() {
        this.setWidth("100%");
        this.setHeight("100%");

        this.addClassNames("main-page");

        mapView = new MapView();
        mapView.setMinWidth("60%");

        sideBarView = new SideBarView();
        sideBarView.setMinWidth("1%");

        this.addToPrimary(sideBarView);
        this.addToSecondary(mapView);
    }
}
