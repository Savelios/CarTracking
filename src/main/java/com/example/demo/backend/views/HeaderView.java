package com.example.demo.backend.views;

import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class HeaderView extends HorizontalLayout {

    private final AuthenticatedUser authenticatedUser;

    public HeaderView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        screen();

    }

    public void screen() {
        Button authButton = new Button("Вход  систему");
        authButton.addClassNames("auth-button");

        authButton.addClickListener(event -> {
            authButton.getUI().ifPresent(ui -> ui.navigate("/auth"));
        });

//        if (authenticatedUser.get().isPresent()) {
//            mapView.btnBuildLocation.setVisible(true);
//            mapView.btnCenter.setVisible(true);
//            mapView.btnLunch.setVisible(true);
//            authButton.setVisible(false);
//        }else {
//            mapView.btnBuildLocation.setVisible(false);
//            mapView.btnCenter.setVisible(false);
//            mapView.btnLunch.setVisible(false);
//            authButton.setVisible(true);
//        }
        this.addClassNames("view-header");
        this.add(createLogo(), authButton);
    }

    public Div createLogo() {
        Div container = new Div();
        Anchor refresh = new Anchor("/", new Image("https://i.ibb.co/c112R87/gps-LOGOBlue.png", "My Alt Image"));
        refresh.addClassNames("logo");
        container.add(refresh);
        return container;
    }
}
