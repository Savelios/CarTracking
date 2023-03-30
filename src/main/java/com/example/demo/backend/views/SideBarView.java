package com.example.demo.backend.views;

import com.example.demo.models.MessageType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.RolesAllowed;

import java.util.Arrays;

public class SideBarView extends HorizontalLayout {

//    private final Div trackContainer;
//    private final Div carContainer;
//    private final Div userContainer;
    public SideBarView() {
        screen();
    }
    public void screen() {
        Button showButton = new Button("Показать");
        showButton.addClickListener(event -> {
//            enterButton.getUI().ifPresent(ui -> ui.navigate("/"));
        });
        showButton.addClassNames("show-button");

        Button createButton = new Button("Создать");
        createButton.addClickListener(event -> {
//            enterButton.getUI().ifPresent(ui -> ui.navigate("/"));
        });
        createButton.addClassNames("create-button");

        addClassNames("sideBar");
        add(showButton, createButton);
    }
}
