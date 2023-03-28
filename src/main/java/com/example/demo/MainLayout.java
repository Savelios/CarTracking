package com.example.demo;


import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.example.demo.backend.views.ContentView;
import com.example.demo.backend.views.HeaderView;
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

    @Autowired
    public MainLayout(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        this.headerView = new HeaderView(this.authenticatedUser);
        this.contentView = new ContentView();
        this.addClassNames("main-view");
        setPadding(false);

        add(headerView, contentView);
    }

}
