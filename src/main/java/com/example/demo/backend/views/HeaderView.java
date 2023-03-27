package com.example.demo.backend.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class HeaderView extends HorizontalLayout {

    public HeaderView() {

        screen();
    }

    public void screen() {
        Button authButton = new Button("Вход  систему");

        authButton.addClickListener(event -> {
            authButton.getUI().ifPresent(ui -> ui.navigate("/auth"));
            UI.getCurrent().getPage().reload();
        });


        this.addClassNames("view-header");
        this.add(createLogo(), authButton);
    }

    private static Div createLogo() {
        Div container = new Div();
        Anchor refresh = new Anchor("/", new Image("https://solomonpro.ru/img/logo.svg", "My Alt Image"));
//        refresh.addClassNames("view-title");
//        container.addClassNames("view-title-container");
        container.add(refresh);
        return container;
    }
}
