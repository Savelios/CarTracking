package com.example.demo.backend.views;

import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.example.demo.component.view.MapView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class HeaderView extends HorizontalLayout {

    private final AuthenticatedUser authenticatedUser;
    public Button authButton = new Button();

    public HeaderView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        screen();
    }

    public void screen() {
        authButton.setText("Вход в систему");

        authButton.addClassNames("auth-button");

        authButton.addClickListener(event -> {
            authButton.getUI().ifPresent(ui -> ui.navigate("/auth"));
        });


        this.addClassNames("view-header");
        this.add(createLogo(), authButton);
    }

    public Div createLogo() {
        Div container = new Div();
        Anchor refresh = new Anchor("/", new Image("https://i.ibb.co/wh3LsF6/gpsLOGO.png", "My Alt Image"));
        refresh.addClassNames("logo");
        container.add(refresh);
        return container;
    }
}
