package com.example.demo.backend.views;

import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class HeaderView extends HorizontalLayout {

//    private final Div btnContainer = new Div();
//    private final Button usersBtn = new Button();
//    private final Button carsBtn = new Button();
//    private final Button tracksBtn = new Button();
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

        Image brLine = new Image();
        brLine.setSrc("https://i.ibb.co/7JGwZpX/line.png");
        brLine.addClassNames("brLine");
        Div btnsContainer = new Div();
        Button usersBtn = new Button("Водители");
        Button carsBtn = new Button("Автомобили");
        Button tracksBtn = new Button("Треки");
        btnsContainer.add(brLine, usersBtn, carsBtn, tracksBtn);

        btnsContainer.addClassNames("btnsContainer");
        usersBtn.addClassNames("usersBtn");
        carsBtn.addClassNames("carsBtn");
        tracksBtn.addClassNames("tracksBtn");

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
        this.add(createLogo(), btnsContainer, authButton);
    }

    public Div createLogo() {
        Div container = new Div();
        Anchor refresh = new Anchor("/", new Image("https://i.ibb.co/c112R87/gps-LOGOBlue.png", "My Alt Image"));
        refresh.addClassNames("logo");
        container.add(refresh);
        return container;
    }
}
