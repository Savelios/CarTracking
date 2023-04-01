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


        Image brLine = new Image();
        brLine.setSrc("https://i.ibb.co/7JGwZpX/line.png");
        brLine.addClassNames("brLine");
        Div btnsContainer = new Div();
        Button usersBtn = new Button("Водители");

        Image firstBrDot = new Image();
        firstBrDot.setSrc("https://i.ibb.co/prnmMcL/brDot.png");
        firstBrDot.addClassNames("firstBrDot");
        Button carsBtn = new Button("Автомобили");

        Image secondBrDot = new Image();
        secondBrDot.setSrc("https://i.ibb.co/prnmMcL/brDot.png");
        secondBrDot.addClassNames("secondBrDot");
        Button tracksBtn = new Button("Треки");
        btnsContainer.add(brLine, usersBtn, firstBrDot, carsBtn, secondBrDot, tracksBtn);

        btnsContainer.addClassNames("btnsContainer", "hidden");
        usersBtn.addClassNames("usersBtn");
        carsBtn.addClassNames("carsBtn");
        tracksBtn.addClassNames("tracksBtn");

        authButton.addClickListener(event -> {
            authButton.getUI().ifPresent(ui -> ui.navigate("/auth"));
        });


        if (!authenticatedUser.get().isPresent()) {
            btnsContainer.setVisible(true);
        } else {
            btnsContainer.setVisible(false);
        }

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
