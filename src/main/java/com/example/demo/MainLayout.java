package com.example.demo;


import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.example.demo.backend.views.ContentView;
import com.example.demo.backend.views.HeaderView;
import com.example.demo.backend.views.SideBarView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@AnonymousAllowed
@Route("/")
public class MainLayout extends VerticalLayout implements RouterLayout {

    private final HeaderView headerView;
    private final ContentView contentView;
    private final AuthenticatedUser authenticatedUser;

    private final Label label = new Label("" +
            "Следите за местоположением Вашего " +
            "автомобиля в режиме реального времени " +
            "с системой GPS Tracking System.");

    @Autowired
    public MainLayout(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        this.headerView = new HeaderView(this.authenticatedUser);
        this.contentView = new ContentView();
        this.addClassNames("main-view");
        this.label.addClassNames("content-label");
        setPadding(false);

        add(headerView, contentView);
    }

}
